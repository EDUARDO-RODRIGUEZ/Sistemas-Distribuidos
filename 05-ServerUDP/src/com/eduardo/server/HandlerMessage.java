package com.eduardo.server;

import com.eduardo.event.OnMessage;
import com.eduardo.listener.ListenerMessage;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HandlerMessage implements Runnable {

    public static String LOG = HandlerMessage.class.getName();

    private List<Object> listeners;
    private DatagramSocket datagramSocket;
    private byte[] buffer;

    public HandlerMessage(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
        this.listeners = new ArrayList<>();
        this.buffer = new byte[100];
    }

    @Override
    public void run() {
        try {
            while (true) {
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(datagramPacket);
                execute(new OnMessage(datagramPacket));
                Arrays.fill(buffer, (byte) 0);
            }
        } catch (IOException e) {
            System.out.println(LOG + " : " + e.getMessage());
        }
    }

    public void addListener(ListenerMessage listener) {
        this.listeners.add(listener);
    }

    public void removeListener(ListenerMessage listener) {
        this.listeners.remove(listener);
    }

    public void execute(Object event) {
        for (Object listener : listeners) {
            if (event instanceof OnMessage) {
                ((ListenerMessage) (listener)).listenerMessage((OnMessage) event);
            }
        }
    }

}
