package com.codecool.battleship.board;

public class Display {

    public void printMenu() {
        System.out.println("Battleship is loading...");
        System.out.println("""

                         .---.
                        /_\\_/_\\
                        /o   o\\
                     __(=  "  =)__
                      //\\'-=-'/\\\\
                         )   (_
                        /      `"=-._
                       /       \\     ``"=.
                      /  /   \\  \\         `=..--.
                  ___/  /     \\  \\___      _,  , `\\
                 `-----' `""\""`'-----``""\"`  \\  \\_/ \s
                  The Real Navy Seals         `-`   \s
                """);
    }

    public void printMessages(String message) {
        System.out.println(message);
    }

    public void printMainMenuOptions() {
        System.out.println("press: \n" +
               " \t 0 - Play" +
                "\t 1 - Print game rules" +
                "\t 2 - Exit the game" );
    }

    public void printExitMessage () {
        System.out.println("Goodbye!");
    }

    public void gameRules () {
        System.out.println("The object of Battleship is to try and sink all of the other player's before " +
                "they sink all of your ships. All of the other player's ships are somewhere on his/her board. " +
                "You try and hit them by calling out the coordinates of one of the squares on the board.");
    }

}
