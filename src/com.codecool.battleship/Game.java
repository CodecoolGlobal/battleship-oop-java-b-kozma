package com.codecool.battleship;

import com.codecool.battleship.board.Board;
import com.codecool.battleship.board.Display;

import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.codecool.battleship.board.*;
import com.codecool.battleship.util.ShipType;

public class Game {

    Board board;

    Player[] players = new Player[2];

    Display display = new Display();

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
        display.printMessages("TESTING: " + input.x + "-" + input.y + " is a good coordinate!");
        // TODO isValid(coordinates) —> while (!isValid(coord)) {input = takeInputCoord("Try again!");}
    }

    // VALIDATIONS
    public boolean isValid(Point input) {
        if (input == null) {
            display.printMessages("Input is null!");
            return false;
        } else if (isNegative(input)) {
            display.printMessages("At least one input is negative!");
            return false;
        } else if (isOutOfBounds(input)) {
            display.printMessages("At least one coordinate is out of bounds!");
            return false;
        } // More validations to be added.
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

    public String takeInputString(String message) {
        display.printMessages(message+"\n");
        Scanner scanInteger = new Scanner(System.in);
        String result = scanInteger.nextLine();
        return result;
    }

    public Point takeInputCoordinates(String message) {
        String input = takeInputString(message);
        while (!validFormat(input)) {
            input = takeInputString("Try again!");
        }
        int x = (int) input.toLowerCase().charAt(0) - 97; // A —> 0
        int y = Integer.parseInt(input.substring(1, input.length())) - 1; //
        return new Point(x, y);
    }



    // This method will naively produce subsequent references to squares based on orientation
    // Validate based on the output of this method
    // and DO NOT instantiate ship with the output if ANY of the squares are invalid
    // (i.e.: out of bounds or neighbouring or occupied)
    // Possibly move this to board
    private Square[] getShipCoordinates(Square headCoordinates, ShipType shipType, Orientations orientation) {
        int length = shipType.getLength();
        Square[] shipCoordinates = new Square[length];
        int xCoordinate;
        int yCoordinate;
        switch(orientation) {
            // Place ship squares to the right (east) of ship's head
            case HORIZONTAL:
                xCoordinate = Directions.EAST.getDirection().x;
                yCoordinate = Directions.EAST.getDirection().y;
                break;
            // Place ship squares to the down (south) of ship's head
            case VERTICAL:
                xCoordinate = Directions.SOUTH.getDirection().x;
                yCoordinate = Directions.SOUTH.getDirection().y;
                break;
            default:
                throw new IllegalArgumentException("Orientation must either be HORIZONTAL or VERTICAL");
        }
        shipCoordinates[0] = headCoordinates;
        for (int i = 1; i < length; i++) {
            shipCoordinates[i] = board.getSquare(headCoordinates.x + i * xCoordinate, headCoordinates.y + i * yCoordinate);
        }
        return shipCoordinates;
    }
}
