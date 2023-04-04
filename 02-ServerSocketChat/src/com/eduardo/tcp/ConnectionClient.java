package com.eduardo.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionClient implements Runnable {

    public static String LOG = ConnectionClient.class.getName();

    private Socket socket;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private ClientManager clientManager;

    public ConnectionClient(Socket socket, BufferedReader br, PrintWriter pw) {
        this.socket = socket;
        this.bufferedReader = br;
        this.printWriter = pw;
    }

    public void setClientManager(ClientManager clientManager) {
        this.clientManager = clientManager;
    }

    public void OnMessage() {
        clientManager.add(ConnectionClient.this);
        System.out.println(LOG + " : " + "New Client Connected");
        System.out.println(LOG + " : " + "Clientes conectados :" + clientManager.getClients());
        while (socket.isConnected()) {
            try {
                String message = bufferedReader.readLine();
                clientManager.broadcast(message, ConnectionClient.this);
            } catch (IOException ex) {
                System.out.println(LOG + " : " + ex.getMessage());
                close();
                break;
            }
        }
        clientManager.remove(ConnectionClient.this);
        System.out.println(LOG + " : " + "Client Disconnected");
        System.out.println(LOG + " : " + "Clientes conectados :" + clientManager.getClients());
    }

    public void sendMessage(String message) {
        printWriter.println(message);
        printWriter.flush();
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
        } catch (IOException ex) {
            System.out.println(LOG + " : " + ex.getCause().getMessage());
        }
    }

    @Override
    public void run() {
        OnMessage();
    }
}
