package com.eduardo.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Server {

    public static void main(String[] args) {

        try {
            System.out.println("Server UDP");
            DatagramSocket socket = new DatagramSocket(8000, InetAddress.getByName("127.0.0.1"));
            int request = 0;
            while (request < 3) {
                byte[] buffer = new byte[100];
                DatagramPacket datagrama = new DatagramPacket(buffer, buffer.length);
                socket.receive(datagrama);
                request++;
                System.out.println("Request " + request + " : data " + new String(datagrama.getData()));
                System.out.println("Request " + request + " : ip   " + datagrama.getAddress());
                System.out.println("Request " + request + " : port " + datagrama.getPort());
            }
            socket.close();
        } catch (UnknownHostException | SocketException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}
