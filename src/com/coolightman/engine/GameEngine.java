package com.coolightman.engine;

import com.coolightman.exceptions.XOBusyCellException;
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
        boolean haveWinner;
        boolean figureIsAddedOnBoard;
        int moveCounter = 0;
        Figure figureOfPlayer = Figure.O;

//        Work algorithm:
//        1. Receive X or O from turn of moves;
//        2. Get move from player;
//        3. Add player move on board;
//        4. check for win after 5 moves;
//        5. check for draw;

        Board.printBoardExample();
        do {
            figureOfPlayer = receiveFigureFromTurn(figureOfPlayer);

            do {
                int playerMove = getValidPlayerMove();
                figureIsAddedOnBoard = addPlayerMoveOnBoard(playerMove, figureOfPlayer);
            } while (!figureIsAddedOnBoard);

            Board.printBoard();
            moveCounter++;
            haveWinner = checkWinner(moveCounter, figureOfPlayer);
            haveWinner = checkDraw(moveCounter, haveWinner);
        }
        while (!haveWinner);

        System.out.println("Game End");
    }

    private static boolean checkDraw(int moveCounter, boolean haveWinner) {
        if (!haveWinner & moveCounter == 9) {
            System.out.println("Draw! We have not winner =(");
            return true;
        }
        else return haveWinner;
    }

    private static boolean checkWinner(int moveCounter, Figure figure) {
//        5 - min moves for check winner
        if (moveCounter >= 5 & checkWinnerCombination()) {
            String winPlayerName = receiveWinPlayerName(figure);
            System.out.println("Congratulation!"+winPlayerName+" is WIN!");
            return true;
        }
        else return false;
    }

    private static String receiveWinPlayerName(Figure figure) {
        String winPlayerName = null;

        if (figure.equals(Figure.X)){
            winPlayerName = players[0].getNamePlayer();
        }else if (figure.equals(Figure.O)){
            winPlayerName = players[1].getNamePlayer();
        }
        return winPlayerName;
    }

    private static boolean addPlayerMoveOnBoard(int playerMove, Figure figureOfPlayer) {
        try {
            checkingCellEmpty(playerMove);
            addFigureOnBoard(playerMove, figureOfPlayer);
            return true;
        } catch (XOBusyCellException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private static void addFigureOnBoard(int playerMove, Figure figure) {
        Board.getCellList().get(playerMove).setFigure(figure);
    }

    private static Figure receiveFigureFromTurn(Figure figureOfPlayer) {
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
    private static void checkingCellEmpty(int cellNumb) throws XOBusyCellException {

        if (!Board.getCellList().get(cellNumb).getFigure().equals(Figure.EMPTY)) {
            throw new XOBusyCellException();
        }
    }

    //  метод проверки наличия победителя
    private static boolean checkWinnerCombination() {
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
