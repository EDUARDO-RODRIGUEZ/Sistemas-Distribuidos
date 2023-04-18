package com.eduardo.helper;

public class CustomString {

    private int pos;

    public CustomString() {
        this.pos = 0;
    }

    public void resetPos() {
        pos = 0;
    }

    public String readUntil(String cadena) {
        String line = "";
        String cadenafilter = cadena.substring(pos);
        int distance = cadena.length() - cadenafilter.length();
        int salto = cadenafilter.indexOf("&");
        if (salto == -1) {
            line = cadena.substring(pos);
            pos = 0;
            return line;
        }
        int posfinal = distance + salto;
        line = cadena.substring(pos, posfinal);
        pos = posfinal + 1;
        return line;
    }

    public String readContent(String cadena, String start, String end) {
        return cadena.substring(cadena.indexOf(start) + 1, cadena.indexOf(end));
    }
}
