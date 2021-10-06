package com.codecool.battleship;

import com.codecool.battleship.board.Display;
import com.codecool.battleship.board.GameSetup;

public class Battleship {

    public static void main(String [] args) {
        Display display = new Display();
        Input input = new Input(display);
        GameSetup gameSetup = new GameSetup(display, input);
        display.printWelcomeArt();


        display.printMenu();
        int userMenuOption = gameSetup.getUserMenuOption();
        gameSetup.execMenuOption(userMenuOption);
    }
}

