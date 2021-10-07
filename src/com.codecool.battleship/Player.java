package com.codecool.battleship;

import com.codecool.battleship.board.*;

import java.awt.*;
import java.util.ArrayList;
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
        // TODO [when Ship and Square are ready, do allMatch() stream on ships and check if all are sunk. ]
        return true;
    }

    public String getName() {return this.name;}

    public Board getBoard() {
        return this.board;
    }

    private Point getPlacementDirectionCoordinates(Orientations orientation) {
        switch(orientation) {
            // Place ship squares to the right (east) of ship's head
            case HORIZONTAL:
                return Directions.EAST.getDirection();
            // Place ship squares to the down (south) of ship's head
            case VERTICAL:
                return Directions.SOUTH.getDirection();
            default:
                throw new IllegalArgumentException("Orientation must either be HORIZONTAL or VERTICAL");
        }
    }

    public Ship createShip(ShipConfig shipConfig) {
        int xHeadCoordinate = shipConfig.headCoordinates.x;
        int yHeadCoordiante = shipConfig.headCoordinates.y;
        int length = shipConfig.shipType.getLength();
        Square[] shipCoordinates = new Square[length];
        Point placementDirectionCoordinates = getPlacementDirectionCoordinates(shipConfig.orientation);
        for (int i = 0; i < length; i++) {
            shipCoordinates[i] = board.getSquare(xHeadCoordinate + i * placementDirectionCoordinates.x,
                    yHeadCoordiante + i * placementDirectionCoordinates.y);
        }
        return new Ship(shipCoordinates);
    }

    public void placeShip(Ship ship) {
        this.boardFactory.manualPlacement(ship);
    }

    public void shoot(Board opponentBoard, Square square) {
        opponentBoard.markShot(square);
    }

}
