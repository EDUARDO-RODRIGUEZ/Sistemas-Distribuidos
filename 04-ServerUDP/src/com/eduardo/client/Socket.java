package com.eduardo.client;

import java.net.InetAddress;

public class Socket {

    private int port;
    private InetAddress address;

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

}
