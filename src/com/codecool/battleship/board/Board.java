package com.codecool.battleship.board;

import com.codecool.battleship.Input;
import com.codecool.battleship.util.ShipType;
import com.codecool.battleship.util.SquareStatus;
import java.util.ArrayList;

import java.util.Arrays;

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
            } else if (!isAllEmpty(input, shipLength, Directions.EAST)) {
                return false;
            } else {return true;}
        } else if (orientation == Orientations.VERTICAL){
            if ((input.x + shipLength - 1) >= boardLength) {
                return false;
            } else if (!isAllEmpty(input, shipLength, Directions.SOUTH)) {
                return false;
            } else {return true;}
        }
        return false;
    }

    public boolean isValidSquare(Square point) {
        return (!isOutOfBounds(point) && isEmpty(point));
    }

    public boolean isAllEmpty(Square input, int length, Directions direction) {
        ArrayList<Square> result = new ArrayList<Square>();
        if (direction == Directions.EAST){
            for (int i = 0; i < length; i++) {
                result.add(getSquare(input.x, input.y+i));
            }
        } else if (direction == Directions.SOUTH){
            for (int i = 0; i < length; i++) {
                result.add(getSquare(input.x+i, input.y));
            }
        }
        return result.stream().allMatch(square -> square.getStatus() == SquareStatus.EMPTY);
    }

    public boolean isValidPlacement(Input input, Display display, Square square, ShipType shipType, Orientations orientation) {
        boolean isValidInput = input.isValidInput(square);
        boolean isValidSquare = isValidInput && isValidSquare(square);
        boolean tryShip = isValidSquare && tryShip(square, shipType, orientation);
        return tryShip;
    }

}
