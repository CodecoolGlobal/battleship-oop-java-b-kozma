package com.codecool.battleship;

import com.codecool.battleship.board.Board;
import com.codecool.battleship.board.BoardFactory;
import com.codecool.battleship.board.Ship;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final String name;
    private final Board board;
    private final BoardFactory boardFactory;
    List<Ship> ships = new ArrayList<>();

    public Player(String name, int boardSize) {
        this.name = name;
        this.board = new Board(boardSize);
        this.boardFactory = new BoardFactory(board);
    }

    public boolean isAlive() {
        // TODO [when Ship and Square are ready, do allMatch() stream on ships and check if all are sunk. ]
        return false;
    }

    public String getName() {return this.name;}

    public Board getBoard() {
        return this.board;
    }

    public BoardFactory getBoardFactory() {
        return this.boardFactory;
    }

}
