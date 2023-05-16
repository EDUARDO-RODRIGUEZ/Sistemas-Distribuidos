package com.eduardo.server.game;

import java.util.Objects;

public class Tablero {

    public static int tableroGame = 0;
    public static int NF = 6;
    public static int NC = 6;

    private Ship[][] data;

    public Tablero() {
        this.data = new Ship[NF][NC];
        tableroGame++;
    }

    public void set(int fila, int columna, Ship ship) {
        if ((fila >= 0 && fila < NF) && (columna >= 0 && columna < NC)) {
            data[fila][columna] = ship;
        }
    }

    public void show() {
        for (int f = 0; f < NF; f++) {
            System.out.print("[");
            for (int c = 0; c < NC; c++) {
                if (Objects.isNull(data[f][c])) {
                    System.out.print("n");
                } else {
                    System.out.print(data[f][c].getResistance());
                }
            }
            System.out.println("]");
        }
    }
}
