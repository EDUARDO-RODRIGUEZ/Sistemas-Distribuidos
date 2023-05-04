package com.eduardo.tcp.client;

import com.eduardo.helper.Protocol;
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
    private String password;
    private Socket server;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private String ModeConnection;

    public Cliente() {
        initialize();
    }

    public void initialize() {
        System.out.println("MODO DE CONEXION");
        System.out.println("---------------------------");
        System.out.println("(1): LOGIN");
        System.out.println("(2): REGISTER");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.trim().equals("1")) {
                ModeConnection = "LOGIN";
                break;
            }
            if (input.trim().equals("2")) {
                ModeConnection = "REGISTER";
                break;
            }
            System.out.println("OPCION NO VALIDA");
        }
        System.out.println("---------------------------");
        System.out.println("INGRESE SU NOMBRE:");
        name = scanner.nextLine().trim();
        System.out.println("INGRESE SU PASSWORD:");
        password = scanner.nextLine().trim();
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
            String authenticated = bufferedReader.readLine();
            if (this.ModeConnection.equals("LOGIN")) {
                printWriter.println(Protocol.setFormatLogin(id, name, password));
                printWriter.flush();
            } else {
                printWriter.println(Protocol.setFormatRegister(id, name, password));
                printWriter.flush();
            }
            System.out.println("ID: " + id);
            System.out.println("---------------------------");
        } catch (IOException e) {
            System.out.println(LOG + " : " + e.getMessage());
        }
    }

    public void OnMenssage() {
        Thread thread = new Thread(() -> {
            String message = "";
            try {
                while ((message = bufferedReader.readLine()) != null) {
                    System.out.println(message);
                }
            } catch (IOException e) {
                System.out.println(LOG + " : " + e.getMessage());
            }
        });
        thread.start();
    }

    public void SendMessage() {
        Thread thread = new Thread(() -> {
            while (true) {
                Scanner scanner = new Scanner(System.in);
                String message = scanner.nextLine();
                if (message.contains("close")) {
                    close();
                    System.exit(0);
                }
                printWriter.println(Protocol.setFormatData(id, message));
                printWriter.flush();
            }
        });
        thread.start();
    }

    public void close() {
        try {
            if (server != null) {
                server.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (printWriter != null) {
                printWriter.close();
            }
        } catch (IOException e) {
            System.out.println(LOG + " : " + e.getMessage());
        }
    }

}
