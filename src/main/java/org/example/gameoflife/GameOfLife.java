package org.example.gameoflife;

import java.util.List;

public class GameOfLife {

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

    boolean survive(final Grid grid, final Coordinate coordinate) {
        final long count = NEIGHBOUR_OFFSETS.stream()
                .map(coordinate::move)
                .filter(grid::isCellAlive)
                .count();
        return grid.isCellAlive(coordinate) && count >= 2 && count <= 3 || count == 3;
    }

    Grid computeNextGeneration(final Grid grid) {
        final Grid nextGenerationGrid = new Grid(grid);
        final Coordinate.CoordinateBuilder coordinateBuilder = Coordinate.builder();
        for (int rowIndex = 0; rowIndex < grid.getHeight(); rowIndex++) {
            coordinateBuilder.y(rowIndex);
            for (int columnIndex = 0; columnIndex < grid.getWidth(); columnIndex++) {
                final Coordinate coordinate = coordinateBuilder.x(columnIndex).build();
                nextGenerationGrid.setCell(coordinate, survive(grid, coordinate));
            }
        }
        return nextGenerationGrid;
    }
    
}
