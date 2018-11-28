package com.coolightman.engine;

import com.coolightman.model.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private String moveXY;
    private int boardSize = 3;
    private ArrayList<Player> playersList = new ArrayList<>();

    //    создание поля
    private Board playBoard = new Board(boardSize);

    public void StartGame(){
//        создание игроков
        Player player1 = new Player();
        playersList.add(player1);
        player1.setPlayerFigure("cross");

        Player player2 = new Player();
        playersList.add(player2);
        player2.setPlayerFigure("zero");

        System.out.println("****Game Begin.**** " +
                "\nChoose your move by \"XY\" format,\n" +
                "where X - number of line from [0:"+(boardSize-1)+"],\n" +
                "where Y - number of column from [0:"+(boardSize-1)+"]");

//************************************************************************************************************

//      основной игровой цикл
//      цикл работает пока не появиться победитель или будет ничья
        boolean haveWinner = false;
        do{
//          задаем очередность ходов игроков
            int[] playersMovesTurn = {0, 1, 0, 1, 0, 1, 0, 1, 0};
            int moveCounter=0;

//          цикл перебора очередности ходов игроков
            for (int playerMove:playersMovesTurn) {
                System.out.println("Player "+playersList.get(playerMove).getNamePlayer()+" do your move:");

//              метод получения хода игрока, его проверка и вставка в поле
                getMoveAndChangeCellFigure(playerMove);

//              рисуем получившееся поле
                playBoard.printBoard();
                moveCounter++;

//              проверяем наличие победителя
                if (checkWinner()){
                    haveWinner = true;
                    System.out.println("Congratulation! We have winner "+playersList.get(playerMove).getNamePlayer());
                    break;
                }
            }

//          проверяем на ничью
            if (moveCounter==9 && !checkWinner()){
                System.out.println("We have not winner =(");
                break;
            }
        }
        while (!haveWinner);
        System.out.println("Game End");
    }
//************************************************************************************************************
//  методы игрового цикла

    private void getMove(){
        Scanner scanner = new Scanner(System.in);
        this.moveXY = scanner.next();
    }

    private void getMoveAndChangeCellFigure(int playerNumb){

        boolean cellFigureChangeSuccess = false;
        do {
            getMove();
            if (checkCellExist()){

                int cellNumb = findCellByXYCords(moveXY);
                if (checkCellEmpty(cellNumb)){
                    playBoard.getCellList().get(cellNumb).setFigure(playersList.get(playerNumb).getPlayerFigure());
                    cellFigureChangeSuccess = true;
                }
            }
        } while (!cellFigureChangeSuccess);
    }

    private boolean checkCellExist(){
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

    private int findCellByXYCords(String moveXY){
        int cellNumb=0;
        for (int i = 0; i<9 ; i++){
            if (playBoard.getCellList().get(i).getCellXYName().equals(moveXY)) {
                cellNumb=i;
            }
        }
        return cellNumb;
    }

    private boolean checkCellEmpty(int cellNumb){
        boolean cellIsEmpty;
        if (playBoard.getCellList().get(cellNumb).getFigure().equals("_")){
            cellIsEmpty = true;
        }
        else {
            System.out.println("Cell is busy, try again:");
            cellIsEmpty = false;
        }
        return cellIsEmpty;
    }

    private boolean checkWinner(){
        boolean haveWinner = false;
        ArrayList<String> figureList = new ArrayList<>();
        for (int i=0; i<9; i++){
            figureList.add(playBoard.getCellList().get(i).getFigure());
        }

        if (figureList.get(0).equals(figureList.get(1)) && figureList.get(1).equals(figureList.get(2)) && !figureList.get(0).equals("_")){
            haveWinner = true;
        }
        if (figureList.get(0).equals(figureList.get(3)) && figureList.get(3).equals(figureList.get(6)) && !figureList.get(0).equals("_")){
            haveWinner = true;
        }
        if (figureList.get(0).equals(figureList.get(4)) && figureList.get(4).equals(figureList.get(8)) && !figureList.get(0).equals("_")){
            haveWinner = true;
        }
        if (figureList.get(1).equals(figureList.get(4)) && figureList.get(4).equals(figureList.get(7)) && !figureList.get(1).equals("_")){
            haveWinner = true;
        }
        if (figureList.get(2).equals(figureList.get(4)) && figureList.get(4).equals(figureList.get(6)) && !figureList.get(2).equals("_")){
            haveWinner = true;
        }
        if (figureList.get(2).equals(figureList.get(5)) && figureList.get(5).equals(figureList.get(8)) && !figureList.get(2).equals("_")){
            haveWinner = true;
        }
        if (figureList.get(3).equals(figureList.get(4)) && figureList.get(4).equals(figureList.get(5)) && !figureList.get(3).equals("_")){
            haveWinner = true;
        }
        if (figureList.get(6).equals(figureList.get(7)) && figureList.get(7).equals(figureList.get(8)) && !figureList.get(6).equals("_")){
            haveWinner = true;
        }

        return haveWinner;
    }
}
