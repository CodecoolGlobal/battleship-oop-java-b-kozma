package com.codecool.battleship.board;

public enum Orientations {
    HORIZONTAL(0),
    VERTICAL(1);

    private final int value;

    Orientations(int value) {
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }
}
