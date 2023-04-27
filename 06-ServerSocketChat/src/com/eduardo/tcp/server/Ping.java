package com.eduardo.tcp.server;

import com.eduardo.event.OnClose;
import com.eduardo.listener.ServerListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Ping implements Runnable {

    public static String LOG = Ping.class.getName();

    private Map<UUID, Session> connections;
    protected List<Object> listenerList;

    public Ping(Map<UUID, Session> connections) {
        this.connections = connections;
        this.listenerList = new ArrayList<>();
    }

    @Override
    public void run() {
        while (true) {
            Iterator<Map.Entry<UUID, Session>> iterator = connections.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<UUID, Session> element = iterator.next();
                try {
                    if (!(element.getValue().getSocket().getInetAddress().isReachable(5000))) {
                        execute(new OnClose(element.getValue(), "Conexion Perdida !!!"));
                    }
                } catch (IOException e) {
                    System.out.println(LOG + " : " + e.getMessage());
                }
            }
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
            if (event instanceof OnClose) {
                ((ServerListener) (listener)).ListenerClose((OnClose) event);
            }
        }
    }

}
