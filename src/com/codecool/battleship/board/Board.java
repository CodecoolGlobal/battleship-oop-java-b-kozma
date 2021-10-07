package com.codecool.battleship.board;
import com.codecool.battleship.Input;
import com.codecool.battleship.util.SquareStatus;

import java.util.ArrayList;

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

    public void markShot(Square square) {
        Square target = getSquare(square.x, square.y);
        SquareStatus targetStatus = target.getStatus();
        switch (targetStatus) {
            case EMPTY:
                target.setStatus(SquareStatus.MISSED);
                break;
            case SHIP:
                target.setStatus(SquareStatus.HIT);
                break;
        }
    }

    public boolean isEmpty(Square coordinates) {
        return board[coordinates.x][coordinates.y].getStatus() == SquareStatus.EMPTY;
    }

    public boolean isOutOfBounds(Square input) {
        // Returns true if one of the x or y coordinates go beyond the board parameters
        // Returns false if no problem is found
        return ((input.x >= board.length) || (input.y >= board[0].length));
    }

    public boolean tryShip(ShipConfig shipConfig) {
        int shipLength = shipConfig.shipType.getLength();
        Orientations orientation = shipConfig.orientation;
        Square headCoordinates = shipConfig.headCoordinates;
        int boardLength = board.length;

        if (orientation == Orientations.HORIZONTAL) {
            return (headCoordinates.y + shipLength - 1) < boardLength && isAllEmpty(headCoordinates, shipLength, Directions.EAST);
        } else if (orientation == Orientations.VERTICAL) {
            return (headCoordinates.x + shipLength - 1) < boardLength && isAllEmpty(headCoordinates, shipLength, Directions.SOUTH);
        }
        return false;
    }

    public boolean isValidSquare(Square point) {
        return (!isOutOfBounds(point) && isEmpty(point));
    }

    public boolean isAllEmpty(Square input, int length, Directions direction) {
        ArrayList<Square> result = new ArrayList<>();
        if (direction == Directions.EAST){
            for (int i = 0; i < length; i++) {
                result.add(getSquare(input.x, input.y+i));
                if (i == 0 && input.y > 0) {
                    result.add(getSquare(input.x, input.y-1));
                }
                if ((i == length -1) && input.y < board.length-1) {
                    result.add(getSquare(input.x, input.y+i+1));
                }
                if (input.x > 0) {
                    result.add(getSquare(input.x - 1, input.y + i));
                }
                if (input.x < board.length-1) {
                    result.add(getSquare(input.x+1, input.y+i));
                }
            }
        } else if (direction == Directions.SOUTH){
            for (int i = 0; i < length; i++) {
                result.add(getSquare(input.x+i, input.y));
                if (i == 0 && input.x > 0) {
                    result.add(getSquare(input.x-1, input.y));
                }
                if ((i == length -1) && input.x < board.length-1) {
                    result.add(getSquare(input.x+i+1, input.y));
                }
                if (input.y > 0) {
                    result.add(getSquare(input.x+i, input.y - 1));
                }
                if (input.y < board.length-1) {
                    result.add(getSquare(input.x+i, input.y + 1));
                }
            }
        }
        return result.stream().allMatch(square -> square.getStatus() == SquareStatus.EMPTY);
    }

    public boolean isValidPlacement(Input input, ShipConfig shipconfig) {
        Square headCoordinates = shipconfig.headCoordinates;
        boolean isValidInput = input.isValidInput(headCoordinates);
        boolean isValidSquare = isValidInput && isValidSquare(headCoordinates);
        return isValidSquare && tryShip(shipconfig);
    }

}
