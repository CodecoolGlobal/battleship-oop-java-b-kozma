package com.codecool.battleship;

import com.codecool.battleship.board.*;
import com.codecool.battleship.util.ShipType;

public class Game {
    Board board;

    public Game() {
        this.board = new Board(10);
    }

    public Board getBoard() {return board;}

    // This method will naively produce subsequent references to squares based on orientation
    // Validate based on the output of this method
    // and DO NOT instantiate ship with the output if ANY of the squares are invalid
    // (i.e.: out of bounds or neighbouring or occupied)
    // Possibly move this to board
    private Square[] getShipCoordinates(Square headCoordinates, ShipType shipType, Orientations orientation) {
        int length = shipType.getLength();
        Square[] shipCoordinates = new Square[length];
        int xCoordinate;
        int yCoordinate;
        switch(orientation) {
            // Place ship squares to the right (east) of ship's head
            case HORIZONTAL:
                xCoordinate = Directions.EAST.getDirection().x;
                yCoordinate = Directions.EAST.getDirection().y;
                break;
            // Place ship squares to the down (south) of ship's head
            case VERTICAL:
                xCoordinate = Directions.SOUTH.getDirection().x;
                yCoordinate = Directions.SOUTH.getDirection().y;
                break;
            default:
                throw new IllegalArgumentException("Orientation must either be HORIZONTAL or VERTICAL");
        }
        shipCoordinates[0] = headCoordinates;
        for (int i = 1; i < length; i++) {
            shipCoordinates[i] = board.getSquare(headCoordinates.x + i * xCoordinate, headCoordinates.y + i * yCoordinate);
        }
        return shipCoordinates;
    }
}
