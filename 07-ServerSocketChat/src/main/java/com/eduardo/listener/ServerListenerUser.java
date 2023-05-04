package com.eduardo.listener;

import com.eduardo.event.OnUserConnected;
import com.eduardo.event.OnUserData;

import java.util.EventListener;

public interface ServerListenerUser extends EventListener {

    public void ListenerUserConnected(OnUserConnected e);

    public void ListenerUserData(OnUserData e);

}
