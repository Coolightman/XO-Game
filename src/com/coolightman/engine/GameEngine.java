package com.coolightman.engine;

import com.coolightman.model.Board;
import com.coolightman.model.Figure;
import com.coolightman.model.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class GameEngine {
    private static Player[] players = new Player[2];
    private static int FULL_BOARD_SIZE = Board.getFullBoardSize();

    public static void start(){
        createModels();
        gameProcess();
    }

    private static void gameProcess() {
        Figure[] turnXOfigure = {Figure.X, Figure.O, Figure.X, Figure.O, Figure.X, Figure.O, Figure.X, Figure.O, Figure.X};
        Board.printBoardExample();

//      основной игровой цикл
//      цикл работает пока не появится победитель или не будет ничья
        boolean haveWinner = false;
        do {
//          счетчик "успешных" ходов
            int moveCounter = 0;

//          цикл перебора очередности ходов игроков
            for (Figure figureOfPlayer : turnXOfigure) {
                printCurrentPlayerText(figureOfPlayer);

                getMoveAndChangeCellFigure(figureOfPlayer);
                moveCounter++;

                Board.printBoard();
                System.out.println();

                if (moveCounter>4 & checkWinner()) {
                    haveWinner = true;
                    System.out.println("Congratulation! ... is WIN!");
                    break;
                }
            }
//          проверяем на ничью
            if (moveCounter == 9 && !haveWinner) {
                System.out.println("We have not winner =(");
                break;
            }
        }
        while (!haveWinner);
        System.out.println("Game End\n");
    }

    private static void printCurrentPlayerText(Figure figure) {
        if (figure.equals(Figure.X)){
            System.out.println(players[0].getNamePlayer() + " do your move:");
        }
        if (figure.equals(Figure.O)){
            System.out.println(players[1].getNamePlayer() + " do your move:");
        }
    }

    private static void createModels() {
        players[0] = new Player("Player_1", Figure.X);
        players[1] = new Player("Player_2", Figure.O);
        Board.createBoard();
    }

    //  метод получения и обработки хода игрока, его проверка и вставка в соотв ячейку фигуры игрока
    private static void getMoveAndChangeCellFigure(Figure playerFigure) {

//      цикл требует от игрока хода пока не будет успешно заменена фигура в какой-либо ячейке
        boolean figureInCellChangedSuccess = false;
        do {
            String playerMove = getPlayerMove();

            if (checkCellExist(playerMove)) {

                int cellNumb = findCellByCords(playerMove);

                if (checkingCellEmpty(cellNumb)) {

//                  запись фигуры играющего игрока в соотв-ую ячейку
                    Board.getCellList().get(cellNumb).setFigure(playerFigure);
                    figureInCellChangedSuccess = true;
                }
            }
        } while (!figureInCellChangedSuccess);
    }

    private static String getPlayerMove(){
        String move;
        Scanner scanner = new Scanner(System.in);
        move = scanner.next();
        return move;
    }

    //  метод проверки на наличие по таким координатам ячейки на поле
    private static boolean checkCellExist(String move) {
        boolean cellExist;

        if (move.equals("0") || move.equals("1") || move.equals("2") || move.equals("3") || move.equals("4")
                || move.equals("5") || move.equals("6") || move.equals("7") || move.equals("8")) {
            cellExist = true;
        } else {
            System.out.println("Wrong coords, try again:");
            cellExist = false;
        }

        return cellExist;
    }

    //  метод поиска ячейки соотв-ей ходу игрока
    private static int findCellByCords(String move) {
        int cellNumb = 0;

        for (int i = 0; i < 9; i++) {
            String cellNumber = Integer.toString(Board.getCellList().get(i).getCellName());
            if (cellNumber.equals(move)){
                cellNumb = i;
            }
        }

        return cellNumb;
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

    //  метод создания массива очередности ходов игроков
    private static int[] playersMoveTurnBuilder() {
        int[] playersMovesTurn = new int[FULL_BOARD_SIZE];
        int playerMoveStep = 1;

        for (int i = 0; i < FULL_BOARD_SIZE; i++) {

            if (playerMoveStep == 0) {
                playerMoveStep = 1;
            } else {
                playerMoveStep = 0;
            }
            playersMovesTurn[i] = playerMoveStep;
        }

        return playersMovesTurn;
    }
}
