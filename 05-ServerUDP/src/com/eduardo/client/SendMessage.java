package com.eduardo.client;

import com.eduardo.helper.Protocol;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class SendMessage implements Runnable {

    public static String LOG = SendMessage.class.getName();
    private DatagramSocket datagramSocket;
    private String id;

    public SendMessage(DatagramSocket datagramSocket, String id) {
        this.datagramSocket = datagramSocket;
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine().trim();
                if (input.contains("close")) {
                    String formatClose = Protocol.setFormatClose(id);
                    byte[] data = formatClose.getBytes();
                    DatagramPacket datagrama = new DatagramPacket(data, data.length, InetAddress.getByName("127.0.0.1"), 8000);
                    datagramSocket.send(datagrama);
                    System.exit(0);
                }
                String messageFormat = Protocol.setFormatMessage(id, input);
                byte[] data = messageFormat.getBytes();
                DatagramPacket datagrama = new DatagramPacket(data, data.length, InetAddress.getByName("127.0.0.1"), 8000);
                datagramSocket.send(datagrama);
            } catch (Exception e) {
                System.out.println(LOG + " : " + e.getMessage());
            }
        }
    }
}
