package com.eduardo.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {

        try {
            System.out.println("Client UDP");
            DatagramSocket socket = new DatagramSocket();
            while (true) {
                System.out.println("Ingrese su mensaje");
                Scanner scanner = new Scanner(System.in);
                String mensaje = scanner.nextLine();
                if(mensaje.contains("quit")) break;
                byte[] data=mensaje.getBytes();
                DatagramPacket datagrama = new DatagramPacket(data, data.length, InetAddress.getByName("127.0.0.1"), 8000);
                socket.send(datagrama);
            }
            socket.close();
        } catch (SocketException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
