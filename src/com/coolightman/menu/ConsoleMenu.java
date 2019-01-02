package com.coolightman.menu;

import com.coolightman.engine.Game;
import java.util.Scanner;

public class ConsoleMenu {

    public static void startConsoleMenu(){
        Game game = new Game();

        System.out.println(
                "*********\n" +
                " XO Game\n" +
                "*********\n" );

        boolean menuWorkWasDid;

        do {
            printConsoleMenu();
            menuWorkWasDid = showConsoleMenu(game);
        }
        while (!menuWorkWasDid);
    }

    private static void printConsoleMenu(){
        System.out.print(
                "MENU:\n" +
                        "1-Start\n" +
                        "2-Set players names\n" +
                        "3-Exit\n" +
                        ">");
    }

    private static boolean showConsoleMenu(Game game) {
        boolean menuWorkWasDid = false;

        String menuChoice = getMenuChoice();

        switch (menuChoice) {
            case "1":
                boolean wannaRepeat;

                do{
                    game.StartGame();
                    wannaRepeat = repeatGame(game);
                }
                while(wannaRepeat);

                menuWorkWasDid = true;
                break;

            case "2":
                setPlayersNames(game);
                break;

            case "3":
                System.out.println("Game end! Bye!");
                menuWorkWasDid = true;
                break;

            default:
                System.out.print(
                        "Choose another menu item!\n" +
                                ">");
        }
        return menuWorkWasDid;
    }

    private static String getMenuChoice() {
        String menuChoice;

        Scanner scanner = new Scanner(System.in);
        menuChoice = scanner.next();

        return menuChoice;
    }

    private static void setPlayersNames(Game game){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose Player 1 name:");

        String namePlayer1 = scanner.next();
        game.getPlayersList().get(0).setNamePlayer(namePlayer1);

        System.out.println("Choose Player 2 name:");

        String namePlayer2 = scanner.next();
        game.getPlayersList().get(1).setNamePlayer(namePlayer2);
    }

    private static boolean repeatGame(Game game){
        System.out.print(
                "Do you wan't repeat XO Game?\n" +
                        "1-Yes\n" +
                        "2-No. Exit.\n" +
                        ">");

        String menuChoice = getMenuChoice();

        boolean wannaRepeat = true;

        switch (menuChoice){
            case "1":
//               обнуляем все ячейки в поле
                for (int i=0; i<9; i++){
                    game.getPlayBoard().getCellList().get(i).setFigure("_");
                }
                break;

            case "2":
                System.out.println("Goodbye! See ya later =)");
                wannaRepeat = false;
                break;

            default:
                System.out.print(
                        "Choose another menu item!\n" +
                                ">");
        }
        return wannaRepeat;
    }
}
