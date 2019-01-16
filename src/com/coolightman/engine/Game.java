package com.coolightman.engine;

import com.coolightman.model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private int BOARD_SIZE = 3;
    private int FULL_BOARD_SIZE = BOARD_SIZE * BOARD_SIZE;
    private ArrayList<Player> playersList = new ArrayList<>();

    private Board playBoard = new Board();

    public Game() {
        Player player1 = new Player("Player_1", Figure.X);
        playersList.add(player1);

        Player player2 = new Player("Player_2", Figure.O);

        playersList.add(player2);
    }

    public void StartGame() {

//      задаем очередность ходов игроков
        int[] playersMoveTurn = playersMoveTurnBuilder();

        System.out.println(
                "Choose your move by this format:");
        playBoard.printBoardExample();
        System.out.println();
        System.out.println("****Game START**** ");

//      основной игровой цикл
//      цикл работает пока не появится победитель или не будет ничья
        boolean haveWinner = false;
        do {
//          счетчик "успешных" ходов
            int moveCounter = 0;

//          цикл перебора очередности ходов игроков
            for (int playingPlayer : playersMoveTurn) {
                System.out.println(playersList.get(playingPlayer).getNamePlayer() + " do your move:");

                getMoveAndChangeCellFigure(playingPlayer);
                moveCounter++;

                playBoard.printBoard();
                System.out.println();

                if (checkWinner()) {
                    haveWinner = true;
                    System.out.println("Congratulation! " + playersList.get(playingPlayer).getNamePlayer() + " is WIN!");
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

    //  методы игрового цикла
//  метод получения хода игрока из командной строки
    private String getPlayerMove() {
        String move;
        Scanner scanner = new Scanner(System.in);
        move = scanner.next();
        return move;
    }

    //  метод получения и обработки хода игрока, его проверка и вставка в соотв ячейку фигуры игрока
    private void getMoveAndChangeCellFigure(int playerNumb) {

//      цикл требует от игрока хода пока не будет успешно заменена фигура в какой-либо ячейке
        boolean figureInCellChangedSuccess = false;
        do {
            String playerMove = getPlayerMove();

            if (checkCellExist(playerMove)) {

                int cellNumb = findCellByCords(playerMove);

                if (checkingCellEmpty(cellNumb)) {

//                  запись фигуры играющего игрока в соотв-ую ячейку
                    playBoard.getCellList().get(cellNumb).setFigure(playersList.get(playerNumb).getFigurePlayer());
                    figureInCellChangedSuccess = true;
                }
            }
        } while (!figureInCellChangedSuccess);
    }

    //  метод проверки на наличие по таким координатам ячейки на поле
    private boolean checkCellExist(String move) {
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
    private int findCellByCords(String move) {
        int cellNumb = 0;

        for (int i = 0; i < 9; i++) {
            String cellNumber = Integer.toString(playBoard.getCellList().get(i).getCellName());
            if (cellNumber.equals(move)){
                cellNumb = i;
            }
        }

        return cellNumb;
    }

    //  метод проверка "занятости" ячейки
    private boolean checkingCellEmpty(int cellNumb) {
        boolean cellIsEmpty;

        if (playBoard.getCellList().get(cellNumb).getFigure().equals(Figure.EMPTY)) {
            cellIsEmpty = true;
        } else {
            System.out.println("Cell is busy, try again:");
            cellIsEmpty = false;
        }

        return cellIsEmpty;
    }

    //  метод проверки наличия победителя
    private boolean checkWinner() {
        boolean haveWinner = false;

//      выйгрышные комбинации
        int[][] winVar = {{0, 1, 2}, {0, 3, 6}, {0, 4, 8}, {1, 4, 7}, {2, 4, 6}, {2, 5, 8}, {3, 4, 5}, {6, 7, 8}};

        ArrayList<Figure> figureList = new ArrayList<>();

//      создание списка текущих фигур
        for (int i = 0; i < FULL_BOARD_SIZE; i++) {
            figureList.add(playBoard.getCellList().get(i).getFigure());
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
    private boolean checkWinnerComb(int fCell, int sCell, int tCell, ArrayList<Figure> figureList) {

        boolean haveWinnerHelp = false;

        if (figureList.get(fCell).equals(figureList.get(sCell)) && figureList.get(sCell).equals(figureList.get(tCell)) && !figureList.get(fCell).equals(Figure.EMPTY)) {
            haveWinnerHelp = true;
        }

        return haveWinnerHelp;
    }

    //  метод создания массива очередности ходов игроков
    private int[] playersMoveTurnBuilder() {
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

    public ArrayList<Player> getPlayersList() {
        return playersList;
    }

    public Board getPlayBoard() {
        return playBoard;
    }
}