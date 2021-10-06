package com.codecool.battleship.board;

import com.codecool.battleship.util.ShipType;
import com.codecool.battleship.util.SquareStatus;

public class Board {
    private final Square[][] board;
    public Board(int size) {
        this.board = new Square[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                board[x][y] = new Square(x, y);
            }
        }
    }

    public Square[][] getSquareList() {return board;}

    public Square getSquare(int xCoordinate, int yCoordinate) {
        return board [xCoordinate][yCoordinate];
    }

    public boolean isEmpty(Square coordinates) {
        if (board[coordinates.x][coordinates.y].getStatus() == SquareStatus.EMPTY) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isOutOfBounds(Square input) {
        // Returns true if one of the x or y coordinates go beyond the board parameters
        // Returns false if no problem is found
        return ((input.x >= board.length) || (input.y >= board[0].length));
    }

    public boolean tryShip(Square input, ShipType type, Orientations orientation) {
        int shipLength = type.getLength();
        int boardLength = board.length;

        if (orientation == Orientations.HORIZONTAL){
            if ((input.y + shipLength - 1) >= boardLength) {
                return false;
            }
            return true;
        } else if (orientation == Orientations.VERTICAL){
            if ((input.x + shipLength - 1) >= boardLength) {
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean isChoicePossible(Square point) {
        return (!isOutOfBounds(point) && isEmpty(point));
    }

}
