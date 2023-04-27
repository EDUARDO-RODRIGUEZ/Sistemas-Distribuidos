package com.eduardo.event;

import com.eduardo.tcp.server.Session;

public class OnUserConnected {

    private Session session;

    public OnUserConnected(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

}
