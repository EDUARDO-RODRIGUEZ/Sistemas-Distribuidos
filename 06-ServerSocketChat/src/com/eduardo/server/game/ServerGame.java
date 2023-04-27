package com.eduardo.server.game;

import com.eduardo.entity.User;
import com.eduardo.event.OnUserConnected;
import com.eduardo.event.OnUserData;
import com.eduardo.helper.Protocol;
import com.eduardo.tcp.server.Server;
import com.eduardo.listener.ServerListenerUser;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ServerGame implements ServerListenerUser {

    public static String LOG = ServerGame.class.getName();
    private Map<UUID, User> users;
    private Server server;
    private int port;

    public ServerGame(int port) {
        this.server = new Server();
        this.server.addServerListener(this);
        this.users = new HashMap<>();
        this.port = port;
    }

    public void run() {
        server.listen(this.port);
    }

    @Override
    public void ListenerUserConnected(OnUserConnected e) {
        server.send(e.getSession(), "&LOGIN[]&");
    }

    @Override
    public void ListenerUserData(OnUserData e) {
        switch (Protocol.getFormat(e.getData())) {
            case "LOGIN":
                login(e);
                break;
            case "DATA":
                data(e);
                break;
            default:
                System.out.println(LOG + " : Format Incorrected!!!");
        }
    }

    public void login(OnUserData e) {
        Map<String, String> data = Protocol.formatLogin(e.getData());
        if (data.isEmpty()) {
            System.out.println(LOG + " : Format Login Incorrected!!!");
            return;
        }
        users.put(e.getSession().getId(), new User(data.get("NAME"), data.get("PASSWORD")));
        System.out.println(LOG + " : New User Connected");
    }

    public void data(OnUserData e) {
        Map<String, String> data = Protocol.formatData(e.getData());
        if (data.isEmpty()) {
            System.out.println(LOG + " : Format Data Incorrected!!!");
            return;
        }
        User user = users.get(UUID.fromString(data.get("ID")));
        server.sendAll(e.getSession(), user.getName() + " : " + data.get("DATA"));
    }

}
