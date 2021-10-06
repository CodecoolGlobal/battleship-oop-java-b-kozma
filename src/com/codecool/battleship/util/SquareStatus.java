package com.codecool.battleship.util;

public enum SquareStatus {
    /*TODO: change this to uniCode characters*/

    EMPTY("\033[1;34m" + "~" + "\033[0m"),
    SHIP("S"),
    HIT("H"),
    MISSED("M");

    private String uniCodeCh;
    SquareStatus(String uniCodeCh) {
        this.uniCodeCh = uniCodeCh;
    }

    public String getCharacter() {return uniCodeCh;}
}
