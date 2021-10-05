package com.codecool.battleship;

import com.codecool.battleship.board.Board;
import com.codecool.battleship.board.Display;
import com.codecool.battleship.board.Orientations;
import com.codecool.battleship.board.Square;
import com.codecool.battleship.util.SquareStatus;

import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {

    Display display;
    public Input(Display display) {
        this.display = display;
    }

    public boolean isValid(Board board, Point input) {
        if (input == null) {
            display.printMessages("Input is null!");
            return false;
        } else if (isNegative(input)) {
            display.printMessages("At least one input is negative!");
            return false;
        } else if (isOutOfBounds(board, input)) {
            display.printMessages("At least one coordinate is out of bounds!");
            return false;
        }
        display.printMessages("[TEST LOG] Valid input!");
        return true;
    }

    public boolean validFormat(String coordinates) {
        if (coordinates.length() > 3 || !coordinates.toLowerCase().matches("[a-z][0-9]+")) {
            display.printMessages("Format your input in the following way:\n'B5' or 'B19'.");
            return false;
        }
        return true;
    }

    public boolean isEmpty(Board board, Point coordinates) {
        if (board.getSquareList()[coordinates.x][coordinates.y].getStatus() == SquareStatus.EMPTY) {
            display.printMessages("The square is empty!");
            return true;
        } else {
            display.printMessages("The square is taken!");
            return false;
        }
    }

    public boolean isOutOfBounds(Board board, Point input) {
        // Returns true if one of the x or y coordinates go beyond the board parameters
        // Returns false if no problem is found
        return ((input.x >= board.getSquareList().length) || (input.y >= board.getSquareList()[0].length));
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

    public Square takeCoordinates(String message, Board board) {
        String input = takeString(message);
        while (!validFormat(input)) {
            input = takeString("Try again!");
        }
        int y = (int) input.toLowerCase().charAt(0) - 97; // aA —> 0
        int x = Integer.parseInt(input.substring(1)) - 1; // 1 —> 0
        return board.getSquare(x, y);
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

    public int getBoardSize() {
        int boardSize = takeInteger("Give us a board size between 10 and 20!");
        while (!isBetween(boardSize, 10,20)) {
            display.printMessages("Between 10 and 20!");
            boardSize = takeInteger("Try again!");
        }
        return boardSize;
    }

}
