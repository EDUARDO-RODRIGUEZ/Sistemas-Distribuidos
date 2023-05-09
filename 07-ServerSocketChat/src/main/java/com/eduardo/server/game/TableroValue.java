package com.eduardo.server.game;

public enum TableroValue {
    EMPTY(0), ELEMENT(1);

    private int value;

    private TableroValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
