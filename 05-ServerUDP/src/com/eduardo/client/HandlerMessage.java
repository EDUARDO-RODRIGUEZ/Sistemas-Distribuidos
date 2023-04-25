package com.eduardo.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

public class HandlerMessage implements Runnable {

    public static String LOG = HandlerMessage.class.getName();

    private DatagramSocket datagramSocket;
    private byte[] bufferReader;

    public HandlerMessage(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
        this.bufferReader = new byte[100];
    }

    @Override
    public void run() {
        while (true) {
            DatagramPacket datagramPacket = new DatagramPacket(bufferReader, bufferReader.length);
            try {
                datagramSocket.receive(datagramPacket);
                System.out.println(new String(datagramPacket.getData()));
                Arrays.fill(bufferReader, (byte) 0);
            } catch (IOException e) {
                System.out.println(LOG + " : " + e.getMessage());
            }
        }
    }

}
