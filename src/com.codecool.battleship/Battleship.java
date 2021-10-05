package com.codecool.battleship;

import com.codecool.battleship.board.Display;
import com.codecool.battleship.board.Square;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Battleship {

    Display display;
    Input input;

    public static void main(String [] args) {
        System.out.println("Battleship works");
        Game game = new Game();
        Arrays.stream(game.getBoard().getSquareList()).forEach(row -> {
            Arrays.stream(row).forEach(square -> System.out.print(square.toString()));
            System.out.println("\n");
        });

    }
}
