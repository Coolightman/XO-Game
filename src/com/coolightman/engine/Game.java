package com.coolightman.engine;

import com.coolightman.model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private int BOARD_SIZE=3;
    private int FULL_BOARD_SIZE = BOARD_SIZE*BOARD_SIZE;
    private ArrayList<Player> playersList = new ArrayList<>();
//      создание поля

    private Board playBoard = new Board();

    public Game(){
//      создание игроков
        Player player1 = new Player();
        playersList.add(player1);
        player1.setPlayerFigure(Figure.X);

        Player player2 = new Player();
        playersList.add(player2);
        player2.setPlayerFigure(Figure.O);
    }

    //  основной игровой метод
    public void StartGame(){

//      задаем очередность ходов игроков
        int[] playersMoveTurn = playersMoveTurnBuilder();

        System.out.println(
                "Choose your move by \"XY\" format\n" +
                        "where X - number of line from [0:2]\n" +
                        "where Y - number of column from [0:2]");
        playBoard.printBoardExample();
        System.out.println();
        System.out.println("****Game START**** ");

//      основной игровой цикл
//      цикл работает пока не появится победитель или не будет ничья
        boolean haveWinner = false;
        do{
//          счетчик "успешных" ходов
            int moveCounter=0;

//          цикл перебора очередности ходов игроков
            for (int playingPlayer:playersMoveTurn) {
                System.out.println("Player "+playersList.get(playingPlayer).getNamePlayer()+" do your move:");

                getMoveAndChangeCellFigure(playingPlayer);
                moveCounter++;

                clearConsole();

                playBoard.printBoard();
                System.out.println();

                if (checkWinner()){
                    haveWinner = true;
                    System.out.println("Congratulation! "+playersList.get(playingPlayer).getNamePlayer()+" is WIN!");
                    break;
                }
            }

//          проверяем на ничью
            if (moveCounter==9 && !haveWinner){
                System.out.println("We have not winner =(");
                break;
            }
        }
        while (!haveWinner);
        System.out.println("Game End\n");
    }

    //  методы игрового цикла
//  метод получения хода игрока из командной строки
    private String getPlayerMove(){
        String moveXY;
        Scanner scanner = new Scanner(System.in);
        moveXY = scanner.next();
        return moveXY;
    }

    //  метод получения и обработки хода игрока, его проверка и вставка в соотв ячейку фигуры игрока
    private void getMoveAndChangeCellFigure(int playerNumb){

//      цикл требует от игрока хода пока не будет успешно заменена фигура в какой-либо ячейке
        boolean figureInCellChangedSuccess = false;
        do {
            String playerXYMove = getPlayerMove();

            if (checkCellExist(playerXYMove)){

                int cellNumb = findCellByXYCords(playerXYMove);

                if (checkingCellEmpty(cellNumb)){

//                  запись фигуры играющего игрока в соотв-ую ячейку
                    playBoard.getCellList().get(cellNumb).setFigure(playersList.get(playerNumb).getPlayerFigure());
                    figureInCellChangedSuccess = true;
                }
            }
        } while (!figureInCellChangedSuccess);
    }

    //  метод проверки на наличие по таким координатам ячейки на поле
    private boolean checkCellExist(String moveXY){
        boolean cellExist;

        if (moveXY.equals("00")||moveXY.equals("10")||moveXY.equals("20")||moveXY.equals("01")||moveXY.equals("11")
                ||moveXY.equals("21")||moveXY.equals("02")||moveXY.equals("12")||moveXY.equals("22")){
            cellExist=true;
        }
        else {
            System.out.println("Wrong coords, try again:");
            cellExist = false;
        }

        return cellExist;
    }

    //  метод поиска ячейки соотв-ей ходу игрока
    private int findCellByXYCords(String moveXY){
        int cellNumb=0;

        for (int i = 0; i<9 ; i++){
            if (playBoard.getCellList().get(i).getCellXYName().equals(moveXY)) {
                cellNumb=i;
            }
        }

        return cellNumb;
    }

    //  метод проверка "занятости" ячейки
    private boolean checkingCellEmpty(int cellNumb){
        boolean cellIsEmpty;

        if (playBoard.getCellList().get(cellNumb).getFigure()==Figure.N){
            cellIsEmpty = true;
        }
        else {
            System.out.println("Cell is busy, try again:");
            cellIsEmpty = false;
        }

        return cellIsEmpty;
    }

    //  метод проверки наличия победителя
    private boolean checkWinner(){
        boolean haveWinner = false;

//      выйгрышные комбинации
        int winVar[][]= {{0,1,2}, {0,3,6}, {0,4,8}, {1,4,7}, {2,4,6}, {2,5,8}, {3,4,5}, {6,7,8}};

        ArrayList<Figure> figureList = new ArrayList<>();

//      создание списка текущих фигур
        for (int i=0; i<FULL_BOARD_SIZE; i++){
            figureList.add(playBoard.getCellList().get(i).getFigure());
        }

        for (int i = 0; i<8; i++){
            int fNumb = winVar[i][0];
            int sNumb = winVar[i][1];
            int tNumb = winVar[i][2];

            if (checkWinnerComb(fNumb, sNumb, tNumb, figureList)){
                haveWinner = true;
                break;
            }
        }

        return haveWinner;
    }

    //  метод проверки комбинации
    private boolean checkWinnerComb(int fCell, int sCell, int tCell, ArrayList<Figure> figureList){

        boolean haveWinnerHelp = false;

        if (figureList.get(fCell)==(figureList.get(sCell)) && figureList.get(sCell)==(figureList.get(tCell)) && figureList.get(fCell)!=Figure.N){
            haveWinnerHelp = true;
        }

        return haveWinnerHelp;
    }

    //  метод создания массива очередности ходов игроков
    private int[] playersMoveTurnBuilder(){
        int[] playersMovesTurn = new int[FULL_BOARD_SIZE];
        int playerMoveStep = 1;

        for (int i = 0; i < FULL_BOARD_SIZE; i++){

            if (playerMoveStep ==0){
                playerMoveStep = 1;
            }
            else {
                playerMoveStep = 0;
            }
            playersMovesTurn[i]=playerMoveStep;
        }

        return playersMovesTurn;
    }

    private static void clearConsole() {

    }

    public ArrayList<Player> getPlayersList() {
        return playersList;
    }

    public Board getPlayBoard() {
        return playBoard;
    }
}