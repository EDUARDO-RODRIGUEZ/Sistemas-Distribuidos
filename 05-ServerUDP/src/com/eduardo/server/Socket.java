package com.eduardo.server;

import java.net.InetAddress;
import java.util.UUID;

public class Socket {

    private int port;
    private InetAddress address;
    private UUID id;
    private String name;

    public Socket(InetAddress address, int port) {
        this.port = port;
        this.address = address;
    }

    public boolean isEqual(Socket socket) {
        return ((this.getAddress().equals(socket.getAddress())) && (this.port == socket.port));
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
