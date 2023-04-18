package com.eduardo.event;

import com.eduardo.tcp.server.Connection;

public class OnMessage {

    private String id;
    private String name;
    private String message;
    private Connection connection;

    public OnMessage(Connection connection, String id, String name, String message) {
        this.connection = connection;
        this.id = id;
        this.name = name;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public Connection getConnection() {
        return connection;
    }

}
