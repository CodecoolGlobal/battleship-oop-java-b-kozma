package com.codecool.battleship.board;

import com.codecool.battleship.util.ShipType;

public class ShipConfig {

    public Square headCoordinates;
    public Orientations orientation;
    public ShipType shipType;

    public ShipConfig(Square headCoordinates, Orientations orientation, ShipType shipType) {
        this.headCoordinates = headCoordinates;
        this.orientation = orientation;
        this.shipType = shipType;
    }
}
