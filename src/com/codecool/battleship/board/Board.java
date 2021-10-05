package com.codecool.battleship.board;

public class Board {
    private final Square[][] board;
    public Board(int size) {
        this.board = new Square[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                board[x][y] = new Square(x, y);
            }
        }
    }

    public Square[][] getSquareList() {return board;}

    public Square getSquare(int xCoordinate, int yCoordinate) {
        return board [xCoordinate][yCoordinate];
    }

}
