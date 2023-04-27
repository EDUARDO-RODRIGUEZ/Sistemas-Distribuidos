package com.eduardo.listener;

import com.eduardo.event.OnUserConnected;
import com.eduardo.event.OnUserData;

public interface ServerListenerUser {

    public void ListenerUserConnected(OnUserConnected e);

    public void ListenerUserData(OnUserData e);
    
}
