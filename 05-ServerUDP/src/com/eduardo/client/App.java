package com.eduardo.client;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

public class App {

    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        Client client = new Client();
        client.start();
    }
}
