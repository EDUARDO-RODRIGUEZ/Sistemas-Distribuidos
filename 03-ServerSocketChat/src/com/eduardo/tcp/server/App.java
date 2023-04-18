package com.eduardo.tcp.server;

public class App {

    public static void main(String[] args) {
        Server server = new Server();
        server.listen(Integer.parseInt(args[0]));
    }

}
