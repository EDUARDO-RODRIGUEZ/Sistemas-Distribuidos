package com.eduardo.event;

import com.eduardo.tcp.server.Session;
import java.util.EventObject;

public class OnClose extends EventObject {

    private Session session;
    private String message;

    public OnClose(Object source, Session session, String message) {
        super(source);
        this.session = session;
        this.message = message;
    }

    public Session getSession() {
        return session;
    }

    public String getMessage() {
        return message;
    }

}
