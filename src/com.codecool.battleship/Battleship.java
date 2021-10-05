package com.codecool.battleship;

import com.codecool.battleship.board.Display;

public class Battleship {

    public static void main(String [] args) {

        Display display = new Display();
        // Instantiate a Game
        Game game = new Game();
        display.printWelcomeArt();
        game.setBoardSize();

        display.printBoard(game.getBoard());
        game.setPlayers();
        game.placeShip(1);


    }


}
