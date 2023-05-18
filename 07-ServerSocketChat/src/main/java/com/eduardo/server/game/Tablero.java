package com.eduardo.server.game;

import java.util.Objects;

public class Tablero {

    public static int tableroGame = 0;
    public static int NF = 3;
    public static int NC = 3;

    private Ship[][] data;

    public Tablero() {
        this.data = new Ship[NF][NC];
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

    public Ship[][] getData() {
        return data;
    }

    public boolean ShootShip(int row, int col) {
        if (Objects.nonNull(data[row][col])) {
            data[row][col] = null;
            return true;
        }
        return false;
    }

    public boolean BoardEmpty() {
        for (int f = 0; f < NF; f++) {
            for (int c = 0; c < NC; c++) {
                if (Objects.nonNull(data[f][c])) {
                    return false;
                }
            }
        }
        tableroGame--;
        return true;
    }

}
