package com.eduardo.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {

        Socket server = null;
        BufferedReader bufferedReader = null;
        PrintWriter printWriter = null;
        try {
            server = new Socket("127.0.0.1", 8000);
            bufferedReader = new BufferedReader(new InputStreamReader(server.getInputStream()));
            printWriter = new PrintWriter(new OutputStreamWriter(server.getOutputStream()));
            System.out.println(bufferedReader.readLine());
            while (true) {
                System.out.println("Ingresa tu mensaje");
                Scanner scanner = new Scanner(System.in);
                String mensaje = scanner.nextLine();
                if (mensaje.contains("close")) {
                    break;
                }
                printWriter.println(mensaje);
                printWriter.flush();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            close(server, printWriter, bufferedReader);
        }
    }

    public static void close(Socket socket, PrintWriter printWriter, BufferedReader bufferedReader) {
        try {
            if (printWriter != null) {
                printWriter.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
