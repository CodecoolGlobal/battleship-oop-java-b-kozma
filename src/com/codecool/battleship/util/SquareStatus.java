package com.codecool.battleship.util;

public enum SquareStatus {
    /*TODO: change this to uniCode characters*/

    EMPTY("\033[1;34m" + "~" + "\033[0m"),
    SHIP(  "\033[0;37m" + "S" + "\033[0m"),
    HIT("\033[0;33m" + "H" + "\033[0m"),
    MISSED(  "\033[0;30m" + "M" + "\033[0m"),
    SUNK(  "\033[0;31m" + "X" + "\033[0m");

    private final String uniCodeCh;
    SquareStatus(String uniCodeCh) {
        this.uniCodeCh = uniCodeCh;
    }

    public String getCharacter() {return uniCodeCh;}
}
