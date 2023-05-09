package com.eduardo.listener;

import com.eduardo.event.OnSocketClose;
import com.eduardo.event.OnSocketConnection;
import com.eduardo.event.OnSocketData;
import java.util.EventListener;

public interface ListenerSocket extends EventListener {

    public void listenerClose(OnSocketClose e);

    public void listenerConnection(OnSocketConnection e);

    public void listenerData(OnSocketData e);

}
