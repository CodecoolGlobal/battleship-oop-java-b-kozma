package com.codecool.battleship;

import com.codecool.battleship.board.Board;

import java.awt.*;
import java.util.Scanner;

public class Game {
    Board board;

    public Game(int size) {
        if (isBetween(size, 10, 20)) {
            this.board = new Board(size);
        } else {
            this.board = new Board(10);
            System.out.println("Wrong input! Board is built with default parameters.");
        }
    }

    public Board getBoard() {return board;}


    public boolean isValid() {
        return false;
    }

    public boolean isOutOfBounds(Point inputPoint) {
        return false;
    }

    public boolean isBetween(int input, int min, int max) {
        return (min <= input) && (input <= max);
    }

}
