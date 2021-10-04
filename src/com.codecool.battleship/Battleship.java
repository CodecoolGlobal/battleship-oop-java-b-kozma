package com.codecool.battleship;

import com.codecool.battleship.board.Square;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Battleship {
    public static void main(String [] args) {
        System.out.println("Battleship works");
        Game game = new Game();
        Arrays.stream(game.getBoard().getSquareList()).forEach(row ->
        {Arrays.stream(row).forEach(square -> System.out.print(square.toString()));
            System.out.println("\n");
        });

//        System.out.println(Arrays.toString(game.getBoard()));
    }
}
