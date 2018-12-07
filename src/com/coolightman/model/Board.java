package com.coolightman.model;

import java.util.ArrayList;

public class Board {
    private int BOARD_SIZE = 3;
    private ArrayList<Cell> cellList= new ArrayList<>();

    public Board() {
        createBoard();
    }

    private void createBoard(){

        for (int i=0; i<BOARD_SIZE;i++){

            for (int j =0;j<BOARD_SIZE;j++){

                Cell cell = new Cell();
                cell.setCellXYName(""+i+j);
                cellList.add(cell);
            }
        }
    }

    public void printBoard(){
        int cellListNumber = 0;

        for (int i=0; i<BOARD_SIZE;i++){

            for (int j =0;j<BOARD_SIZE;j++){

                cellList.get(cellListNumber).printCell();
                cellListNumber++;
            }
            System.out.print("\t|"+i+"0|"+i+"1|"+i+"2|\n");
//            System.out.println();
        }
    }

    public void printBoardExample(){
        System.out.println(
                "|00|01|02|\n" +
                        "|10|11|12|\n" +
                        "|20|21|22|");
    }

    public ArrayList<Cell> getCellList() {
        return cellList;
    }
}
