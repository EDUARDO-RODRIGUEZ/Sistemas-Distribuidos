package com.eduardo.tcp.server;

import com.eduardo.listener.ServerListener;
import com.eduardo.event.OnConnection;
import com.eduardo.event.OnMessage;
import com.eduardo.event.OnClose;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements ServerListener {

    public static String LOG = Server.class.getName();

    private ServerSocket server;
    private String host;
    private int port;
    private Map<UUID, Connection> connections;
    private ExecutorService executor;

    public Server() {
        this.connections = new HashMap<>();
        this.host = "127.0.0.1";
        this.port = 8000;
        this.executor = Executors.newFixedThreadPool(10);
    }

    public void listen(String host, int port) {
        this.host = host;
        this.port = port;
        this.listen();
    }

    public void listen() {
        System.out.println(LOG + " : " + "Server listen " + host + ":" + port);
        try {
            server = new ServerSocket(port, 50, InetAddress.getByName(host));
            HandlerConnection handlerConnection = new HandlerConnection(server);
            handlerConnection.addServerListener(this);
            executor.execute(handlerConnection);
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
        Connection eventConnection = (Connection) e.getSource();
        connections.forEach((uuid, connection) -> {
            if (uuid != eventConnection.getId()) {
                connection.sendMessage(e.getMessage());
            }
        });
    }

    @Override
    public void ListenerConnection(OnConnection e) {
        Socket socket = e.getSocket();
        Connection connection = new Connection(socket, UUID.randomUUID());
        connection.addServerListener(this);
        connections.put(connection.getId(), connection);
        System.out.println(LOG + " : " + "New Connected Socket " + socket.getInetAddress() + ":" + socket.getPort());
        executor.execute(connection);
    }

    @Override
    public void ListenerClose(OnClose e) {
        Connection connection = e.getConnection();
        Socket socket = connection.getSocket();
        connection.close();
        connections.remove(connection.getId());
        System.out.println(LOG + " : " + "Closed Connected Socket " + socket.getInetAddress() + ":" + socket.getPort());
    }
}
