package com.eduardo.tcp.server;

import com.eduardo.listener.ServerListener;
import com.eduardo.event.OnConnection;
import com.eduardo.event.OnMessage;
import com.eduardo.event.OnClose;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements ServerListener {

    public static String LOG = Server.class.getName();

    private ServerSocket server;
    private int port;
    private Map<UUID, Connection> connections;
    private ExecutorService executor;
    private HandlerConnection handlerConnection;

    public Server() {
        this.connections = new HashMap<>();
        this.port = 8000;
        this.executor = Executors.newFixedThreadPool(10);
    }

    public void listen(int port) {
        this.port = port;
        this.listen();
    }

    public void listen() {
        pingConnections();
        try {
            server = new ServerSocket(port);
            handlerConnection = new HandlerConnection(server);
            handlerConnection.addServerListener(this);
            executor.execute(handlerConnection);
            System.out.println(LOG + " : " + "Server listen " + server.getInetAddress().getLocalHost() + ":" + server.getLocalPort());
        } catch (IOException e) {
            close();
            System.out.println(LOG + " : " + e.getMessage());
        }
    }

    public void close() {
        executor.shutdown();
        try {
            if (server != null) {
                server.close();
            }
        } catch (IOException e) {
            System.out.println(LOG + " : " + e.getMessage());
        }
    }

    @Override
    public void ListenerMessage(OnMessage e) {
        sendAll(e.getId(), e.getName() + ": " + e.getMessage());
    }

    @Override
    public void ListenerConnection(OnConnection e) {
        Thread thread = new Thread(() -> {
            Connection connection = new Connection(e.getSocket(), UUID.randomUUID());
            if (connection.establishNegotiation()) {
                connection.addServerListener(this);
                connections.put(connection.getId(), connection);
                executor.execute(connection);
                System.out.println(LOG + " : " + "New Connected Socket " + e.getSocket().getInetAddress() + ":" + e.getSocket().getPort());
                sendAll(connection.getId().toString(), "->" + connection.getName() + " se ha conectado");
            } else {
                connection.close();
            }
        });
        executor.execute(thread);
    }

    @Override
    public void ListenerClose(OnClose e) {
        Connection connection = e.getConnection();
        connections.remove(connection.getId());
        System.out.println(LOG + " : " + "Closed Connected Socket " + connection.getSocket().getInetAddress() + ":" + connection.getSocket().getPort());
        sendAll(connection.getId().toString(), "<-" + connection.getName() + " se ha desconectado");
    }

    public void sendAll(String id, String message) {
        UUID idfilter = UUID.fromString(id);
        connections.forEach((uuid, connection) -> {
            if (!idfilter.equals(uuid)) {
                connection.sendMessage(message);
            }
        });
    }

    public void pingConnections() {
        Thread ping = new Thread(() -> {
            while (true) {
                Iterator<Map.Entry<UUID, Connection>> iterator = connections.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<UUID, Connection> element = iterator.next();
                    try {
                        if (!(element.getValue().getSocket().getInetAddress().isReachable(3000))) {
                            ListenerClose(new OnClose(element.getValue(), "Conexion Perdida !!!"));
                            System.out.println("Conexion Perdidad");
                        }
                    } catch (IOException e) {
                        System.out.println(LOG + " : " + e.getMessage());
                    }
                }
            }
        });
        executor.execute(ping);
    }

}
