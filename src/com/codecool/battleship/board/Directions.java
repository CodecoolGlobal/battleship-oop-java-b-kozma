package com.codecool.battleship.board;

import java.awt.*;

public enum Directions {
    NORTH(new Point(-1, 0)),
    EAST(new Point(0, 1)),
    SOUTH(new Point(1, 0)),
    WEST(new Point(0, -1));

    public Point direction;
    Directions(Point direction) {
        this.direction = direction;
    }

    public Point getDirection() {
        return direction;
    }
}
