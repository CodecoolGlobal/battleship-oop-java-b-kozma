package com.codecool.battleship;

import com.codecool.battleship.board.Display;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Battleship {

    public static void main(String [] args) {

        Display display = new Display();
        // Instantiate a Game
        Game game = new Game();
        game.setBoardSize();
        display.printBoard(game.getBoard());
        game.setPlayers();
        game.placeShip(1);


    }


}
