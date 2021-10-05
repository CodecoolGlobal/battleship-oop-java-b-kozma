package com.codecool.battleship;

import java.util.Arrays;

public class Battleship {
    public static void main(String [] args) {
        System.out.println("Battleship works");
        Game game = new Game();
        Arrays.stream(game.getBoard().getSquareList()).forEach(row ->
        {Arrays.stream(row).forEach(square -> System.out.print(square.toString()));
            System.out.println("\n");
        });
    }
}
