package org.example.gameoflife;

import java.util.Random;

public class Grid {

    private static final Random RANDOM = new Random();

    public static Grid fromRandomSize(final int widthMin, final int widthMax, final int heightMin, final int heightMax) {
        return new Grid(
                widthMin + RANDOM.nextInt(widthMax - widthMin),
                heightMin + RANDOM.nextInt(heightMax - heightMin));
    }

    private final String[][] rows;

    public Grid() {
        this(0, 0);
    }

    public Grid(final int width, final int height) {
        this.rows = new String[height][width];
    }

    public boolean isEmpty() {
        return true;
    }

}
