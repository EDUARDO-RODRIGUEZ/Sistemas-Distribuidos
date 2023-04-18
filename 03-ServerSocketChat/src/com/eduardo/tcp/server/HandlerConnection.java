package com.eduardo.tcp.server;

import com.eduardo.event.OnConnection;
import com.eduardo.listener.ServerListener;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class HandlerConnection implements Runnable {

    public static String LOG = HandlerConnection.class.getName();
    private ServerSocket server;
    protected List<Object> listenerList;

    public HandlerConnection(ServerSocket server) {
        this.server = server;
        this.listenerList = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Socket client = server.accept();
                execute(new OnConnection(client));
            }
        } catch (Exception e) {
            System.out.println(LOG + " : " + e.getMessage());
        }
    }

    public void addServerListener(ServerListener listener) {
        listenerList.add(listener);
    }

    public void removeServerListener(ServerListener listener) {
        listenerList.remove(listener);
    }

    public void execute(Object event) {
        for (Object listener : listenerList) {
            if (event instanceof OnConnection) {
                ((ServerListener) (listener)).ListenerConnection((OnConnection) event);
            }
        }
    }
}
