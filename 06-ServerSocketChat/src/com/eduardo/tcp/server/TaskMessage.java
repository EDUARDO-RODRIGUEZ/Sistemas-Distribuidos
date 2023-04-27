package com.eduardo.tcp.server;

import java.util.Map;
import java.util.UUID;

public class TaskMessage implements Runnable {

    private String message;
    private Session session;
    private TypeSend typeSend;
    private Map<UUID, Session> sessions;

    public TaskMessage(Session session, String message) {
        this.message = message;
        this.session = session;
        this.typeSend = TypeSend.ONE;
    }

    public TaskMessage(Session session, String message, Map<UUID, Session> sessions) {
        this.message = message;
        this.session = session;
        this.typeSend = TypeSend.ALL;
        this.sessions = sessions;
    }

    public void send() {
        session.sendMessage(message);
    }

    public void sendAll() {
        for (Map.Entry<UUID, Session> element : sessions.entrySet()) {
            if (!element.getValue().getId().equals(session.getId())) {
                element.getValue().sendMessage(message);
            }
        }
    }

    @Override
    public void run() {
        switch (typeSend) {
            case ALL:
                sendAll();
                break;
            case ONE:
                send();
                break;
        }
    }

}
