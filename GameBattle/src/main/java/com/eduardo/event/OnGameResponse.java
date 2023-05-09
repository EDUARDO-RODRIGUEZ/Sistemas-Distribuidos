package com.eduardo.event;

import java.util.EventObject;

public class OnGameResponse extends EventObject {

    private boolean ok;
    private String response;
    private String message;

    public OnGameResponse(Object source, boolean ok, String response, String message) {
        super(source);
        this.ok = ok;
        this.response = response;
        this.message = message;
    }

    public String getResponse() {
        return response;
    }

    public String getMessage() {
        return message;
    }

    public boolean getOK() {
        return ok;
    }
}
