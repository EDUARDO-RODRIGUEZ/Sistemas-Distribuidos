package com.eduardo.event;

import java.net.DatagramPacket;

public class OnMessage {

    private DatagramPacket datagramPacket;

    public OnMessage(DatagramPacket datagramPacket) {
        this.datagramPacket = datagramPacket;
    }

    public DatagramPacket getDatagramPacket() {
        return datagramPacket;
    }

}
