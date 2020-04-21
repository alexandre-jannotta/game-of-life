package org.example.gameoflife;

public class Grid {

    public static Grid from(final String s) {
        return new Grid();
    }

    private Grid() {
    }

    public boolean isEmpty() {
        return true;
    }
}
