package com.eduardo.tcp.server;

import com.eduardo.listener.ServerListener;
import com.eduardo.event.OnMessage;
import com.eduardo.event.OnClose;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.EventObject;
import java.util.UUID;
import javax.swing.event.EventListenerList;

public class Connection implements Runnable {

    public static String LOG = Connection.class.getName();
    private Socket socket;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private UUID id;
    protected EventListenerList listenerList = new EventListenerList();

    public Connection(Socket socket, UUID id) {
        initialize(socket, id);
    }

    public void initialize(Socket socket, UUID id) {
        this.id = id;
        this.socket = socket;
        try {
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.printWriter = new PrintWriter(socket.getOutputStream());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void sendMessage(String message) {
        printWriter.println(message);
        printWriter.flush();
    }

    @Override
    public void run() {
        String message = "";
        try {
            while ((message = bufferedReader.readLine()) != null) {
                execute(new OnMessage(Connection.this, message));
            }
        } catch (IOException e) {
            execute(new OnClose(Connection.this, e.getMessage()));
        }
    }

    public void addServerListener(ServerListener listener) {
        listenerList.add(ServerListener.class, listener);
    }

    public void removeServerListener(ServerListener listener) {
        listenerList.remove(ServerListener.class, listener);
    }

    public void execute(EventObject e) {
        Object[] listeners = listenerList.getListenerList();
        if (e instanceof OnMessage) {
            for (int i = 0; i < listeners.length; i = i + 2) {
                if (listeners[i] == ServerListener.class) {
                    ((ServerListener) listeners[i + 1]).ListenerMessage((OnMessage) e);
                }
            }
        } else if (e instanceof OnClose) {
            for (int i = 0; i < listeners.length; i = i + 2) {
                if (listeners[i] == ServerListener.class) {
                    ((ServerListener) listeners[i + 1]).ListenerClose((OnClose) e);
                }
            }
        }
    }

    public UUID getId() {
        return id;
    }

    public Socket getSocket() {
        return socket;
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
