package com.eduardo.server;

import com.eduardo.client.Socket;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private static String LOG = Server.class.getName();

    private DatagramSocket datagramSocket;
    private DatagramPacket datagramPacket;
    private List<Socket> connections;

    public Server() {
        this.connections = new ArrayList<>();
    }

    public void listen() {
        System.out.println("Server Up");
        try {
            datagramSocket = new DatagramSocket(8000, InetAddress.getByName("localhost"));
            while (true) {
                byte[] buffer = new byte[100];
                datagramPacket = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(datagramPacket);
                Socket socket = new Socket(datagramPacket.getAddress(), datagramPacket.getPort());
                add(socket);
                byte[] message = new byte[100];
                message = new String(datagramPacket.getData()).getBytes();
                sendAll(message, socket);
            }
        } catch (SocketException | UnknownHostException e) {
            System.out.println(LOG + " : " + e.getMessage());
        } catch (IOException e) {
            System.out.println(LOG + " : " + e.getMessage());
        } finally {
            datagramSocket.close();
        }
    }

    private void add(Socket socket) {
        boolean existe = false;
        for (Socket connection : connections) {
            if (connection.isEqual(socket)) {
                existe = true;
                break;
            }
        }
        if (!existe) {
            connections.add(socket);
        }
    }

    public void sendAll(byte[] message, Socket socket) {
        for (Socket connection : connections) {
            if (!connection.isEqual(socket)) {
                try {
                    DatagramPacket datagrama = new DatagramPacket(
                            message,
                            message.length,
                            connection.getAddress(),
                            connection.getPort()
                    );
                    datagramSocket.send(datagrama);
                } catch (Exception e) {
                    System.out.println(LOG + " : " + e.getMessage());
                }
            }
        }
    }
}
