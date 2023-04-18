package com.eduardo.listener;

import com.eduardo.event.OnConnection;
import com.eduardo.event.OnMessage;
import com.eduardo.event.OnClose;

public interface ServerListener {

    public void ListenerMessage(OnMessage e);

    public void ListenerConnection(OnConnection e);

    public void ListenerClose(OnClose e);
    
}
