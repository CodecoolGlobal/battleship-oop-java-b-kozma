package com.codecool.battleship;

import java.util.ArrayList;
import java.util.List;
import com.codecool.battleship.board.Ship;

public class Player {

    private final int ID;

    private final String name;

    private final String UNICODE;

    private boolean lost = false;

    List<Ship> ships = new ArrayList<>();

    Player(String name, int id, String unicode) {
        this.name = name;
        this.ID = id;
        this.UNICODE = unicode;
    }

    public void registerShip(Ship ship) {
        ships.add(ship);
    }

    public boolean isAllSunk() {
        // TODO [when Ship and Square are ready, do allMatch() stream on ships and check if all are sunk. ]
        return false;
    }

    public String getName() {return this.name;}

    public String getUNICODE() {return this.UNICODE;}

    public boolean lost() {return this.lost;}
}
