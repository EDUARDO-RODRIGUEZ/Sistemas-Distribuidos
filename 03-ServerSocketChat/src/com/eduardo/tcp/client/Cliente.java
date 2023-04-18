package com.eduardo.tcp.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static String LOG = Cliente.class.getName();

    private String id;
    private String name;
    private Socket server;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;

    public Cliente() {
        initialize();
    }

    public void initialize() {
        Scanner s = new Scanner(System.in);
        System.out.print("Ingrese su nombre:");
        name = s.nextLine().trim();
        try {
            server = new Socket("localhost", 8000);
            bufferedReader = new BufferedReader(new InputStreamReader(server.getInputStream()));
            printWriter = new PrintWriter(server.getOutputStream());
        } catch (IOException e) {
            System.out.println(LOG + " : " + e.getMessage());
        }
    }

    public void establishNegotiation() {
        try {
            String lineId = bufferedReader.readLine();
            id = lineId.substring(lineId.indexOf("[") + 1, lineId.indexOf("]"));
        } catch (IOException e) {
            System.out.println(LOG + " : " + e.getMessage());
        }
    }

    public void OnMenssage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String message = "";
                try {
                    while ((message = bufferedReader.readLine()) != null) {
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    System.out.println(LOG + " : " + e.getMessage());
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
            printWriter.println(String.format(""
                    + "ID[%s]&"
                    + "NAME[%s]&"
                    + "DATA[%s]",
                    id, name, message.trim())
            );
            printWriter.flush();
        }
    }
}
