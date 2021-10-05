package com.codecool.battleship;

import com.codecool.battleship.board.*;
import com.codecool.battleship.util.ShipType;
import com.codecool.battleship.util.SquareStatus;

import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {

    Board board;

    Player[] players = new Player[2];

    Display display = new Display();

    Input input = new Input();

    // GAME STEPS METHODS

    public Board getBoard() {return board;}

    public void setBoardSize() {
        int size = input.takeInteger("Give us a board size between 10 and 20!");
        while (!input.isBetween(size, 10,20)) {
            display.printMessages("Between 10 and 20!");
            size = input.takeInteger("Try again!");
        }
        this.board = new Board(size);
    }

    public void setPlayers() {
        players[0] = new Player(
                input.takeString("Tell us your name, Player 1!"),
                1);

        players[1] = new Player(
                input.takeString("Tell us your name, Player 2!"),
                2);

        System.out.println("Players are all set!\nCaptain " +
                players[0].getName() + " versus Captain " + players[1].getName());
    }


    public void placeShip(int player) {
        Point point = input.takeCoordinates("Give coordinates!");
        display.printMessages("TESTING: " + point.x + "-" + point.y + " is a well formatted coordinate!");
        if (input.isValid(board, point)) {
            if (input.isEmpty(board, point)){
                // Ship Placement Should be Executed Here!
                display.printMessages("Ship Placement should be executed here!");
            };
        };
    }


    // This method will naively produce subsequent references to squares based on orientation
    // Validate based on the output of this method
    // and DO NOT instantiate ship with the output if ANY of the squares are invalid
    // (i.e.: out of bounds or neighbouring or occupied)
    // Possibly move this to board
    private Square[] createShipCoordinates(Square headCoordinates, ShipType shipType, Orientations orientation) {
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
