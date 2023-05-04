package com.eduardo.tcp.server;

import com.eduardo.event.OnConnection;
import com.eduardo.listener.ServerListener;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

public class HandlerConnection implements Runnable {

    public static String LOG = HandlerConnection.class.getName();

    private ServerSocket server;
    private List<ServerListener> listenerList;

    public HandlerConnection(ServerSocket server) {
        this.server = server;
        this.listenerList = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Socket socket = server.accept();
                launchEvent(new OnConnection(HandlerConnection.this, socket));
            }
        } catch (Exception ex) {
            System.out.println(LOG + " : " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void addServerListener(ServerListener listener) {
        listenerList.add(listener);
    }

    public void removeServerListener(ServerListener listener) {
        listenerList.remove(listener);
    }

    public void launchEvent(EventObject event) {
        for (ServerListener listener : listenerList) {
            if (event instanceof OnConnection) {
                (listener).ListenerConnection((OnConnection) event);
            }
        }
    }
}
