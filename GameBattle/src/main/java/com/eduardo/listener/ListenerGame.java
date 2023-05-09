package com.eduardo.listener;

import com.eduardo.event.OnGameClose;
import com.eduardo.event.OnGameConnection;
import com.eduardo.event.OnGameData;
import com.eduardo.event.OnGameResponse;
import java.util.EventListener;

public interface ListenerGame extends EventListener {

    public void listenerGameConnection(OnGameConnection e);

    public void listenerGameClose(OnGameClose e);

    public void listenerGameData(OnGameData e);
    
    public void listenerGameResponse(OnGameResponse e);

}
