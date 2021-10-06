package com.codecool.battleship.util;

public enum SquareStatus {
    /*TODO: change this to uniCode characters*/

    EMPTY("\033[1;34m" + "~" + "\033[0m"),
    SHIP("\033[1;33m" + "S"),
    HIT("\033[1;32m" + "H"),
    MISSED("\033[1;31m" + "M");

    private String uniCodeCh;
    SquareStatus(String uniCodeCh) {
        this.uniCodeCh = uniCodeCh;
    }

    public String getCharacter() {return uniCodeCh;}
}
