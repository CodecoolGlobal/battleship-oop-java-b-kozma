package com.codecool.battleship.board;

import com.codecool.battleship.Game;
import com.codecool.battleship.Input;
import com.codecool.battleship.Player;

public class GameSetup {

    Display display;
    Input input;

    public GameSetup(Display display, Input input) {
        this.display = display;
        this.input = input;
    }

    public int getUserMenuOption() {
        display.printMainMenuOptions();
        return input.takeInteger("Please choose an option!");
    }

    public Player[] getPlayers(int boardSize) {
        String playerOne = input.takeString("Player 1's name: ");
        String playerTwo = input.takeString("Player 2's name: ");
        Player[] players = {new Player(playerOne, boardSize), new Player(playerTwo, boardSize)};
        return players;
    }

    public void execMenuOption(int menuOption) {
        switch(menuOption) {
            case 0:
                playGame();
                break;
            case 1:
                display.gameRules();
                display.printMenu();
                menuOption = getUserMenuOption();
                execMenuOption(menuOption);
            case 2:
                display.printExitMessage();
                System.exit(0);
        }
    }

    public void playGame() {
        int boardSize = input.getBoardSize();
        Board playerBoard = new Board(boardSize);
        Player[] players = getPlayers(boardSize);
        Game game = new Game(players, display, input);
        game.play();
    }

}
