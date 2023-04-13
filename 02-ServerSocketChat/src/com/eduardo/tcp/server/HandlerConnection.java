package com.eduardo.tcp.server;

import com.eduardo.event.OnConnection;
import com.eduardo.listener.ServerListener;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.event.EventListenerList;

public class HandlerConnection implements Runnable {

    public static String LOG = HandlerConnection.class.getName();
    private ServerSocket server;
    protected EventListenerList listenerList = new EventListenerList();

    public HandlerConnection(ServerSocket server) {
        this.server = server;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Socket client = server.accept();
                execute(new OnConnection(HandlerConnection.this, client));
            }
        } catch (Exception e) {
            System.out.println(LOG + " : " + e.getMessage());
        }
    }

    public void addServerListener(ServerListener listener) {
        listenerList.add(ServerListener.class, listener);
    }

    public void removeServerListener(ServerListener listener) {
        listenerList.remove(ServerListener.class, listener);
    }

    public void execute(OnConnection e) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = 0; i < listeners.length; i = i + 2) {
            if (listeners[i] == ServerListener.class) {
                ((ServerListener) listeners[i + 1]).ListenerConnection(e);
            }
        }
    }
}
