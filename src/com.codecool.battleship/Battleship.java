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
        int size = takeInputInteger("Give a table size between 10 and 20!");
        Game game = new Game(size);


        // Test Assets:
        /// Print Square [][] board;
        Arrays.stream(game.getBoard().getSquareList()).forEach(row ->
        {Arrays.stream(row).forEach(square -> System.out.print(square.toString()));
            System.out.println("\n");
        });

    }

    public static String takeInputString(String message) {
        System.out.println(message + "\n");
        Scanner takeIn = new Scanner(System.in);
        return takeIn.nextLine();
    }

    public static int takeInputInteger(String message) {
        System.out.println(message + "\n");
        Scanner takeIn = new Scanner(System.in);
        return takeIn.nextInt();
    }
}
