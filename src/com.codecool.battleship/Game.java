package com.codecool.battleship;

import com.codecool.battleship.board.Board;

import java.awt.*;
import java.util.Scanner;

public class Game {
    Board board;

    public Board getBoard() {return board;}

    public void setBoardSize() {
        int size = takeInputInteger("Give us a board size between 10 and 20!");
        while (!isBetween(size, 10,20)) {
            System.out.println("Between 10 and 20!");
            size = takeInputInteger("Try again!");
        }
        this.board = new Board(size);
    }

    public boolean isValid() {
        return false;
    }

    public boolean isOutOfBounds(Point inputPoint) {
        return false;
    }

    public boolean isBetween(int input, int min, int max) {
        return (min <= input) && (input <= max);
    }

    public static int takeInputInteger(String message) {
        System.out.println(message + "\n");
        Scanner takeIn = new Scanner(System.in);
        return takeIn.nextInt();
    }

}
