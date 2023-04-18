package com.eduardo.event;

import java.net.Socket;

public class OnConnection {

    private Socket socket;

    public OnConnection(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

}
