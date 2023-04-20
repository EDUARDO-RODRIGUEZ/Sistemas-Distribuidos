package com.eduardo.tcp.client;

public class App {
    public static void main(String[] args) throws InterruptedException {
        Cliente cliente = new Cliente();
        cliente.establishNegotiation();
        cliente.OnMenssage();
        cliente.SendMessage();
    }
}
