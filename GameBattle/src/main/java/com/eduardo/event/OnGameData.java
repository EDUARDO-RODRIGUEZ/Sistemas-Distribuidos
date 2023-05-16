package com.eduardo.event;

import java.util.EventObject;

public class OnGameData extends EventObject {

    private String data;

    public OnGameData(Object source, String data) {
        super(source);
        this.data = data;
    }

    public String getData() {
        return data;
    }

}
