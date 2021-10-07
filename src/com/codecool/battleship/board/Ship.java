package com.codecool.battleship.board;

import com.codecool.battleship.util.SquareStatus;

import java.util.Arrays;

public class Ship {

    Square[] shipCoordinates;

    public Ship(Square[] shipCoordinates) {
        this.shipCoordinates = shipCoordinates;
    }

    public Square[] getShipCoordinates() {
        return shipCoordinates;
    }

    public boolean isSunk() {
        return Arrays.stream(shipCoordinates).allMatch(square -> square.getStatus() == SquareStatus.HIT);
    }

    public void updateShip() {
        if (isSunk()) {
            for (Square square: shipCoordinates){
                square.setStatus(SquareStatus.SUNK);
            }
        }
    }
}

