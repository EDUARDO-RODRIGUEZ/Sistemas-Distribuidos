package com.eduardo.server.game;

public class App {

    public static void main(String[] args) {
        ServerGame serverGame = new ServerGame(8000);
        serverGame.run();
    }

}
