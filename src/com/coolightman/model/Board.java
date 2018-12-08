package com.coolightman.model;

import java.util.ArrayList;

public class Board {
    private int BOARD_SIZE = 3;
    private ArrayList<Cell> cellList= new ArrayList<>();

    public Board() {
        createBoard();
    }

    private void createBoard(){

        for (int i=0; i<BOARD_SIZE*BOARD_SIZE;i++){

                Cell cell = new Cell();
                cell.setCellName(""+i);
                cellList.add(cell);
        }
    }

    public void printBoard(){
        int cellListNumber = 0;

        for (int i=0; i<BOARD_SIZE;i++){

            for (int j =0;j<BOARD_SIZE;j++){

                cellList.get(cellListNumber).printCell();
                cellListNumber++;
            }
            System.out.println();
        }
    }

    public void printBoardExample(){
        System.out.println(
                 "|0|1|2|\n" +
                 "|3|4|5|\n" +
                 "|6|7|8|");
    }

    public ArrayList<Cell> getCellList() {
        return cellList;
    }
}
