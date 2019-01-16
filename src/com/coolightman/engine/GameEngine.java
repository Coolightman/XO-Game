package com.coolightman.engine;

import com.coolightman.exceptions.XOGameOutOfBoundsException;
import com.coolightman.model.Board;
import com.coolightman.model.Figure;
import com.coolightman.model.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class GameEngine {
    private static Player[] players = new Player[2];
    private static int FULL_BOARD_SIZE = Board.getFullBoardSize();

    public static void start() {
        createModels();
        runGameProcess();
    }

    private static void runGameProcess() {
        Board.printBoardExample();
        boolean haveWinner;
        int moveCounter = 0;
        Figure figureOfPlayer = Figure.O;

        do {
            figureOfPlayer = receiveFigureFromMove(figureOfPlayer);
            boolean figureIsAddedOnBoard;

            do {
                int playerMove = getValidPlayerMove();
//                TODO exception for busy cell
                if (checkingCellEmpty(playerMove)) {
                    Board.getCellList().get(playerMove).setFigure(figureOfPlayer);
                    figureIsAddedOnBoard = true;
                } else {
                    figureIsAddedOnBoard = false;
                }
            } while (!figureIsAddedOnBoard);

            Board.printBoard();
            moveCounter++;

            if (moveCounter > 4 & checkWinner()) {
                haveWinner = true;
                System.out.println("Congratulation! ... is WIN!");
                break;
            } else {
                haveWinner = false;
            }

//          проверяем на ничью
            if (!haveWinner & moveCounter == 9) {
                System.out.println("We have not winner =(");
                break;
            }
        }
        while (!haveWinner);
        System.out.println("Game End\n");
    }

    private static Figure receiveFigureFromMove(Figure figureOfPlayer) {
        if (figureOfPlayer.equals(Figure.X)) {
            figureOfPlayer = Figure.O;
        } else {
            figureOfPlayer = Figure.X;
        }
        printCurrentPlayerText(figureOfPlayer);
        return figureOfPlayer;
    }

    private static int getValidPlayerMove() {
        boolean validPlayerMove;
        int playerMove = -1;
        do {
            try {
                playerMove = getPlayerMove();
                validPlayerMove = true;
            } catch (XOGameOutOfBoundsException e) {
                System.out.println(e.getMessage());
                validPlayerMove = false;
            }
        } while (!validPlayerMove);

        return playerMove;
    }

    private static void printCurrentPlayerText(Figure figure) {
        if (figure.equals(Figure.X)) {
            System.out.println(players[0].getNamePlayer() + " do your move:");
        }
        if (figure.equals(Figure.O)) {
            System.out.println(players[1].getNamePlayer() + " do your move:");
        }
    }

    private static void createModels() {
        players[0] = new Player("Player_1", Figure.X);
        players[1] = new Player("Player_2", Figure.O);
        Board.createBoard();
    }

    private static int getPlayerMove() throws XOGameOutOfBoundsException {
        int playerMove = -1;
        boolean goodMoveFormat;

        do {
            try {
                Scanner scanner = new Scanner(System.in);
                playerMove = scanner.nextInt();
                goodMoveFormat = true;
            } catch (Exception e) {
                System.out.println("Wrong format! Try move again from 0 to 8:");
                goodMoveFormat = false;
            }

        } while (!goodMoveFormat);

        if (!(playerMove == 0 | playerMove == 1 | playerMove == 2
                | playerMove == 3 | playerMove == 4 | playerMove == 5
                | playerMove == 6 | playerMove == 7 | playerMove == 8)) {
            throw new XOGameOutOfBoundsException();
        }
        return playerMove;
    }

    //  метод проверка "занятости" ячейки
    private static boolean checkingCellEmpty(int cellNumb) {
        boolean cellIsEmpty;

        if (Board.getCellList().get(cellNumb).getFigure().equals(Figure.EMPTY)) {
            cellIsEmpty = true;
        } else {
            System.out.println("Cell is busy, try again:");
            cellIsEmpty = false;
        }

        return cellIsEmpty;
    }

    //  метод проверки наличия победителя
    private static boolean checkWinner() {
        boolean haveWinner = false;

//      выйгрышные комбинации
        int[][] winVar = {{0, 1, 2}, {0, 3, 6}, {0, 4, 8}, {1, 4, 7}, {2, 4, 6}, {2, 5, 8}, {3, 4, 5}, {6, 7, 8}};

        ArrayList<Figure> figureList = new ArrayList<>();

//      создание списка текущих фигур
        for (int i = 0; i < FULL_BOARD_SIZE; i++) {
            figureList.add(Board.getCellList().get(i).getFigure());
        }

        for (int i = 0; i < 8; i++) {
            int fNumb = winVar[i][0];
            int sNumb = winVar[i][1];
            int tNumb = winVar[i][2];

            if (checkWinnerComb(fNumb, sNumb, tNumb, figureList)) {
                haveWinner = true;
                break;
            }
        }

        return haveWinner;
    }

    //  метод проверки комбинации
    private static boolean checkWinnerComb(int fCell, int sCell, int tCell, ArrayList<Figure> figureList) {

        boolean haveWinnerHelp = false;

        if (figureList.get(fCell).equals(figureList.get(sCell))
                && figureList.get(sCell).equals(figureList.get(tCell))
                && !figureList.get(fCell).equals(Figure.EMPTY)) {
            haveWinnerHelp = true;
        }

        return haveWinnerHelp;
    }
}
