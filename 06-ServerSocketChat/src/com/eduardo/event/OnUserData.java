package com.eduardo.event;

import com.eduardo.tcp.server.Session;

public class OnUserData {

    private Session session;
    private String data;

    public OnUserData(Session session, String data) {
        this.data = data;
        this.session = session;
    }

    public String getData() {
        return data;
    }

    public Session getSession() {
        return session;
    }

}
