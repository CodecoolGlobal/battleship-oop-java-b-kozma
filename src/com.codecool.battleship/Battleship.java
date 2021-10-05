package com.codecool.battleship;

import com.codecool.battleship.board.Square;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Battleship {
    public static void main(String [] args) {

        // Instantiate a Game
        Game game = new Game();
        game.setBoardSize();
        game.setPlayers();
        game.placeShip(1);


        // Test Assets:
        /// Print Square [][] board;
        Arrays.stream(game.getBoard().getSquareList()).forEach(row ->
        {Arrays.stream(row).forEach(square -> System.out.print(square.toString()));
            System.out.println("\n");
        });

    }


}
