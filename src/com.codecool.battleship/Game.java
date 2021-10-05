package com.codecool.battleship;

import com.codecool.battleship.board.Board;

import java.awt.*;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Game {

    Board board;

    Player[] players = new Player[2];

    // GAME STEPS METHODS

    public Board getBoard() {return board;}

    public void setBoardSize() {
        int size = takeInputInteger("Give us a board size between 10 and 20!");
        while (!isBetween(size, 10,20)) {
            System.out.println("Between 10 and 20!");
            size = takeInputInteger("Try again!");
        }
        this.board = new Board(size);
    }

    public void setPlayers() {
        players[0] = new Player(
                takeInputString("Tell us your name, Player 1!"),
                1,
                "X");

        players[1] = new Player(
                takeInputString("Tell us your name, Player 2!"),
                2,
                "O"
        );
        System.out.println("Players are all set!\nCaptain " +
                players[0].getName() + " versus Captain " + players[1].getName());
    }


    public void placeShip(int player) {
        Point input = takeInputCoordinates("Give coordinates!");
        System.out.println("TESTING: " + input.x + "-" + input.y + " is a good coordinate!");
        // TODO isValid(coordinates) —> while (!isValid(coord)) {input = takeInputCoord("Try again!");}
    }

    // VALIDATIONS
    public boolean isValid(Point input) {
        if (input == null) {
            System.out.println("Input is null!");
            return false;
        } else if (isNegative(input)) {
            System.out.println("At least one input is negative!");
            return false;
        } else if (isOutOfBounds(input)) {
            System.out.println("At least one coordinate is out of bounds!");
            return false;
        } // More validations to be added.
        System.out.println("[TEST LOG] Valid input!");
        return true;
    }

    public boolean validFormat(String coordinates) {
        if (coordinates.length() > 3 || !coordinates.toLowerCase().matches("[a-z][0-9]+")) {
            System.out.println("Format your input in the following way:\n'B5' or 'B19'.");
            return false;
        }
        return true;
    }

    public boolean isOutOfBounds(Point input) {
        // Returns true if one of the x or y coordinates go beyond the board parameters
        // Returns false if no problem is found
        return ((input.x > board.getSquareList().length) || (input.y > board.getSquareList()[0].length));
    }

    public boolean isNegative(Point input) {
        // Returns true if negative
        // Returns false if positive
        return (input.x < 0 || input.y < 0);
    }


    public boolean isBetween(int input, int min, int max) {
        return (min <= input) && (input <= max);
    }


    // TAKE INPUT
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

    public String takeInputString(String message) {
        System.out.println(message+"\n");
        Scanner scanInteger = new Scanner(System.in);
        String result = scanInteger.nextLine();
        return result;
    }

    public Point takeInputCoordinates(String message) {
        String input = takeInputString(message);
        while (!validFormat(input)) {
            input = takeInputString("Try again!");
        }
        int x = (int) input.charAt(0) - 97; // A —> 0
        int y = Integer.parseInt(input.substring(1, input.length())) - 1; //
        return new Point(x, y);
    }


}
