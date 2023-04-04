package com.eduardo.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static String LOG = Cliente.class.getName();

    private String name;
    private Socket server;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;

    public Cliente(Socket server, BufferedReader bufferedReader, PrintWriter printWriter, String name) {
        this.server = server;
        this.bufferedReader = bufferedReader;
        this.printWriter = printWriter;
        this.name = name;
    }

    public void OnMenssage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (server.isConnected()) {
                    try {
                        String message = bufferedReader.readLine();
                        System.out.println(message);
                    } catch (IOException e) {
                        System.out.println(LOG + " : " + e.getCause().getMessage());
                        break;
                    }
                }
            }
        }).start();
    }

    public void SendMessage() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String message = scanner.nextLine();
            if (message.contains("close")) {
                break;
            }
            printWriter.println(this.name + ": " + message);
            printWriter.flush();
        }
    }

    public static void main(String[] args) {
        System.out.print("Agregue su nombre: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println("----------------------------------------------------");
        try {
            Socket server = new Socket("127.0.0.1", 8000);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(server.getInputStream()));
            PrintWriter bufferedWriter = new PrintWriter(server.getOutputStream());
            Cliente cliente = new Cliente(server, bufferedReader, bufferedWriter, name);
            cliente.OnMenssage();
            cliente.SendMessage();
        } catch (IOException e) {
            System.out.println("Error Cliente :" + e.getMessage());
        }
    }
}
