package com.eduardo.tcp.server;

import java.util.Map;
import java.util.UUID;

public class TaskMessage implements Runnable {

    private String message;
    private Session session;
    private TypeSend typeSend;
    private Map<UUID, Session> sessions;

    public TaskMessage(Map<UUID, Session> sessions) {
        this.sessions = sessions;
    }

    public void config(Session session, String message, TypeSend typeSend) {
        this.session = session;
        this.message = message;
        this.typeSend = typeSend;
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
