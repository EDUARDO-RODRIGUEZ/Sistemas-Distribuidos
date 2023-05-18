package com.eduardo.server.game;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.eduardo.entity.User;
import com.eduardo.event.OnUserConnected;
import com.eduardo.event.OnUserData;
import com.eduardo.helper.Response;
import com.eduardo.helper.ErrorFormatException;
import com.eduardo.helper.ProtocolServer;
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
    private Map<Integer, User> userPlaying;
    private int nextPlay;
    private int countUserPlaying;

    public ServerGame() {
        this(8000);
    }

    public ServerGame(int port) {
        this.server = new Server(port);
        this.server.addServerListener(this);
        this.users = new HashMap<>();
        this.userPlaying = new HashMap<>();
        this.countUserPlaying = 0;
        this.nextPlay = 0;
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
        server.send(e.getSessionId(), ProtocolServer.setFormatAuthenticated(), TypeSend.ONE);
    }

    @Override
    public void ListenerUserData(OnUserData e) {
        routerData(e);
    }

    public void routerData(OnUserData e) {
        switch (ProtocolServer.getFormat(e.getData())) {
            case "LOGIN":
                login(e);
                break;
            case "REGISTER":
                register(e);
                break;
            case "DATA":
                data(e);
                break;
            case "SIZE_MATRIX":
                sizeMatrix(e);
                break;
            case "COUNT_ONLINE":
                countOnline(e);
                break;
            case "TABLERO_SET":
                setTablero(e);
                break;
            case "CONFIRM_PLAY":
                confirmPlay(e);
                break;
            case "INIT_PLAY":
                initPlay(e);
                break;
            case "GET_SHIPS_TABLERO":
                getShipsTablero(e);
                break;
            case "SHOOT_SHIPS":
                shootShips(e);
                break;
            default:
                System.out.println(LOG + " : Format Incorrected!!!");
        }
    }

    public void login(OnUserData e) {
        try {
            Map<String, String> data = ProtocolServer.formatLogin(e.getData());
            User user = findUserByNickName(data.get("NAME"));
            if (Objects.isNull(user)) {
                System.out.println(LOG + " : " + "User Not Found");
                server.send(UUID.fromString(data.get("SESSIONID")), ProtocolServer.setFormatResponse(false, Response.LOGIN, "User NotFound !!!"), TypeSend.ONE);
                return;
            }
            BCrypt.Result result = BCrypt.verifyer(BCrypt.Version.VERSION_2A).verify(data.get("PASSWORD").toCharArray(), user.getPassword());
            if (!result.verified) {
                System.out.println(LOG + " : " + "Passsowrd Incorrect !!!");
                server.send(UUID.fromString(data.get("SESSIONID")), ProtocolServer.setFormatResponse(false, Response.LOGIN, "User password incorrect !!!"), TypeSend.ONE);
                return;
            }
            countUserPlaying++;
            Tablero.tableroGame++;
            user.setSessionId(UUID.fromString(data.get("SESSIONID")));
            System.out.println(LOG + " : New User Login Connected");
            int number = userPlaying.size();
            userPlaying.put(number, user);
            if (number == 0) {
                server.send(UUID.fromString(data.get("SESSIONID")), ProtocolServer.setFormatResponse(true, Response.LOGIN, String.valueOf(number)), TypeSend.ONE);
                return;
            }
            server.send(UUID.fromString(data.get("SESSIONID")), ProtocolServer.setFormatResponse(true, Response.LOGIN, String.valueOf(number)), TypeSend.ONE);
            server.send(UUID.fromString(data.get("SESSIONID")), ProtocolServer.setFormatNewUser(), TypeSend.ALL);
        } catch (ErrorFormatException ex) {
            System.out.println(LOG + ex.getMessage());
        }
    }

    public void register(OnUserData e) {
        try {
            Map<String, String> data = ProtocolServer.formatRegister(e.getData());
            if (Objects.nonNull(findUserByNickName(data.get("NAME")))) {
                System.out.println("No se pude registrar ya existe el user con ese nombre");
                server.send(UUID.fromString(data.get("SESSIONID")), ProtocolServer.setFormatResponse(false, Response.REGISTER, "Not can register the user exists !!!"), TypeSend.ONE);
                return;
            }
            users.put(UUID.randomUUID(), new User(null, data.get("NAME"), data.get("PASSWORD"), UUID.fromString(data.get("SESSIONID"))));
            System.out.println(LOG + " : New User Register Connected");
            server.send(UUID.fromString(data.get("SESSIONID")), ProtocolServer.setFormatResponse(true, Response.REGISTER, "User successfully registered !!!"), TypeSend.ONE);
        } catch (ErrorFormatException ex) {
            System.out.println(LOG + ex.getMessage());
        }
    }

    public void data(OnUserData e) {
        try {
            Map<String, String> data = ProtocolServer.formatData(e.getData());
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

    public void sizeMatrix(OnUserData e) {
        try {
            Map<String, String> data = ProtocolServer.formatSizeMatrix(e.getData());
            User user = findUserBySessionId(UUID.fromString(data.get("SESSIONID")));
            if (Objects.isNull(user)) {
                System.out.println("SESSIONID NO VALIDO !!!");
                return;
            }
            server.send(user.getSessionId(), ProtocolServer.setFormatMatrix(Tablero.NF, Tablero.NC), TypeSend.ONE);
        } catch (ErrorFormatException ex) {
            System.out.println(LOG + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void countOnline(OnUserData e) {
        try {
            Map<String, String> data = ProtocolServer.formatCountOnline(e.getData());
            User user = findUserBySessionId(UUID.fromString(data.get("SESSIONID")));
            if (Objects.isNull(user)) {
                System.out.println("SESSIONID NO VALIDO !!!");
                return;
            }
            int count = userPlaying.size();
            server.send(user.getSessionId(), ProtocolServer.setFormatCountOnline(count), TypeSend.ONE);
        } catch (ErrorFormatException ex) {
            System.out.println(LOG + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void setTablero(OnUserData e) {
        try {
            Map<String, String> data = ProtocolServer.formatTableroSet(e.getData());
            User user = findUserBySessionId(UUID.fromString(data.get("SESSIONID")));
            if (Objects.isNull(user)) {
                System.out.println("SESSIONID NO VALIDO !!!");
                return;
            }
            Tablero tablero = user.getTablero();
            Ship ship = new Ship(1);
            tablero.set(Integer.parseInt(data.get("ROW")), Integer.parseInt(data.get("COL")), ship);
        } catch (ErrorFormatException ex) {
            System.out.println(LOG + ex.getMessage());
        }
    }

    public void confirmPlay(OnUserData e) {
        try {
            Map<String, String> data = ProtocolServer.formatConfirmPlay(e.getData());
            User user = findUserBySessionId(UUID.fromString(data.get("SESSIONID")));
            if (Objects.isNull(user)) {
                System.out.println("SESSIONID NO VALIDO !!!");
                return;
            }
            server.send(user.getSessionId(), ProtocolServer.setFormatConfirmPlay(), TypeSend.ALL);
        } catch (ErrorFormatException ex) {
            System.out.println(LOG + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void initPlay(OnUserData e) {
        try {
            Map<String, String> data = ProtocolServer.formatInitPlay(e.getData());
            User user = findUserBySessionId(UUID.fromString(data.get("SESSIONID")));
            if (Objects.isNull(user)) {
                System.out.println("SESSIONID NO VALIDO !!!");
                return;
            }
            server.send(user.getSessionId(), ProtocolServer.setFormatInitPlay(), TypeSend.ALL);
        } catch (ErrorFormatException ex) {
            System.out.println(LOG + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void getShipsTablero(OnUserData e) {
        try {
            Map<String, String> data = ProtocolServer.formatGetTablero(e.getData());
            User user = findUserBySessionId(UUID.fromString(data.get("SESSIONID")));
            if (Objects.isNull(user)) {
                System.out.println("SESSIONID NO VALIDO !!!");
                return;
            }
            server.send(user.getSessionId(), ProtocolServer.setFormatGetTablero(user.getTablero()), TypeSend.ONE);
        } catch (ErrorFormatException ex) {
            System.out.println(LOG + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void shootShips(OnUserData e) {
        try {
            Map<String, String> data = ProtocolServer.formatShootShip(e.getData());
            User user = findUserBySessionId(UUID.fromString(data.get("SESSIONID")));
            if (Objects.isNull(user)) {
                System.out.println("SESSIONID NO VALIDO !!!");
                return;
            }
            ReviewShootBoards(user, Integer.parseInt(data.get("ROW")), Integer.parseInt(data.get("COL")));
        } catch (ErrorFormatException ex) {
            System.out.println(LOG + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void ReviewShootBoards(User userCall, int row, int col) {
        boolean destroyShip = false;
        Map<Integer, User> usersShipDestroy = new HashMap<>();
        Map<Integer, User> usersGameOver = new HashMap<>();
        for (Map.Entry<Integer, User> element : userPlaying.entrySet()) {
            User user = element.getValue();
            if (!user.equals(userCall)) {
                if (user.getTablero().ShootShip(row, col)) {
                    usersShipDestroy.put(element.getKey(), user);
                    if (user.getTablero().BoardEmpty()) {
                        usersGameOver.put(element.getKey(), user);
                    }
                    destroyShip = true;
                }
            }
        }
        handlerUsersShipDestroy(usersShipDestroy, row, col);
        handlerUsersGameOver(usersGameOver);
        server.send(userCall.getSessionId(), ProtocolServer.setFormatShootShip(destroyShip), TypeSend.ONE);
        if (Tablero.tableroGame == 1) {
            server.send(userCall.getSessionId(), ProtocolServer.setFormatWinner(), TypeSend.ONE);
            return;
        }
        handlerNotifyTurnUser();
    }

    public void handlerUsersShipDestroy(Map<Integer, User> usersShipDestroy, int row, int col) {
        for (Map.Entry<Integer, User> element : usersShipDestroy.entrySet()) {
            User userShipDestroy = element.getValue();
            server.send(userShipDestroy.getSessionId(), ProtocolServer.setFormatDestroyShip(row, col), TypeSend.ONE);
        }
    }

    public void handlerUsersGameOver(Map<Integer, User> usersGameOver) {
        for (Map.Entry<Integer, User> element : usersGameOver.entrySet()) {
            User userGameOver = element.getValue();
            userPlaying.remove(element.getKey());
            server.send(userGameOver.getSessionId(), ProtocolServer.setFormatGameOver(), TypeSend.ONE);
        }
    }

    public void handlerNotifyTurnUser() {
        nextTurntPlay();
        User user = userPlaying.get(nextPlay);
        server.send(user.getSessionId(), ProtocolServer.setFormatTurnShoot(), TypeSend.ONE);
    }

    public void nextTurntPlay() {
        int i = nextPlay;
        while (true) {
            i++;
            if (i == countUserPlaying) {
                i = 0;
            }
            if (Objects.nonNull(userPlaying.get(i))) {
                nextPlay = i;
                break;
            }
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
