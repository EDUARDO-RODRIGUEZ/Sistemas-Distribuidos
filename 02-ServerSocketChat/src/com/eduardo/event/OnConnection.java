package com.eduardo.event;

import java.net.Socket;
import java.util.EventObject;

public class OnConnection extends EventObject {

    private Socket socket;
    
    public OnConnection(Object source, Socket socket) {
        super(source);
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }
    

}
