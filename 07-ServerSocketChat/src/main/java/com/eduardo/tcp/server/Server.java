package com.eduardo.tcp.server;

import com.eduardo.listener.ServerListener;
import com.eduardo.event.OnConnection;
import com.eduardo.event.OnMessage;
import com.eduardo.event.OnClose;
import com.eduardo.event.OnUserConnected;
import com.eduardo.event.OnUserData;
import com.eduardo.helper.Protocol;
import com.eduardo.listener.ServerListenerUser;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.ArrayList;
import java.util.List;
import java.net.UnknownHostException;
import java.util.EventObject;
import java.util.concurrent.Future;

public class Server implements ServerListener {

    public static String LOG = Server.class.getName();

    private ServerSocket server;
    private int port;
    private Map<UUID, Session> sessions;
    private ExecutorService executor;
    private HandlerConnection handlerConnection;
    private Ping ping;
    private List<ServerListenerUser> listenerList;
    private TaskMessage task;

    public Server(int port) {
        try {
            this.port = port;
            this.sessions = new ConcurrentHashMap<>();
            this.executor = Executors.newFixedThreadPool(20);
            this.listenerList = new ArrayList<>();
            server = new ServerSocket(this.port);
            task = new TaskMessage(this.sessions);
        } catch (IOException ex) {
            close();
            System.out.println(LOG + " : " + ex.getMessage());
        }
    }

    public Server() {
        this(8000);
    }

    public void listen() {
        ping = new Ping(sessions);
        ping.addServerListener(this);
        executor.execute(ping);
        try {
            handlerConnection = new HandlerConnection(server);
            handlerConnection.addServerListener(this);
            executor.execute(handlerConnection);
            System.out.println(LOG + " : " + "Server listen " + server.getInetAddress().getLocalHost() + ":" + server.getLocalPort());
        } catch (UnknownHostException ex) {
            System.out.println(LOG + " : " + ex.getMessage());
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
        launchEvent(new OnUserData(Server.this, e.getMessage()));
    }

    @Override
    public void ListenerConnection(OnConnection e) {
        Session session = new Session(e.getSocket(), UUID.randomUUID());
        sessions.put(session.getId(), session);
        session.addServerListener(this);
        executor.execute(session);
        System.out.println(LOG + " : New Session Connected");
        send(session.getId(), Protocol.setFormatId(String.valueOf(session.getId())), TypeSend.ONE);
        launchEvent(new OnUserConnected(Server.this, session.getId()));
    }

    @Override
    public void ListenerClose(OnClose e) {
        Session session = e.getSession();
        session.close();
        sessions.remove(session.getId());
        System.out.println(LOG + " : Closed Session  " + session.getSocket().getInetAddress() + ":" + session.getSocket().getPort());
        System.out.println(LOG + " : " + e.getMessage());
    }

    public synchronized void send(UUID uuid, String data, TypeSend typeSend) {
        Session session = sessions.get(uuid);
        task.config(session, data, typeSend);
        Future<?> submit = executor.submit(task);
        while (!submit.isDone());
    }

    public void addServerListener(ServerListenerUser listener) {
        listenerList.add(listener);
    }

    public void removeServerListener(ServerListenerUser listener) {
        listenerList.remove(listener);
    }

    public void launchEvent(EventObject event) {
        for (ServerListenerUser listener : listenerList) {
            if (event instanceof OnUserConnected) {
                listener.ListenerUserConnected((OnUserConnected) event);
            } else if (event instanceof OnUserData) {
                listener.ListenerUserData((OnUserData) event);
            }
        }
    }
}
