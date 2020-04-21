package org.example.gameoflife;

import java.util.Arrays;

public class Grid {

    final boolean[][] rows;

    public Grid(final Grid grid) {
        this.rows = Arrays.stream(grid.rows)
                .map(boolean[]::clone)
                .toArray(boolean[][]::new);
    }

    public Grid(final int width, final int height) {
        this.rows = new boolean[height][width];
    }

    public int getWidth() {
        return this.rows.length == 0 ? 0 : this.rows[0].length;
    }

    public int getHeight() {
        return this.rows.length;
    }

    public void setCellAlive(final Coordinate coordinate) {
        this.setCell(coordinate, true);
    }

    public void setCell(final Coordinate coordinate, final boolean alive) {
        if (coordinate.isIn(this)) {
            this.rows[coordinate.getY()][coordinate.getX()] = alive;
        }
    }

    public boolean isCellAlive(final Coordinate coordinate) {
        return coordinate.isIn(this) && this.rows[coordinate.getY()][coordinate.getX()];
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getHeight()).append(' ').append(this.getWidth());
        for (final boolean[] row : this.rows) {
            stringBuilder.append('\n');
            for (final boolean cell : row) {
                stringBuilder.append(cell ? '*' : '.');
            }
        }
        return stringBuilder.toString();
    }

}
