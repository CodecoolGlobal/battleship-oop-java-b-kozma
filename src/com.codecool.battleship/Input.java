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

    public boolean isValidInput(Board board, Square input) {
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

    public boolean tryShip(Board board, Square input, ShipType type, Orientations orientation) {
        int shipLength = type.getLength();
        int boardLength = board.getSquareList().length;
        // TODO outOfBounds() for placement
        // TODO check if all are empty
        // TODO check if neighbours are empty
        if (orientation == Orientations.HORIZONTAL){
            if ((input.y + shipLength - 1) >= boardLength) {
                display.printMessages("You cannot place horizontally!");
                return false;
            }
            return true;
        } else if (orientation == Orientations.VERTICAL){
            if ((input.x + shipLength - 1) >= boardLength) {
                display.printMessages("You cannot place vertically!");
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean isValidPlacement(Board board, ShipType type) {
        Square point = takeCoordinates("Give coordinates!");
        Orientations orientation;
        display.printMessages("TESTING: " + point.x + "-" + point.y + " is a well formatted coordinate!");
        boolean isValidInput = isValidInput(board, point);
        boolean isEmpty = isValidInput && isEmpty(board, point);


        while (!(isValidInput && isEmpty)) {
            point = takeCoordinates("Try again!");
            isValidInput = isValidInput(board, point);
            isEmpty = isValidInput && isEmpty(board, point);
        }

        orientation = takeDirection();
        return tryShip(board, point, type, orientation);
    }

    public boolean validFormat(String coordinates) {
        if (coordinates.length() > 3 || !coordinates.toLowerCase().matches("[a-z][0-9]+")) {
            display.printMessages("Format your input in the following way:\n'B5' or 'B19'.");
            return false;
        }
        return true;
    }

    public boolean isEmpty(Board board, Square coordinates) {
        if (board.getSquareList()[coordinates.x][coordinates.y].getStatus() == SquareStatus.EMPTY) {
            display.printMessages("The square is empty!");
            return true;
        } else {
            display.printMessages("The square is taken!");
            return false;
        }
    }

    public boolean isOutOfBounds(Board board, Square input) {
        // Returns true if one of the x or y coordinates go beyond the board parameters
        // Returns false if no problem is found
        return ((input.x >= board.getSquareList().length) || (input.y >= board.getSquareList()[0].length));
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
        while (!validFormat(input)) {
            input = takeString("Try again!");
        }
        int y = (int) input.toLowerCase().charAt(0) - 97; // aA —> 0
        int x = Integer.parseInt(input.substring(1, input.length())) - 1; // 1 —> 0
        return new Square(x, y);
    }

    public int getBoardSize() {
        int boardSize = takeInteger("Give us a board size between 10 and 20!");
        while (!isBetween(boardSize, 10,20)) {
            display.printMessages("Between 10 and 20!");
            boardSize = takeInteger("Try again!");
        }
        return boardSize;
    }

    public Orientations takeDirection(){
        int input = takeInteger("Press 0 for horizontal, and 1 for vertical.");
        while(input != Orientations.HORIZONTAL.getValue() && input != Orientations.VERTICAL.getValue()) {
            display.printMessages("Incorrect orientation!");
            input = takeInteger("Press 0 for horizontal and 1 for vertical.");
        }
        if (input == Orientations.HORIZONTAL.getValue()) {return Orientations.HORIZONTAL;}
        else {return Orientations.VERTICAL;}
    }

}
