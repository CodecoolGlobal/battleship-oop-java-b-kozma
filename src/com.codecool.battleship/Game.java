package com.codecool.battleship;

import com.codecool.battleship.board.Board;

public class Game {
    Board board;

    public Game() {
        this.board = new Board(10);
    }

    public Board getBoard() {return board;}
}
