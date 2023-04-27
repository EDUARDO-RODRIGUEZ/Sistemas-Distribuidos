package com.eduardo.event;

import com.eduardo.tcp.server.Session;

public class OnMessage {

    private String message;
    private Session session;

    public OnMessage(Session session, String message) {
        this.session = session;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Session getSession() {
        return session;
    }

}
