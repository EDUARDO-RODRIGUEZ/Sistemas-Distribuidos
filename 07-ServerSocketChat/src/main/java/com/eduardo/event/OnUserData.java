package com.eduardo.event;

import java.util.EventObject;

public class OnUserData extends EventObject {

    private String data;

    public OnUserData(Object source, String data) {
        super(source);
        this.data = data;
    }

    public String getData() {
        return data;
    }

}
