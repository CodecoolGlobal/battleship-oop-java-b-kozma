package com.codecool.battleship.board;

public class Ship {

    Square[] shipCoordinates;

    public Ship(Square[] shipCoordinates) {
        this.shipCoordinates = shipCoordinates;
    }

    public Square[] getShipCoordinates() {
        return shipCoordinates;
    }
}

