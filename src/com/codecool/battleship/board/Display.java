package com.codecool.battleship.board;

import com.codecool.battleship.Player;

public class Display {

    public void printMenu() {
        System.out.println("Battleship is loading...");
    }

    public void printMessages(String message) {
        System.out.println(message);
    }

    public void printMainMenuOptions() {
        System.out.println("press: \n" +
               " \t 0 - Play" +
                "\t 1 - Print game rules" +
                "\t 2 - Exit the game" );
    }

    public void printExitMessage () {
        System.out.println("Goodbye!");
    }

    public void gameRules () {
        System.out.println("The object of Battleship is to try and sink all of the other player's before " +
                "they sink all of your ships. All of the other player's ships are somewhere on his/her board. " +
                "You try and hit them by calling out the coordinates of one of the squares on the board.");
    }

    public void printBoard(Board board) {
        int size = board.getSquareList().length;
        System.out.print(" ");
        for (char i = 'A'; i < 'A' + size ; i++) {
            System.out.print("  " + i );
        }

        System.out.println();

        Square square;


        for (int x = 1; x < size+1; x++) {
            if (x < 10) {
                System.out.print(" " + x + " ");
            }
            else {
                System.out.print(x + " ");
            }
            for (int y = 0; y < size; y++) {
                square = new Square(x, y);
                System.out.print("" + square + "  ");
            }
            System.out.println();
        }
    }
}
