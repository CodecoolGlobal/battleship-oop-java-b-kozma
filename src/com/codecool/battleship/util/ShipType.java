package com.codecool.battleship.util;

public enum ShipType {
    CARRIER("carrier", 1),
    CRUISER("cruiser", 2),
    BATTLESHIP("battleship", 3),
    SUBMARINE("submarine", 4),
    DESTROYER("destroyer", 5);
    private int length;
    public String displayName;

    ShipType(String displayName, int length) {
        this.length = length;
        this.displayName = displayName;
    }

    public int getLength() {return length;}

}
