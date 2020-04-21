package org.example.gameoflife;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.gameoflife.Grid.NEIGHBOUR_OFFSETS;

class GridTest {

    private static final Random RANDOM = new Random();

    @RepeatedTest(10)
    void anAliveCellDieWithFewerThanTwoNeighbours() {
        final Grid grid = newRandomGrid();
        final Coordinate coordinate = newRandomCoordinateNotOnEdge(grid);
        final List<Coordinate> neighbours = randomNeighboursOf(coordinate);

        grid.setCellAlive(coordinate);

        assertThat(grid.cellSurvive(coordinate)).isFalse(); // 0
        grid.setCellAlive(neighbours.remove(0));
        assertThat(grid.cellSurvive(coordinate)).isFalse(); // 1
    }

    @RepeatedTest(10)
    void anAliveCellSurviveWithTweOrThreeNeighbours() {
        final Grid grid = newRandomGrid();
        final Coordinate coordinate = newRandomCoordinateNotOnEdge(grid);
        final List<Coordinate> neighbours = randomNeighboursOf(coordinate);

        grid.setCellAlive(coordinate);
        grid.setCellAlive(neighbours.remove(0));

        grid.setCellAlive(neighbours.remove(0));
        assertThat(grid.cellSurvive(coordinate)).isTrue(); // 2
        grid.setCellAlive(neighbours.remove(0));
        assertThat(grid.cellSurvive(coordinate)).isTrue(); // 3
    }

    @RepeatedTest(10)
    void anAliveCellDieWithMoreThanThreeNeighbours() {
        final Grid grid = newRandomGrid();
        final Coordinate coordinate = newRandomCoordinateNotOnEdge(grid);
        final List<Coordinate> neighbours = randomNeighboursOf(coordinate);

        grid.setCellAlive(coordinate);
        grid.setCellAlive(neighbours.remove(0)); // 1
        grid.setCellAlive(neighbours.remove(0)); // 2
        grid.setCellAlive(neighbours.remove(0)); // 3

        grid.setCellAlive(neighbours.remove(0));
        assertThat(grid.cellSurvive(coordinate)).isFalse(); // 4
        grid.setCellAlive(neighbours.remove(0));
        assertThat(grid.cellSurvive(coordinate)).isFalse(); // 5
        grid.setCellAlive(neighbours.remove(0));
        assertThat(grid.cellSurvive(coordinate)).isFalse(); // 6
        grid.setCellAlive(neighbours.remove(0));
        assertThat(grid.cellSurvive(coordinate)).isFalse(); // 7
        grid.setCellAlive(neighbours.remove(0));
        assertThat(grid.cellSurvive(coordinate)).isFalse(); // 8
    }

    @RepeatedTest(10)
    void anDeadCellWithThreeNeighboursResurrect() {
        final Grid grid = newRandomGrid();
        final Coordinate coordinate = newRandomCoordinateNotOnEdge(grid);
        final List<Coordinate> neighbours = randomNeighboursOf(coordinate);

        assertThat(grid.cellSurvive(coordinate)).isFalse(); // 0
        grid.setCellAlive(neighbours.remove(0));
        assertThat(grid.cellSurvive(coordinate)).isFalse(); // 1
        grid.setCellAlive(neighbours.remove(0));
        assertThat(grid.cellSurvive(coordinate)).isFalse(); // 2
        grid.setCellAlive(neighbours.remove(0));
        assertThat(grid.cellSurvive(coordinate)).isTrue(); // 3
        grid.setCellAlive(neighbours.remove(0));
        assertThat(grid.cellSurvive(coordinate)).isFalse(); // 4
        grid.setCellAlive(neighbours.remove(0));
        assertThat(grid.cellSurvive(coordinate)).isFalse(); // 5
        grid.setCellAlive(neighbours.remove(0));
        assertThat(grid.cellSurvive(coordinate)).isFalse(); // 6
        grid.setCellAlive(neighbours.remove(0));
        assertThat(grid.cellSurvive(coordinate)).isFalse(); // 7
        grid.setCellAlive(neighbours.remove(0));
        assertThat(grid.cellSurvive(coordinate)).isFalse(); // 8
    }

    @Test
    void predefinedGrid() {
        final String actual = "4 8\n" +
                "........\n" +
                "....*...\n" +
                "...**...\n" +
                "........";
        final String expected = "4 8\n" +
                "........\n" +
                "...**...\n" +
                "...**...\n" +
                "........";
        assertThat(Grid.parse(actual).nextGeneration().toString()).isEqualTo(expected);
    }

    @Test
    void edgeCase() {
        final Grid grid = Grid.parse("4 8\n" +
                "........\n" +
                "....*...\n" +
                "....*...\n" +
                "........");
        final Grid nextGenerationGrid = grid.nextGeneration();
        System.out.println(nextGenerationGrid);
    }

    static Grid newRandomGrid() {
        return newRandomGrid(5, 10, 5, 10);
    }

    static Grid newRandomGrid(final int widthMin, final int widthMax, final int heightMin, final int heightMax) {
        return new Grid(
                widthMin + RANDOM.nextInt(widthMax - widthMin),
                heightMin + RANDOM.nextInt(heightMax - heightMin));
    }

    static Coordinate newRandomCoordinateNotOnEdge(final Grid grid) {
        return Coordinate.builder()
                .x(1 + RANDOM.nextInt(grid.getWidth() - 2))
                .y(1 + RANDOM.nextInt(grid.getHeight() - 2))
                .build();
    }

    static List<Coordinate> randomNeighboursOf(final Coordinate coordinate) {
        final List<Coordinate> neighbourOffsets = new ArrayList<>(NEIGHBOUR_OFFSETS);
        return IntStream.range(0, neighbourOffsets.size())
                .mapToObj(n -> neighbourOffsets.remove(RANDOM.nextInt(neighbourOffsets.size())))
                .map(coordinate::move)
                .collect(Collectors.toList());
    }

}