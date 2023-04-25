package com.eduardo.server;

import com.eduardo.event.OnMessage;
import com.eduardo.helper.Protocol;
import com.eduardo.listener.ListenerMessage;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Server implements ListenerMessage {

    private static String LOG = Server.class.getName();

    private DatagramSocket datagramSocket;
    private Map<UUID, Socket> clients;
    private HandlerMessage handlerMessage;
    private Ping ping;
    private Executor executor;
    private int port;

    public Server() {
        this.clients = new ConcurrentHashMap<>();
        this.executor = Executors.newFixedThreadPool(50);
        this.port = 8000;
    }

    public void listen(int port) {
        this.port = port;
        this.listen();
    }

    public void listen() {
        ping = new Ping(clients);
        executor.execute(ping);
        try {
            datagramSocket = new DatagramSocket(port, InetAddress.getByName("localhost"));
            handlerMessage = new HandlerMessage(datagramSocket);
            handlerMessage.addListener(this);
            executor.execute(handlerMessage);
            System.out.println(LOG + " : Server Up");
        } catch (Exception e) {
            System.out.println(LOG + " : " + e.getMessage());
        }
    }

    @Override
    public void listenerMessage(OnMessage e) {
        DatagramPacket datagramReceived = e.getDatagramPacket();
        String data = new String(datagramReceived.getData());
        switch (Protocol.getFormat(data)) {
            case "init":
                init(datagramReceived);
                break;
            case "message":
                message(datagramReceived);
                break;
            case "close":
                close(datagramReceived);
                break;
            default:
                System.out.println(LOG + " : Format Incorrected !!!");
        }
    }

    public void init(DatagramPacket datagramReceived) {
        String data = new String(datagramReceived.getData());
        Map<String, String> map = Protocol.formatInit(data);
        if (map.isEmpty()) {
            System.out.println(LOG + " : Format Init Incorrected !!!");
            return;
        }
        Socket socket = new Socket(datagramReceived.getAddress(), datagramReceived.getPort());
        socket.setId(UUID.randomUUID());
        socket.setName(map.get("NAME"));
        addSocket(socket);
        String messageSend = String.format("ID[%s]&", String.valueOf(socket.getId()));
        send(messageSend.getBytes(), socket);
    }

    public void message(DatagramPacket datagramReceived) {
        String data = new String(datagramReceived.getData());
        Map<String, String> map = Protocol.formatMessage(data);
        if (map.isEmpty()) {
            System.out.println(LOG + " : " + "Format Messages Incorrected !!!");
            return;
        }
        if (!clients.containsKey(UUID.fromString(map.get("ID")))) {
            System.out.println(LOG + " : " + "Socket not in List !!!");
            return;
        }
        Socket socket = clients.get(UUID.fromString(map.get("ID")));
        String message = socket.getName() + ": " + map.get("DATA");
        sendAll(message.getBytes(), socket);
    }

    public void close(DatagramPacket datagramReceived) {
        String data = new String(datagramReceived.getData());
        Map<String, String> map = Protocol.formatClose(data);
        if (map.isEmpty()) {
            System.out.println(LOG + " : " + "Format Close Incorrected !!!");
            return;
        }
        UUID uuid = UUID.fromString(map.get("ID"));
        if (!clients.containsKey(uuid)) {
            System.out.println(LOG + " : " + "Could not close socket not found !!!");
            return;
        }
        clients.remove(uuid);
        System.out.println("Client disconected !!!");
    }

    public void send(byte[] message, Socket socket) {
        try {
            DatagramPacket datagramSend = new DatagramPacket(message, message.length, socket.getAddress(), socket.getPort());
            datagramSocket.send(datagramSend);
        } catch (Exception e) {
            System.out.println(LOG + " : " + e.getMessage());
        }
    }

    public void sendAll(byte[] message, Socket socket) {
        for (Map.Entry<UUID, Socket> element : clients.entrySet()) {
            if (!element.getKey().equals(socket.getId())) {
                send(message, element.getValue());
            }
        }
    }

    private void addSocket(Socket socket) {
        if (!exist(socket)) {
            clients.put(socket.getId(), socket);
        }
    }

    private boolean exist(Socket socket) {
        for (Map.Entry<UUID, Socket> element : clients.entrySet()) {
            if (element.getValue().isEqual(socket)) {
                return true;
            }
        }
        return false;
    }

}
