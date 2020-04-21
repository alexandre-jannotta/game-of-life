package org.example.gameoflife;

import java.util.List;

public class GameOfLife {

    public static final java.util.List<Coordinate> NEIGHBOUR_OFFSETS = List.of(
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
        return NEIGHBOUR_OFFSETS.stream()
                .map(coordinate::move)
                .filter(grid::isCellAlive)
                .count() >= 2;
    }

}
