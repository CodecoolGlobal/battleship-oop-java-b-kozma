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
                display.printMessages("Player " + currentPlayer.getName() + "'s turn \n"
                        + "Please place a " + shipType.displayName + "(" + shipType.getLength() + " squares long)");
                Square square = input.takeCoordinates("Give coordinates!");
                Orientations orientation = input.getUserShipOrientation();
                Ship playerShip = currentPlayer.createShip(square, orientation, shipType);
                currentPlayer.placeShip(playerShip);
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
}
