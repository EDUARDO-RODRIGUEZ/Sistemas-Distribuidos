package com.eduardo.tcp.server;

import com.eduardo.listener.ServerListener;
import com.eduardo.event.OnMessage;
import com.eduardo.event.OnClose;
import com.eduardo.helper.CustomString;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Connection implements Runnable {

    public static String LOG = Connection.class.getName();

    private Socket socket;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private UUID id;
    protected List<Object> listenerList;

    public Connection(Socket socket, UUID id) {
        initialize(socket, id);
    }

    private void initialize(Socket socket, UUID id) {
        this.id = id;
        this.socket = socket;
        this.listenerList = new ArrayList<>();
        try {
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.printWriter = new PrintWriter(socket.getOutputStream());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void establishNegotiation() {
        sendMessage(String.format("ID:[%s]", id.toString()));
        System.out.println(LOG + " : establishNegotiation");
    }

    public void sendMessage(String message) {
        printWriter.println(message);
        printWriter.flush();
    }

    @Override
    public void run() {
        CustomString customString = new CustomString();
        String message = "";
        try {
            while ((message = bufferedReader.readLine()) != null) {
                String id = customString.readContent(customString.readUntil(message), "[", "]");
                String name = customString.readContent(customString.readUntil(message), "[", "]");
                String data = customString.readContent(customString.readUntil(message), "[", "]");
                customString.resetPos();
                execute(new OnMessage(Connection.this, id, name, data));
            }
        } catch (IOException e) {
            execute(new OnClose(Connection.this, e.getMessage()));
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
            if (event instanceof OnMessage) {
                ((ServerListener) (listener)).ListenerMessage((OnMessage) event);
            } else if (event instanceof OnClose) {
                ((ServerListener) (listener)).ListenerClose((OnClose) event);
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public UUID getId() {
        return id;
    }

    public void close() {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (printWriter != null) {
                printWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            System.out.println(LOG + " : " + e.getMessage());
        }
    }
}
