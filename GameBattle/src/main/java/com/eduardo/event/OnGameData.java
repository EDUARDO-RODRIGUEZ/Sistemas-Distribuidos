package com.eduardo.event;

import java.util.EventObject;

public class OnGameData extends EventObject {

    private String data;

    public OnGameData(Object source) {
        super(source);
    }

    public String getData() {
        return data;
    }

}
