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
        final Grid grid = Grid.from("");
        final Grid nextGenerationGrid = this.test.computeNextGeneration(grid);

        assertThat(nextGenerationGrid).isNotNull();
        assertThat(nextGenerationGrid).isNotSameAs(grid);
        assertThat(nextGenerationGrid.isEmpty()).isTrue();
    }

}