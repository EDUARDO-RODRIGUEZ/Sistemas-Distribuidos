package com.eduardo.client;

import com.eduardo.helper.CustomString;
import com.eduardo.helper.Protocol;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Client {

    private static String LOG = Client.class.getName();

    private String name;
    private String id;
    private DatagramSocket datagramSocket;
    private HandlerMessage handlerMessage;
    private SendMessage sendMessage;
    private Executor executor;

    public Client() {
        init();
    }

    public void init() {
        this.executor = Executors.newFixedThreadPool(2);
        try {
            datagramSocket = new DatagramSocket();
        } catch (SocketException e) {
            System.out.println(LOG + " : " + e.getMessage());
        }
    }

    public void start() {
        MessageInitial();
        OnMenssage();
        SendMessage();
    }

    public void MessageInitial() {
        CustomString cs = new CustomString();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese su nombre: ");
        name = scanner.nextLine().trim();
        String data = Protocol.setFormatInit(name);
        try {
            DatagramPacket datagramInitial = new DatagramPacket(data.getBytes(), data.getBytes().length, InetAddress.getByName("localhost"), 8000);
            datagramSocket.send(datagramInitial);
            byte[] buffer = new byte[100];
            DatagramPacket datagramReceived = new DatagramPacket(buffer, buffer.length);
            datagramSocket.receive(datagramReceived);
            data = new String(datagramReceived.getData());
            id = cs.readContent(cs.readUntil(data, "&"), "[", "]");
            System.out.println("ID: " + id);
            System.out.println("-----------------------------------");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void OnMenssage() {
        handlerMessage = new HandlerMessage(datagramSocket);
        executor.execute(handlerMessage);
    }

    public void SendMessage() {
        sendMessage = new SendMessage(datagramSocket, id);
        executor.execute(sendMessage);
    }

}
