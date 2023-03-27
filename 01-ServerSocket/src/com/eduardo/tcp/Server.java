package com.eduardo.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws InterruptedException {
        try {
            ServerSocket server = new ServerSocket(8000, 10);
            int clients = 0;
            System.out.println("Server Up");
            while (clients < 3) {
                Socket cliente = server.accept();
                clients++;
                System.out.println("Server: Request " + clients);
                System.out.println("Server: Cliente Aceptado!!");
                new Thread(new GestorClient(cliente)).start();
            }
            System.out.println("Server Down");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

class GestorClient implements Runnable {

    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private Socket socketClient;

    public GestorClient(Socket socket) {
        this.socketClient = socket;
    }

    @Override
    public void run() {
        try {
            printWriter = new PrintWriter(new OutputStreamWriter(socketClient.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            sendMessage(printWriter, "Bienvenido !!!");
            OnMessage(socketClient, bufferedReader);
        } catch (IOException ex) {
            System.out.println("Client Disconnect !!! :" + ex.getMessage());
        } finally {
            close();
        }
    }

    public void sendMessage(PrintWriter printWriter, String message) {
        printWriter.println(message);
        printWriter.flush();
    }

    public void OnMessage(Socket socket, BufferedReader bufferedReader) throws IOException {
        String message = "";
        while ((message = bufferedReader.readLine()) != null) {
            System.out.println("Cliente: " + message);
        }
    }

    public void close() {
        try {
            if (printWriter != null) {
                printWriter.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (socketClient != null) {
                socketClient.close();
            }
        } catch (IOException e) {
            System.out.println("Error Close Connection : " + e.getMessage());
        }
    }

}
