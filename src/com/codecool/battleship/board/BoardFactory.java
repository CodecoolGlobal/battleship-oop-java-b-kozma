package com.codecool.battleship.board;

import com.codecool.battleship.util.SquareStatus;

import java.util.Arrays;

public class BoardFactory {

    Board board;

    public BoardFactory(Board board) {
        this.board = board;
    }

    public void manualPlacement(Ship ship) {
        // Set ship coordinate status to ship
        Arrays.stream(ship.getShipCoordinates()).forEach(square -> square.setStatus(SquareStatus.SHIP));
    }

}
