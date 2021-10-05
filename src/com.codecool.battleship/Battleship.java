package com.codecool.battleship;

import com.codecool.battleship.board.Display;
import com.codecool.battleship.controller.Controller;

public class Battleship {

    public static void main(String [] args) {
        Display display = new Display();
        Input input = new Input(display);
        Controller controller = new Controller(display, input);

        display.printWelcomeArt();
        display.printMenu();
        int userMenuOption = controller.getUserMenuOption();
        controller.execMenuOption(userMenuOption);
    }
}
