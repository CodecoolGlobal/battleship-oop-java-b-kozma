package com.codecool.battleship.board;

public enum Phase {
    PLACEMENT("Placement"),
    SHOOTING("Shooting");

    private final String phase;

    Phase(String phase) {
        this.phase = phase;
    }

    public String getPhase() {return this.phase;}
}
