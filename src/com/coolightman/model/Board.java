package com.coolightman.model;

import java.util.ArrayList;

public class Board {
    private int boardSize;
    private ArrayList<Cell> cellList= new ArrayList<Cell>();

    public Board(int boardSize) {
        System.out.println("*******");
        System.out.println("XO GAME");
        System.out.println("*******");
        this.boardSize = boardSize;
        for (int i=0; i<boardSize;i++){
            for (int j =0;j<boardSize;j++){
                Cell cell = new Cell();
                cell.setCellXYName(""+i+j);
                cellList.add(cell);
            }
            System.out.println();
        }
    }

    public void printBoard(){
        int cellListNumber = 0;
        for (int i=0; i<boardSize;i++){
            for (int j =0;j<boardSize;j++){
                cellList.get(cellListNumber).drawCell();
                cellListNumber++;
            }
            System.out.println();
        }
    }

    public ArrayList<Cell> getCellList() {
        return cellList;
    }
}
