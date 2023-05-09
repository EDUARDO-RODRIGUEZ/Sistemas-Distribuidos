package com.eduardo.client;

import com.eduardo.event.OnSocketConnection;
import com.eduardo.listener.ListenerSocket;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import java.util.Objects;

public class HandlerConnection implements Runnable {

    public static String LOG = HandlerConnection.class.getName();

    private Socket server;
    private int port;
    private String host;
    private List<ListenerSocket> listeners;

    public HandlerConnection(int port) {
        this("127.0.0.1", port);
    }

    public HandlerConnection(String host, int port) {
        this.host = host;
        this.port = port;
        this.listeners = new ArrayList<>();
    }

    @Override
    public void run() {
        while (Objects.isNull(server)) {
            try {
                server = new Socket(InetAddress.getByName(host), port);
            } catch (IOException ex) {
                System.out.println(LOG + " : " + ex.getMessage());
            }
        }
        lauchEvent(new OnSocketConnection(HandlerConnection.this, server));
    }

    public void addListener(ListenerSocket listener) {
        listeners.add(listener);
    }

    public void removeListener(ListenerSocket listener) {
        listeners.remove(listener);
    }

    public void lauchEvent(EventObject event) {
        for (ListenerSocket listener : listeners) {
            if (event instanceof OnSocketConnection) {
                listener.listenerConnection((OnSocketConnection) event);
            }
        }
    }

}
