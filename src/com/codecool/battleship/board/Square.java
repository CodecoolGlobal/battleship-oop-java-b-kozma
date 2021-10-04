package com.codecool.battleship.board;

import com.codecool.battleship.util.SquareStatus;

import java.awt.Point;

public class Square extends Point {
    private SquareStatus status;

    public Square(int x, int y) {
        super(x, y);
        this.status = SquareStatus.EMPTY;
    }

    @Override
    public String toString() {
        return status.getCharacter();
    }


}
