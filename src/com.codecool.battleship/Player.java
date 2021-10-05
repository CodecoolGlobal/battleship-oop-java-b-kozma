package com.codecool.battleship;

import com.codecool.battleship.board.Ship;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final String name;

    List<Ship> ships = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public boolean isAlive() {
        // TODO [when Ship and Square are ready, do allMatch() stream on ships and check if all are sunk. ]
        return false;
    }

    public String getName() {return this.name;}

    public void addShip(Ship ship) {
        ships.add(ship);
    }

}
