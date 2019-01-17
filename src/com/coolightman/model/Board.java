package com.coolightman.model;

import java.util.ArrayList;

public class Board {
    private static int BOARD_SIZE = 3;
    private static int FULL_BOARD_SIZE = BOARD_SIZE*BOARD_SIZE;
    private static ArrayList<Cell> cellList = new ArrayList<>();

    public static void createBoard() {

        for (int i = 0; i < FULL_BOARD_SIZE; i++) {
            Cell cell = new Cell(i);
            cellList.add(cell);
        }
    }

    public static void printBoard() {
        int cellListNumber = 0;

        for (int i = 0; i < BOARD_SIZE; i++) {

            for (int j = 0; j < BOARD_SIZE; j++) {

                cellList.get(cellListNumber).printCell();
                cellListNumber++;
            }
            System.out.println();
        }
    }

    public static void printBoardExample() {
        System.out.println(
                "|0|1|2|\n" +
                "|3|4|5|\n" +
                "|6|7|8|");
    }

    public static void clean(){
        for (int i = 0; i < FULL_BOARD_SIZE; i++) {
            cellList.get(i).setFigure(Figure.EMPTY);
        }
    }

    public static ArrayList<Cell> getCellList() {
        return cellList;
    }

    public static int getFullBoardSize() {
        return FULL_BOARD_SIZE;
    }
}
