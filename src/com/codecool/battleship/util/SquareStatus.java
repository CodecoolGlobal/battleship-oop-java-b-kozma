package com.codecool.battleship.util;

public enum SquareStatus {
    /*TODO: change this to uniCode characters*/

    EMPTY("\033[1;34m" + "~" + "\033[0m"),
    SHIP("\033[1;33m" + "S" + "\033[0m"),
    HIT("\033[1;32m" + "H" + "\033[0m"),
    MISSED("\033[1;31m" + "M" + "\033[0m");

    private String uniCodeCh;
    SquareStatus(String uniCodeCh) {
        this.uniCodeCh = uniCodeCh;
    }

    public String getCharacter() {return uniCodeCh;}
}
