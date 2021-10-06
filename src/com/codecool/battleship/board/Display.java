package com.codecool.battleship.board;

import com.codecool.battleship.Game;
import com.codecool.battleship.Player;

public class Display {

    public void printMenu() {
        System.out.println("Battleship is loading...");
    }

    public void printWelcomeArt() {
        System.out.println("   __      _____ _    ___ ___  __  __ ___         \n" +
                "   \\ \\    / / __| |  / __/ _ \\|  \\/  | __|        \n" +
                "    \\  ^^  /| _|| |_| (_| (_) | |\\/| | _|         \n" +
                "     \\_/\\_/ |___|____\\___\\___/|_|  |_|___|        \n" +
                "                |_   _/ _ \\                     \n" +
                "                  | || (_) |                    \n" +
                " ___   _ _____ ___|_| \\___/___ ___ _  _ ___ __ \n" +
                "| _ ) /_\\_   _|_   _| |  | __/ __| || |_ _| _ \\\n" +
                "| _ \\/ _ \\| |   | | | |__| _|\\__ \\ __ || ||  _/\n" +
                "|___/_/ \\_\\_|   |_| |____|___|___/_||_|___|_|  ");
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
        Square[][] squareList = board.getSquareList();
        System.out.print(" ");
        for (char i = 'A'; i < 'A' + size; i++) {
            System.out.print("  " + i);
        }

        System.out.println();


        for (int x = 1; x < size + 1; x++) {
            if (x < 10) {
                System.out.print(" " + x + " ");
            } else {
                System.out.print(x + " ");
            }
            for (int y = 0; y < size; y++) {
                System.out.print("" + squareList[x - 1][y].getStatus().getCharacter() + "  ");
            }
            System.out.println();
        }
    }

    // if phase is shooting enemy placement board = myShooting board.
    // if enemySquareStatus == ship -> myShootingSquare show empty
    // if enemySquareStatus == hit -> show hit
    // if missed -> missed
    // sunk -> sunk.
}
