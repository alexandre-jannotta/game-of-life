package org.example.gameoflife;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

class GameOfLifeTest {

    private static final Random RANDOM = new Random();

    GameOfLife test;

    @BeforeEach
    void init() {
        this.test = new GameOfLife();
    }

    @RepeatedTest(10)
    void surviveWithFewerThanTwoNeighbours() {
        final Grid grid = this.newRandomGrid(5, 10, 5, 10);
        final Coordinate coordinate = this.newRandomCoordinate(grid);

        // Alone
        grid.setCellAlive(coordinate);
        assertThat(this.test.survive(grid, coordinate)).isFalse();

        // with 1 neighbour
        grid.setCellAlive(coordinate.move(GameOfLife.NEIGHBOUR_OFFSETS.get(0)));
        assertThat(this.test.survive(grid, coordinate)).isFalse();

        // with 2 neighbour
        grid.setCellAlive(coordinate.move(GameOfLife.NEIGHBOUR_OFFSETS.get(1)));
        assertThat(this.test.survive(grid, coordinate)).isTrue();
    }

    Grid newRandomGrid(final int widthMin, final int widthMax, final int heightMin, final int heightMax) {
        return new Grid(
                widthMin + RANDOM.nextInt(widthMax - widthMin),
                heightMin + RANDOM.nextInt(heightMax - heightMin));
    }

    Coordinate newRandomCoordinate(final Grid grid) {
        return Coordinate.builder()
                .x(RANDOM.nextInt(grid.getWidth()))
                .y(RANDOM.nextInt(grid.getHeight()))
                .build();
    }

}