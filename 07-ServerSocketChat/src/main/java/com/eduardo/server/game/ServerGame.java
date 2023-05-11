package com.eduardo.server.game;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.eduardo.entity.User;
import com.eduardo.event.OnUserConnected;
import com.eduardo.event.OnUserData;
import com.eduardo.helper.Response;
import com.eduardo.helper.ErrorFormatException;
import com.eduardo.helper.Protocol;
import com.eduardo.tcp.server.Server;
import com.eduardo.listener.ServerListenerUser;
import com.eduardo.model.ModelUser;
import com.eduardo.tcp.server.TypeSend;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Objects;

public class ServerGame implements ServerListenerUser {

    public static String LOG = ServerGame.class.getName();

    private Map<UUID, User> users;
    private Server server;

    public ServerGame() {
        this(8000);
    }

    public ServerGame(int port) {
        this.server = new Server(port);
        this.server.addServerListener(this);
        this.users = new HashMap<>();
    }

    public void run() {
        handlerLoadDataStart();
        server.listen();
        handlerSaveDataClose();
    }

    public void handlerLoadDataStart() {
        ModelUser modelUser = new ModelUser();
        modelUser.open();
        Map<UUID, User> users = modelUser.findAll();
        this.users.putAll(users);
        modelUser.close();
    }

    public void handlerSaveDataClose() {
        new HandlerSaveData(this.users).start();
    }

    @Override
    public void ListenerUserConnected(OnUserConnected e) {
        server.send(e.getSessionId(), Protocol.setFormatAuthenticated(), TypeSend.ONE);
    }

    @Override
    public void ListenerUserData(OnUserData e) {
        routerData(e);
    }

    public void routerData(OnUserData e) {
        switch (Protocol.getFormat(e.getData())) {
            case "LOGIN":
                login(e);
                break;
            case "REGISTER":
                register(e);
                break;
            case "DATA":
                data(e);
                break;
            case "TABLERO_SET":
                setTablero(e);
                break;
            default:
                System.out.println(LOG + " : Format Incorrected!!!");
        }
    }

    public void login(OnUserData e) {
        try {
            Map<String, String> data = Protocol.formatLogin(e.getData());
            User user = findUserByNickName(data.get("NAME"));
            if (Objects.isNull(user)) {
                System.out.println(LOG + " : " + "User Not Found");
                server.send(UUID.fromString(data.get("SESSIONID")), Protocol.setFormatResponse(false, Response.LOGIN, "User NotFound !!!"), TypeSend.ONE);
                return;
            }
            BCrypt.Result result = BCrypt.verifyer(BCrypt.Version.VERSION_2A).verify(data.get("PASSWORD").toCharArray(), user.getPassword());
            if (!result.verified) {
                System.out.println(LOG + " : " + "Passsowrd Incorrect !!!");
                server.send(UUID.fromString(data.get("SESSIONID")), Protocol.setFormatResponse(false, Response.LOGIN, "User password incorrect !!!"), TypeSend.ONE);
                return;
            }
            user.setSessionId(UUID.fromString(data.get("SESSIONID")));
            System.out.println(LOG + " : New User Login Connected");
            server.send(UUID.fromString(data.get("SESSIONID")), Protocol.setFormatResponse(true, Response.LOGIN, "User successfully logged in !!!"), TypeSend.ONE);
        } catch (ErrorFormatException ex) {
            System.out.println(LOG + ex.getMessage());
        }
    }

    public void register(OnUserData e) {
        try {
            Map<String, String> data = Protocol.formatRegister(e.getData());
            if (Objects.nonNull(findUserByNickName(data.get("NAME")))) {
                System.out.println("No se pude registrar ya existe el user con ese nombre");
                server.send(UUID.fromString(data.get("SESSIONID")), Protocol.setFormatResponse(false, Response.REGISTER, "Not can register the user exists !!!"), TypeSend.ONE);
                return;
            }
            users.put(UUID.randomUUID(), new User(null, data.get("NAME"), data.get("PASSWORD"), UUID.fromString(data.get("SESSIONID"))));
            System.out.println(LOG + " : New User Register Connected");
            server.send(UUID.fromString(data.get("SESSIONID")), Protocol.setFormatResponse(true, Response.REGISTER, "User successfully registered !!!"), TypeSend.ONE);
        } catch (ErrorFormatException ex) {
            System.out.println(LOG + ex.getMessage());
        }
    }

    public void data(OnUserData e) {
        try {
            Map<String, String> data = Protocol.formatData(e.getData());
            User user = findUserBySessionId(UUID.fromString(data.get("SESSIONID")));
            if (Objects.isNull(user)) {
                System.out.println("SESSIONID NO VALIDO !!!");
                return;
            }
            server.send(user.getSessionId(), user.getName() + " : " + data.get("DATA"), TypeSend.ALL);
        } catch (ErrorFormatException ex) {
            System.out.println(LOG + ex.getMessage());
        }
    }

    public void setTablero(OnUserData e) {
        try {
            Map<String, String> data = Protocol.formatTableroSet(e.getData());
            User user = findUserBySessionId(UUID.fromString(data.get("SESSIONID")));
            if (Objects.isNull(user)) {
                System.out.println("SESSIONID NO VALIDO !!!");
                return;
            }
            Tablero tablero = user.getTablero();
            tablero.set(Integer.parseInt(data.get("ROW")), Integer.parseInt(data.get("COL")), Integer.parseInt(data.get("VALUE")));
            tablero.show();
        } catch (ErrorFormatException ex) {
            System.out.println(LOG + ex.getMessage());
        }
    }

    public User findUserBySessionId(UUID sessionId) {
        for (Map.Entry<UUID, User> element : users.entrySet()) {
            UUID userSessionId = element.getValue().getSessionId();
            if (Objects.nonNull(userSessionId) && userSessionId.equals(sessionId)) {
                return element.getValue();
            }
        }
        return null;
    }

    public User findUserByNickName(String name) {
        for (Map.Entry<UUID, User> element : users.entrySet()) {
            if (element.getValue().getName().equals(name)) {
                return element.getValue();
            }
        }
        return null;
    }

}
