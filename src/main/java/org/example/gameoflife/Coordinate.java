package org.example.gameoflife;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Coordinate {

    private final int x;
    private final int y;

    public Coordinate move(final Coordinate offset) {
        return Coordinate.builder()
                .x(this.x + offset.x)
                .y(this.y + offset.y)
                .build();
    }

    public boolean isIn(final Grid grid) {
        return this.x >= 0 && this.y >= 0 && this.x < grid.getWidth() && this.y < grid.getHeight();
    }

}
