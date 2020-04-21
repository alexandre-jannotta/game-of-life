package org.example.gameoflife;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameOfLifeTest {

    GameOfLife test;

    @BeforeEach
    void init() {
        this.test = new GameOfLife();
    }

    @Test
    void computeNextGenerationWithEmptyGrid() {
        final Grid currentGenerationGrid = Grid.fromRandomSize(5, 10, 5, 10);
        assertThat(currentGenerationGrid.isEmpty()).isTrue();

        final Grid nextGenerationGrid = this.test.computeNextGeneration(currentGenerationGrid);
        assertThat(nextGenerationGrid).isNotNull();
        assertThat(nextGenerationGrid).isNotSameAs(currentGenerationGrid);
        assertThat(nextGenerationGrid.isEmpty()).isTrue();
    }

    @Test
    void computeNextGenerationWithOneCellWithFewerThanTwoNeighbours() {
        final Grid currentGenerationGrid = Grid.fromRandomSize(5, 10, 5, 10);
        assertThat(currentGenerationGrid).isNotNull();
        assertThat(currentGenerationGrid.isEmpty()).isFalse();

        final Grid nextGenerationGrid = this.test.computeNextGeneration(currentGenerationGrid);
        assertThat(nextGenerationGrid).isNotNull();
        assertThat(nextGenerationGrid).isNotSameAs(currentGenerationGrid);
        assertThat(nextGenerationGrid.isEmpty()).isTrue();
    }

}