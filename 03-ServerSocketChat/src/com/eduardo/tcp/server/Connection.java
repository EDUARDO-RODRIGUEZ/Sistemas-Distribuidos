package com.eduardo.tcp.server;

import com.eduardo.listener.ServerListener;
import com.eduardo.event.OnMessage;
import com.eduardo.event.OnClose;
import com.eduardo.helper.Protocol;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Connection implements Runnable {

    public static String LOG = Connection.class.getName();

    private Socket socket;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private UUID id;
    private String name;
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

    public boolean establishNegotiation() {
        sendMessage(String.format("ID:[%s]", id.toString()));
        try {
            String message = bufferedReader.readLine();
            Map<String, String> data = Protocol.formatConexion(message);
            if (data.isEmpty()) {
                return false;
            }
            this.name = data.get("NAME");
            return true;
        } catch (IOException e) {
            System.out.println(LOG + " : " + e.getMessage());
            return false;
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
                Map<String, String> data = Protocol.formatMessage(message);
                if (!data.isEmpty()) {
                    execute(new OnMessage(Connection.this, data.get("ID"), name, data.get("DATA")));
                }
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
    
    public String getName() {
        return name;
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
