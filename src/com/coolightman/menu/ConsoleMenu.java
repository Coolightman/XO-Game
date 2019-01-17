package com.coolightman.menu;

import com.coolightman.engine.GameEngine;

import java.util.Scanner;

public class ConsoleMenu {

    public static void startConsoleMenu() {
        System.out.println("XO Game");
        GameEngine.createPlayers();
        showMainMenu();
    }

    private static void showMainMenu() {
        GameEngine.createBoard();

        if (gameProcessWorking()) {
            showMainMenu();
        }
    }

    private static boolean gameProcessWorking() {
        printConsoleMenu();
        int menuChoice = getMenuChoice();

        switch (menuChoice) {
            case 1:
                boolean wannaRepeat;

                do {
                    GameEngine.start();
                    wannaRepeat = repeatGame();
                }
                while (wannaRepeat);
                return false;

            case 2:
                setPlayersNames();
                return true;

            case 3:
                System.out.println("Game end! Bye!");
                return false;

            default:
                System.out.print(
                        "Choose another menu item!\n" +
                                ">");
                return true;
        }
    }

    private static void printConsoleMenu() {
        System.out.print(
                "MENU:\n" +
                        "1-Start\n" +
                        "2-Set players names\n" +
                        "3-Exit\n" +
                        ">");
    }

    private static int getMenuChoice() {
        int menuChoice=-1;

        Scanner scanner = new Scanner(System.in);

        try {
            menuChoice = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Wrong menu format! Try again:\n" +
                    ">");
            getMenuChoice();
        }

        return menuChoice;
    }

    private static void setPlayersNames() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose Player_1 name:\n" +
                ">");
        String namePlayer1 = scanner.next();
        GameEngine.getPlayers()[0].setNamePlayer(namePlayer1);

        System.out.println("Choose Player_2 name:\n" +
                ">");
        String namePlayer2 = scanner.next();
        GameEngine.getPlayers()[1].setNamePlayer(namePlayer2);
    }

    private static boolean repeatGame() {
        System.out.print(
                "Do you wan't repeat XO Game?\n" +
                        "1-Yes\n" +
                        "2-No. Exit.\n" +
                        ">");

        int menuChoice = getMenuChoice();

        switch (menuChoice) {
            case 1:
                showMainMenu();
                return true;

            case 2:
                return false;

            default:
                System.out.print(
                        "Choose another menu item!\n" +
                                ">");
                return true;
        }
    }
}
