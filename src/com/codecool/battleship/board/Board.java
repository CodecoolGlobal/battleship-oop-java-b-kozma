package com.codecool.battleship.board;

public class Board {
    private Square[][] board;
    public Board(int size) {
        this.board = new Square[size][size];
    }
}
