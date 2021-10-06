package com.codecool.battleship;

import com.codecool.battleship.board.*;
import com.codecool.battleship.util.ShipType;

public class Game {

    private final Display display;
    private final Input input;
    private final Board board;
    private final Player[] players;

    public Game(Player[] players, int boardSize, Display display, Input input) {
        this.display = display;
        this.input = input;
        this.board = new Board(boardSize);
        this.players = players;
    }

    public Board getBoard() {return board;}

    public void play() {
        System.out.println("Game is played here");
        display.printBoard(board);
        // TESTING START
        placeShip(1, ShipType.BATTLESHIP);

        // TESTING END
    }

    public void placeShip(int player, ShipType type) {
        if (input.isValidPlacement(board, type)) {
            display.printMessages("[TESTED] The placement passed the tests! The Ship can be made!");
        } else {
            display.printMessages("[TESTED] The placemenet failed the test!");
        }
    }

    // TODO

    // This method will naively produce subsequent references to squares based on orientation
    // Validate based on the output of this method
    // and DO NOT instantiate ship with the output if ANY of the squares are invalid
    // (i.e.: out of bounds or neighbouring or occupied)
    // Possibly move this to board
    private Square[] createShipCoordinates(Square headCoordinates, ShipType shipType, Orientations orientation) {
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
