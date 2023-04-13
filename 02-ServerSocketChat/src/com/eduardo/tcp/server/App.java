package com.eduardo.tcp.server;

public class App {

    public static void main(String[] args) {
        Server server = new Server();
        server.listen("127.0.0.1", 8000);
    }

}
