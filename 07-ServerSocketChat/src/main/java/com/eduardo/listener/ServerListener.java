package com.eduardo.listener;

import com.eduardo.event.OnConnection;
import com.eduardo.event.OnMessage;
import com.eduardo.event.OnClose;

import java.util.EventListener;

public interface ServerListener extends EventListener {

    public void ListenerMessage(OnMessage e);

    public void ListenerConnection(OnConnection e);

    public void ListenerClose(OnClose e);

}
