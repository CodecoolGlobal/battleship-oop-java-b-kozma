package com.codecool.battleship;

import com.codecool.battleship.board.Board;

import java.awt.*;
import java.util.InputMismatchException;
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

    public int takeInputInteger(String message) {
        System.out.println(message+"\n");
        Scanner scanInteger = new Scanner(System.in);
        int input = -1;
        while (input == -1) {
            try {
                input = scanInteger.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Mismatch error: Give Integer!");
                scanInteger.next();
            }
        }
        return input;
    }

}
