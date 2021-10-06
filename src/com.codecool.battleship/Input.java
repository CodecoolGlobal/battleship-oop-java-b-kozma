package com.codecool.battleship;

import com.codecool.battleship.board.Board;
import com.codecool.battleship.board.Display;
import com.codecool.battleship.board.Orientations;
import com.codecool.battleship.board.Square;
import com.codecool.battleship.util.ShipType;
import com.codecool.battleship.util.SquareStatus;

import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {

    Display display;
    public Input(Display display) {
        this.display = display;
    }

    public boolean isValidInput(Square input) {
        if (input == null) {
            display.printMessages("Input is null!");
            return false;
        } else if (isNegative(input)) {
            display.printMessages("At least one input is negative!");
            return false;
        }
        display.printMessages("[TEST LOG] Valid input!");
        return true;
    }

    public boolean isValidFormat(String coordinates) {
        if (coordinates.length() > 3 || !coordinates.toLowerCase().matches("[a-z][0-9]+")) {
            display.printMessages("Format your input in the following way:\n'B5' or 'B19'.");
            return false;
        }
        return true;
    }

    public boolean isNegative(Square input) {
        // Returns true if negative
        // Returns false if positive
        return (input.x < 0 || input.y < 0);
    }


    public boolean isBetween(int input, int min, int max) {
        return (min <= input) && (input <= max);
    }

    // TAKE INPUT
    public int takeInteger(String message) {
        display.printMessages(message+"\n");
        Scanner scanInteger = new Scanner(System.in);
        int input = -1;
        while (input == -1) {
            try {
                input = scanInteger.nextInt();
            } catch (InputMismatchException e) {
                display.printMessages("Mismatch error: Give Integer!");
                scanInteger.next();
            }
        }
        return input;
    }

    public String takeString(String message) {
        display.printMessages(message+"\n");
        Scanner scanInteger = new Scanner(System.in);
        String result = scanInteger.nextLine();
        return result;
    }

    public Square takeCoordinates(String message) {
        String input = takeString(message);
        while (!isValidFormat(input)) {
            input = takeString("Try again!");
        }
        int y = (int) input.toLowerCase().charAt(0) - 97; // aA —> 0
        int x = Integer.parseInt(input.substring(1)) - 1; // 1 —> 0
        return new Square(x, y);
    }

    private boolean isValidOrientation(int orientationNumCode) {
        return orientationNumCode == 1 || orientationNumCode == 2;
    }

    public Orientations getUserShipOrientation() {
        String userShipOrientationPromptMsg = "Please choose an orientation: \n Horizontal(1) \n Vertical(2)";
        int userShipOrientationNumCode = takeInteger(userShipOrientationPromptMsg);
        while(!isValidOrientation(userShipOrientationNumCode)) {
            display.printMessages(userShipOrientationPromptMsg);
            userShipOrientationNumCode = takeInteger("Try again");
        }
        Orientations orientation;
        switch (userShipOrientationNumCode) {
            case 1:
                orientation = Orientations.HORIZONTAL;
                break;
            case 2:
                orientation = Orientations.VERTICAL;
                break;
            default:
                // QUESTION This should never happen
                // Can we get the enum object back from the entered user input somehow?
                throw new IllegalArgumentException("Orientation number code must be either 1 or 2");
        }
        return orientation;
    }

    /* Coordinates taking -- OUT OF USE!
    public Square takeCoordinates(String message, Board board) {
        String input = takeString(message);
        while (!isValidFormat(input)) {
            input = takeString("Try again!");
        }
        int y = (int) input.toLowerCase().charAt(0) - 97; // aA —> 0
        int x = Integer.parseInt(input.substring(1)) - 1; // 1 —> 0
        return board.getSquare(x, y);
    }
    */

    public int getBoardSize() {
        int boardSize = takeInteger("Give us a board size between 10 and 20!");
        while (!isBetween(boardSize, 10,20)) {
            display.printMessages("Between 10 and 20!");
            boardSize = takeInteger("Try again!");
        }
        return boardSize;
    }

    /* INPUT METHOD taking orientation from the user -- OUT OF USE!
    public Orientations takeDirection(){
        int input = takeInteger("Press 0 for horizontal, and 1 for vertical.");
        while(input != Orientations.HORIZONTAL.getValue() && input != Orientations.VERTICAL.getValue()) {
            display.printMessages("Incorrect orientation!");
            input = takeInteger("Press 0 for horizontal and 1 for vertical.");
        }
        if (input == Orientations.HORIZONTAL.getValue()) {return Orientations.HORIZONTAL;}
        else {return Orientations.VERTICAL;}
    }
     */

}
