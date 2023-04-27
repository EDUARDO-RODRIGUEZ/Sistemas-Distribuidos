package com.eduardo.tcp.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.eduardo.listener.ServerListener;
import com.eduardo.event.OnConnection;
import com.eduardo.event.OnMessage;
import com.eduardo.event.OnClose;
import com.eduardo.event.OnUserConnected;
import com.eduardo.event.OnUserData;
import com.eduardo.listener.ServerListenerUser;
import java.util.ArrayList;
import java.util.List;

public class Server implements ServerListener {

    public static String LOG = Server.class.getName();

    private ServerSocket server;
    private int port;
    private Map<UUID, Session> sessions;
    private ExecutorService executor;
    private HandlerConnection handlerConnection;
    private Ping ping;
    private List<Object> listenerList;

    public Server() {
        this.sessions = new ConcurrentHashMap<>();
        this.port = 8000;
        this.executor = Executors.newFixedThreadPool(20);
        this.listenerList = new ArrayList<>();
    }

    public void listen(int port) {
        this.port = port;
        this.listen();
    }

    public void listen() {
        ping = new Ping(sessions);
        ping.addServerListener(this);
        executor.execute(ping);
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
        launchEvent(new OnUserData(e.getSession(), e.getMessage()));
    }

    @Override
    public void ListenerConnection(OnConnection e) {
        Session session = new Session(e.getSocket(), UUID.randomUUID());
        sessions.put(session.getId(), session);
        session.addServerListener(this);
        executor.execute(session);
        send(session, String.format("ID[%s]&", session.getId()));
        launchEvent(new OnUserConnected(session));
    }

    @Override
    public void ListenerClose(OnClose e) {
        Session session = e.getSession();
        session.close();
        sessions.remove(session.getId());
        System.out.println(LOG + " : Closed Session  " + session.getSocket().getInetAddress() + ":" + session.getSocket().getPort());
        System.out.println(LOG + " : " + e.getMessage());
    }

    public void send(Session session, String data) {
        executor.execute(new TaskMessage(session, data));
    }

    public void sendAll(Session session, String data) {
        executor.execute(new TaskMessage(session, data, sessions));
    }

    public void addServerListener(ServerListenerUser listener) {
        listenerList.add(listener);
    }

    public void removeServerListener(ServerListenerUser listener) {
        listenerList.remove(listener);
    }

    public void launchEvent(Object event) {
        for (Object listener : listenerList) {
            if (event instanceof OnUserConnected) {
                ((ServerListenerUser) (listener)).ListenerUserConnected((OnUserConnected) event);
            } else if (event instanceof OnUserData) {
                ((ServerListenerUser) (listener)).ListenerUserData((OnUserData) event);
            }
        }
    }
}
