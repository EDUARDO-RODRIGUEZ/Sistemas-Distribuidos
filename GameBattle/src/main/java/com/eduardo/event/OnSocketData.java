package com.eduardo.event;

import java.util.EventObject;

public class OnSocketData extends EventObject {

    private String data;

    public OnSocketData(Object source, String data) {
        super(source);
        this.data = data;
    }

    public String getData() {
        return data;
    }

}
