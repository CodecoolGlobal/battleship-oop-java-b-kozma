package com.codecool.battleship;

import com.codecool.battleship.board.*;
import com.codecool.battleship.util.ShipType;
import com.codecool.battleship.util.SquareStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {

    private final String name;
    private final Board board;
    private final BoardFactory boardFactory;
    List<Ship> ships = new ArrayList<>();

    public Player(String name, int boardSize) {
        this.name = name;
        this.board = new Board(boardSize);
        this.boardFactory = new BoardFactory(board);
    }

    public boolean isAlive() {
        // [when Ship and Square are ready, do allMatch() stream on ships and check if all are sunk. ]
        return !(ships.stream().allMatch(ship -> Arrays.stream(ship.getShipCoordinates()).allMatch(
                square -> square.getStatus() == SquareStatus.SUNK)));
    }

    public String getName() {return this.name;}

    public Board getBoard() {
        return this.board;
    }

    // This method will naively produce subsequent references to squares based on orientation
    // Validate based on the output of this method
    // and DO NOT instantiate ship with the output if ANY of the squares are invalid
    // (i.e.: out of bounds or neighbouring or occupied)
    // Possibly move this to board
    public Ship createShip(Square headCoordinates, Orientations orientation, ShipType shipType) {
        int length = shipType.getLength();
        Square[] shipCoordinates = new Square[length];
        // TODO refactor this, maybe outsource to enum
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
        for (int i = 0; i < length; i++) {
            shipCoordinates[i] = this.board.getSquare(headCoordinates.x + i * xCoordinate, headCoordinates.y + i * yCoordinate);
        }
        return new Ship(shipCoordinates);
    }

    public void placeShip(Ship ship) {
        this.boardFactory.manualPlacement(ship);
        ships.add(ship);
    }

    public void shoot(Board opponentBoard, Square square) {
        opponentBoard.markShot(square);
    }

    public void searchForSunk() {
        for (Ship ship: ships) {
            ship.updateShip();
        }
    }
}
