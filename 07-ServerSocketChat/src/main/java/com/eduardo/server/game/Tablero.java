package com.eduardo.server.game;

public class Tablero {

    private int[][] data;
    private int nf;
    private int nc;

    public Tablero(int nf, int nc) {
        this.nf = nf;
        this.nc = nc;
        this.data = new int[nf][nc];
    }

    public void set(int fila, int columna, TableroValue value) {
        for (int f = 0; f < nf; f++) {
            for (int c = 0; c < nc; c++) {
                if (f == fila && c == columna) {
                    data[f][c] = value.getValue();
                    break;
                }
            }
        }
    }

    public void clear(TableroValue valor) {
        for (int f = 0; f < nf; f++) {
            for (int c = 0; c < nc; c++) {
                data[f][c] = valor.getValue();
            }
        }
    }

    public void show() {
        for (int f = 0; f < nf; f++) {
            System.out.print("[");
            for (int c = 0; c < nc; c++) {
                System.out.print(" " + data[f][c] + " ");
            }
            System.out.println("]");
        }
    }

}
