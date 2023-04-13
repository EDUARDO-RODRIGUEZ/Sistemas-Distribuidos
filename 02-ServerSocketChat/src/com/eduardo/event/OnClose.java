package com.eduardo.event;

import com.eduardo.tcp.server.Connection;
import java.util.EventObject;

public class OnClose extends EventObject {

    private Connection connection;
    private String message;

    public OnClose(Object source, String message) {
        super(source);
        this.connection = (Connection) source;
        this.message = message;
    }

    public Connection getConnection() {
        return connection;
    }

    public String getMessage() {
        return message;
    }

}
