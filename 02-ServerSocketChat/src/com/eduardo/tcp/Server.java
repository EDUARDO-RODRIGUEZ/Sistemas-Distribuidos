package com.eduardo.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static String log = Server.class.getName();
    private ServerSocket server;
    ClientManager clientManager;

    public Server() {
        this.clientManager = new ClientManager();
    }

    public void listen(int port) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        try {
            server = new ServerSocket(port);
            System.out.println(log + " : " + "Server Up");
            while (true) {
                Socket client = server.accept();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter printWriter = new PrintWriter(client.getOutputStream());
                ConnectionClient connectionClient = new ConnectionClient(client, bufferedReader, printWriter);
                connectionClient.setClientManager(clientManager);
                executor.execute(connectionClient);
            }
        } catch (IOException e) {
            System.out.println(log + " : " + e.getMessage());
        } finally {
            executor.shutdown();
            close();
        }
    }

    public void close() {
        try {
            if (server != null) {
                server.close();
            }
        } catch (IOException e) {
            System.out.println(log + " : " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.listen(8000);
    }

}
