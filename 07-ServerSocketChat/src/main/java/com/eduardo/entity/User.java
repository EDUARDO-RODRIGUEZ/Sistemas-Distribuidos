package com.eduardo.entity;

import com.eduardo.server.game.Tablero;
import java.util.UUID;

public class User {

    private Integer id;
    private String name;
    private String password;
    private UUID sessionId;
    private Tablero tablero;

    public User(Integer id, String name, String password) {
        this(id, name, password, null);
    }

    public User(Integer id, String name, String password, UUID sessionId) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.sessionId = sessionId;
        this.tablero = new Tablero();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UUID getSessionId() {
        return sessionId;
    }

    public void setSessionId(UUID sessionId) {
        this.sessionId = sessionId;
    }

    public Tablero getTablero() {
        return tablero;
    }

}
