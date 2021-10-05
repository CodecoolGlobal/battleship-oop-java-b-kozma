package com.codecool.battleship.board;

import com.codecool.battleship.util.SquareStatus;

import java.util.Arrays;

public class Ship {

    Square[] shipCoordinates;

    public Ship(Square[] shipCoordinates) {
        // Set ship coordinate status to ship
        Arrays.stream(shipCoordinates).forEach(square -> square.setStatus(SquareStatus.SHIP));
        this.shipCoordinates = shipCoordinates;
    }
}

