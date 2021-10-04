package com.codecool.battleship.util;

public enum ShipType {
    CARRIER(1),
    CRUISER(2),
    BATTLESHIP(3),
    SUBMARINE(4),
    DESTROYER(5);
    private int length;

    ShipType(int length) {
        this.length = length;
    }

    public int getLength() {return length;}

}
