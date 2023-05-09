package com.eduardo.client;

import com.eduardo.event.OnSocketClose;
import com.eduardo.event.OnSocketData;
import com.eduardo.listener.ListenerSocket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import java.util.Objects;

public class HandlerSession implements Runnable {

    public static String LOG = HandlerSession.class.getName();

    private Socket server;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private List<ListenerSocket> listeners;

    public HandlerSession(Socket server) {
        try {
            this.listeners = new ArrayList<>();
            this.server = server;
            this.bufferedReader = new BufferedReader(new InputStreamReader(this.server.getInputStream()));
            this.printWriter = new PrintWriter(this.server.getOutputStream());
        } catch (IOException ex) {
            System.out.println(LOG + " : " + ex.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            String data = "";
            while (Objects.nonNull(data = bufferedReader.readLine())) {
                lauchEvent(new OnSocketData(HandlerSession.this, data));
            }
        } catch (IOException ex) {
            close();
            lauchEvent(new OnSocketClose(HandlerSession.this));
        }
    }

    public void send(String data) {
        printWriter.println(data);
        printWriter.flush();
    }

    public void close() {
        try {
            if (Objects.nonNull(printWriter)) {
                printWriter.close();
            }
            if (Objects.nonNull(bufferedReader)) {
                bufferedReader.close();
            }
            if (Objects.nonNull(server)) {
                server.close();
            }
            listeners.clear();
        } catch (IOException ex) {
            System.out.println(LOG + " : " + ex.getMessage());
        }
    }

    public void addListener(ListenerSocket listener) {
        listeners.add(listener);
    }

    public void removeListener(ListenerSocket listener) {
        listeners.remove(listener);
    }

    public void lauchEvent(EventObject event) {
        for (ListenerSocket listener : listeners) {
            if (event instanceof OnSocketData) {
                listener.listenerData((OnSocketData) event);
            } else if (event instanceof OnSocketClose) {
                listener.listenerClose((OnSocketClose) event);
            }
        }
    }

}
