package com.eduardo.event;

import java.util.EventObject;

public class OnMessage extends EventObject {

    private String message;

    public OnMessage(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
