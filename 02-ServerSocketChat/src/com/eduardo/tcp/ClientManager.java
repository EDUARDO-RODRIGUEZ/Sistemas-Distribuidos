package com.eduardo.tcp;

import java.util.ArrayList;
import java.util.List;

public class ClientManager {

    private List<ConnectionClient> clients;

    public ClientManager() {
        clients = new ArrayList<ConnectionClient>();
    }

    public int getClients() {
        return clients.size();
    }

    public void add(ConnectionClient connectionClient) {
        clients.add(connectionClient);
    }

    public void remove(ConnectionClient connectionClient) {
        clients.remove(connectionClient);
    }

    public void broadcast(String message, ConnectionClient client) {
        for (ConnectionClient connectionClient : clients) {
            if (connectionClient != client) {
                connectionClient.sendMessage(message);
            }
        }
    }

}
