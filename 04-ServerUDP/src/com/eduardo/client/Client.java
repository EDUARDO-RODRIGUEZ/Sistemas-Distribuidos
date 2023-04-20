package com.eduardo.client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    private static String LOG = Client.class.getName();
    private DatagramSocket datagramSocket;
    private DatagramPacket datagramPacket;

    public Client() throws SocketException {
        datagramSocket = new DatagramSocket();
    }

    public void OnMenssage() {
        Thread thread = new Thread(() -> {
            while (true) {
                byte[] buffer = new byte[100];
                datagramPacket = new DatagramPacket(buffer, buffer.length);
                try {
                    datagramSocket.receive(datagramPacket);
                    System.out.println(new String(datagramPacket.getData()));
                } catch (IOException e) {
                    System.out.println(LOG + " : " + e.getMessage());
                }
            }
        });
        thread.start();
    }

    public void sendMessage() {
        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    Scanner scanner = new Scanner(System.in);
                    String mensaje = scanner.nextLine();
                    if (mensaje.contains("quit")) {
                        break;
                    }
                    byte[] data = mensaje.getBytes();
                    DatagramPacket datagrama = new DatagramPacket(data, data.length, InetAddress.getByName("127.0.0.1"), 8000);
                    datagramSocket.send(datagrama);
                }
            } catch (SocketException | UnknownHostException e) {
                System.out.println(LOG + " : " + e.getMessage());
            } catch (IOException e) {
                System.out.println(LOG + " : " + e.getMessage());
            } finally {
                datagramSocket.close();
            }
        });
        thread.start();
    }

}
