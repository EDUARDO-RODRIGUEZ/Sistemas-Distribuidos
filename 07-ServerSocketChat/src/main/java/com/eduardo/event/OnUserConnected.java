package com.eduardo.event;

import java.util.EventObject;
import java.util.UUID;

public class OnUserConnected extends EventObject {

    private UUID sessionId;

    public OnUserConnected(Object source, UUID sessionId) {
        super(source);
        this.sessionId = sessionId;
    }

    public UUID getSessionId() {
        return sessionId;
    }

}
