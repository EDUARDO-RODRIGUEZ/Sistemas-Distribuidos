package com.eduardo.event;

import com.eduardo.tcp.server.Connection;

public class OnClose {

    private Connection connection;
    private String message;

    public OnClose(Connection connection, String message) {
        this.connection = connection;
        this.message = message;
    }

    public Connection getConnection() {
        return connection;
    }

    public String getMessage() {
        return message;
    }

}
