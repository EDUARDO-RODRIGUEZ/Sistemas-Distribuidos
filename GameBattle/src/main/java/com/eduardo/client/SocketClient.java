package com.eduardo.client;

import com.eduardo.event.OnGameClose;
import com.eduardo.event.OnGameConnection;
import com.eduardo.event.OnGameData;
import com.eduardo.event.OnGameResponse;
import com.eduardo.event.OnSocketClose;
import com.eduardo.event.OnSocketConnection;
import com.eduardo.event.OnSocketData;
import com.eduardo.helper.ErrorFormatException;
import com.eduardo.helper.Protocol;
import com.eduardo.listener.ListenerGame;
import com.eduardo.listener.ListenerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SocketClient implements ListenerSocket {

    private Socket server;
    private Executor executor;
    private HandlerConnection handlerConnection;
    private HandlerSession handlerSession;
    private List<ListenerGame> listeners;
    private UUID sessionId;

    public SocketClient() {
        this.executor = Executors.newFixedThreadPool(5);
        this.listeners = new ArrayList<>();
    }

    public void connect() {
        handlerConnection = new HandlerConnection(8000);
        handlerConnection.addListener(this);
        executor.execute(handlerConnection);
    }

    @Override
    public void listenerConnection(OnSocketConnection e) {
        this.server = e.getSocket();
        handlerSession = new HandlerSession(server);
        handlerSession.addListener(this);
        executor.execute(handlerSession);
    }

    @Override
    public void listenerData(OnSocketData e) {
        switch (Protocol.getFormat(e.getData())) {
            case "ID":
                handlerSaveId(e);
                break;
            case "AUTHENTICATED":
                handlerAuthenticate();
                break;
            case "DATA":
                handlerData(e);
                break;
            case "RESPONSE":
                handlerResponse(e);
                break;
            default:
                System.out.println("Format Incorrecto!!!");
        }
    }

    @Override
    public void listenerClose(OnSocketClose e) {
        connect();
    }

    public void handlerSaveId(OnSocketData e) {
        try {
            Map<String, String> map = Protocol.formatID(e.getData());
            sessionId = UUID.fromString(map.get("SESSIONID"));
        } catch (ErrorFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void handlerAuthenticate() {
        lauchEvent(new OnGameConnection(SocketClient.this));
    }

    public void handlerData(OnSocketData e) {
        try {
            Map<String, String> map = Protocol.formatData(e.getData());
            lauchEvent(new OnGameData(map.get("DATA")));
        } catch (ErrorFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void handlerResponse(OnSocketData e) {
        try {
            Map<String, String> map = Protocol.formatResponse(e.getData());
            lauchEvent(new OnGameResponse(SocketClient.this, Boolean.valueOf(map.get("OK")), map.get("RESPONSE"), map.get("MESSAGE")));
        } catch (ErrorFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void send(String data) {
        handlerSession.send(data);
    }

    public UUID getSessionId() {
        return sessionId;
    }

    public void addListener(ListenerGame listener) {
        listeners.add(listener);
    }

    public void removeListener(ListenerGame listener) {
        listeners.remove(listener);
    }

    public void lauchEvent(EventObject event) {
        for (ListenerGame listener : listeners) {
            if (event instanceof OnGameConnection) {
                listener.listenerGameConnection((OnGameConnection) event);
            } else if (event instanceof OnGameData) {
                listener.listenerGameData((OnGameData) event);
            } else if (event instanceof OnGameClose) {
                listener.listenerGameClose((OnGameClose) event);
            } else if (event instanceof OnGameResponse) {
                listener.listenerGameResponse((OnGameResponse) event);
            }
        }
    }
}
