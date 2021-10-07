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
        shootingPhase();
    }

    private ShipConfig getUserShipConfig(ShipType shipType) {
        Square shipHeadCoordinates = input.takeCoordinates("Give coordinates!");
        Orientations orientation = input.getUserShipOrientation();
        ShipConfig shipConfig = new ShipConfig(shipHeadCoordinates, orientation, shipType);
        while(!currentPlayer.getBoard().isValidPlacement(input, shipConfig)) {
            shipHeadCoordinates = input.takeCoordinates("You cannot place here!\nGive coordinates!");
            orientation = input.getUserShipOrientation();
            shipConfig = new ShipConfig(shipHeadCoordinates, orientation, shipType);
        }
        return shipConfig;
    }

    private void placementPhase() {
        // NOTE this is only valid if we can want to place one of each ship
        //ShipType[] shipsToPlace = ShipType.values();
        ShipType[] shipsToPlace = {ShipType.CARRIER, ShipType.CRUISER};
        for(ShipType shipType : shipsToPlace) {
            for (int i=0; i < players.length; i++) {
                display.clearConsole();
                display.printMessages("Player " + currentPlayer.getName() + "'s turn \n"
                        + "Please place a " + shipType.displayName + "(" + shipType.getLength() + " squares long)");
                display.printBoard(currentPlayer.getBoard(), Phase.PLACEMENT);
                ShipConfig shipConfig = getUserShipConfig(shipType);
                Ship playerShip = currentPlayer.createShip(shipConfig);
                currentPlayer.placeShip(playerShip);
                display.clearConsole();
                display.printBoard(currentPlayer.getBoard(), Phase.PLACEMENT);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                switchPlayer();
            }
        }
    }

    private void shootingPhase() {
        while (true) {
            display.clearConsole();
            display.printMessages("It's " + currentPlayer.getName() + "'s turn");
            Board opponentBoard = getOpponent().getBoard();
            display.printBoard(getOpponent().getBoard(), Phase.SHOOTING);
            Square target = input.takeCoordinates("Please select coordinates to shoot at!\n");
            currentPlayer.shoot(opponentBoard, target);
            getOpponent().searchForSunk();
            display.clearConsole();
            display.printBoard(opponentBoard, Phase.SHOOTING);
            if(!getOpponent().isAlive()){break;}
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switchPlayer();
        }
        display.printMessages(currentPlayer.getName() + " WON!!!!!");
    }

    private void switchPlayer() {
        if (currentPlayer == players[0]) {
            currentPlayer = players[1];
        } else {
            currentPlayer = players[0];
        }
    }

    private Player getOpponent() {
        if (currentPlayer == players[0]) {
            return players[1];
        } else {
            return players[0];
        }
    }
}
