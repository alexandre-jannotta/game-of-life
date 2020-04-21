package org.example.gameoflife;

import org.example.gameoflife.Coordinate.CoordinateBuilder;

import java.util.Arrays;
import java.util.List;

public class Grid {

    public static final List<Coordinate> NEIGHBOUR_OFFSETS = List.of(
            Coordinate.builder().x(-1).y(-1).build(),
            Coordinate.builder().x(-1).y(0).build(),
            Coordinate.builder().x(-1).y(1).build(),
            Coordinate.builder().x(0).y(-1).build(),
            Coordinate.builder().x(0).y(1).build(),
            Coordinate.builder().x(1).y(-1).build(),
            Coordinate.builder().x(1).y(0).build(),
            Coordinate.builder().x(1).y(1).build()
    );

    private final boolean[][] rows;

    public static Grid parse(final String gridString) {
        final String[] rows = gridString.split("\n");
        final String[] size = rows[0].split(" ");
        final Grid grid = new Grid(Integer.parseInt(size[1]), Integer.parseInt(size[0]));
        for (int rowIndex = 1; rowIndex < rows.length; rowIndex++) {
            final char[] row = rows[rowIndex].toCharArray();
            for (int columnIndex = 0; columnIndex < row.length; columnIndex++) {
                if (row[columnIndex] == '*') {
                    grid.setCellAlive(Coordinate.builder().x(columnIndex).y(rowIndex - 1).build());
                }
            }
        }
        return grid;
    }

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

    public boolean isCellSurviving(final Coordinate coordinate) {
        final long count = NEIGHBOUR_OFFSETS.stream()
                .map(coordinate::move)
                .filter(this::isCellAlive)
                .count();
        return this.isCellAlive(coordinate) && count >= 2 && count <= 3 || count == 3;
    }

    public Grid nextGeneration() {
        final Grid nextGenerationGrid = new Grid(this);
        final CoordinateBuilder coordinateBuilder = Coordinate.builder();
        for (int rowIndex = 0; rowIndex < this.getHeight(); rowIndex++) {
            coordinateBuilder.y(rowIndex);
            for (int columnIndex = 0; columnIndex < this.getWidth(); columnIndex++) {
                final Coordinate coordinate = coordinateBuilder.x(columnIndex).build();
                nextGenerationGrid.setCell(coordinate, this.isCellSurviving(coordinate));
            }
        }
        return nextGenerationGrid;
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
