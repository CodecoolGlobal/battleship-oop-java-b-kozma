package com.codecool.battleship;

import com.codecool.battleship.board.*;
import com.codecool.battleship.util.ShipType;

public class Game {

    private final Display display;
    private final Input input;
    private final Player[] players;
    private Player currentPlayer;

    public Game(Player[] players, Display display, Input input) {
        this.display = display;
        this.input = input;
        this.players = players;
        this.currentPlayer = players[0];
    }

    public void play() {
        placementPhase();
    }

    private void placementPhase() {
        // NOTE this is only valid if we can want to place one of each ship
        ShipType[] shipsToPlace = ShipType.values();
        for(ShipType shipType : shipsToPlace) {
            for (int i=0; i < players.length; i++) {
                System.out.println("Current player is: " + currentPlayer.getName() +"\n" + "Opponent is: " + getOpponent().getName());
                placeShip(currentPlayer, shipType);
                display.printBoard(currentPlayer.getBoard());
                switchPlayer();
            }
        }
    }

    private void switchPlayer() {
        if(currentPlayer == players[0]) {
            currentPlayer = players[1];
        } else {
            currentPlayer = players[0];
        }
    }

    private Player getOpponent() {
        if(currentPlayer == players[0]) {
            return players[1];
        } else {
            return players[0];
        }
    }

    public void placeShip(Player player, ShipType shipType) {
        display.printMessages("Please place a " + shipType.displayName + "(" + shipType.getLength() + " squares long)");
        Board board = player.getBoard();
        Square square = input.takeCoordinates("Give coordinates!", board);
        Orientations orientation = input.getUserShipOrientation();
        if (input.isValid(board, square) && input.isEmpty(board, square)) {
            Square[] shipCoordinates = createShipCoordinates(square, shipType, orientation);
            Ship ship = new Ship(shipCoordinates);
            player.getBoardFactory().manualPlacement(ship);
        }
    }

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
            shipCoordinates[i] = currentPlayer.getBoard().getSquare(headCoordinates.x + i * xCoordinate, headCoordinates.y + i * yCoordinate);
        }
        return shipCoordinates;
    }
}
