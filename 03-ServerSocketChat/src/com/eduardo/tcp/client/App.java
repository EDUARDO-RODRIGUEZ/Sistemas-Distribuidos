package com.eduardo.tcp.client;

public class App {

    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        cliente.establishNegotiation();
        cliente.OnMenssage();
        cliente.SendMessage();
    }
}
