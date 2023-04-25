package com.eduardo.server;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class Ping implements Runnable {

    public static String LOG = Ping.class.getName();
    private Map<UUID, Socket> sockets;

    public Ping(Map<UUID, Socket> sockets) {
        this.sockets = sockets;
    }

    @Override
    public void run() {
        while (true) {
            Iterator<Map.Entry<UUID, Socket>> iterator = sockets.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<UUID, Socket> element = iterator.next();
                Socket socket = element.getValue();
                UUID key = element.getKey();
                try {
                    if (!socket.getAddress().isReachable(3000)) {
                        sockets.remove(key);
                        System.out.println(LOG + "Client lost signal");
                    }
                } catch (IOException e) {
                    System.out.println(LOG + " : " + e.getMessage());
                }
            }
        }
    }

}
