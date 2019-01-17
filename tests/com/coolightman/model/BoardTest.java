package com.coolightman.model;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void getCellList() {
        int expectedCellListSize = 9;

        Board.createBoard();
        ArrayList<Cell> cellArrayList = Board.getCellList();
        int actualCellListSize = cellArrayList.size();
        assertEquals(expectedCellListSize, actualCellListSize);
    }

    @Test
    public void getFullBoardSize() {
        int expectedFullBoardSize = 9;

        int actualFullBoardSize = Board.getFullBoardSize();
        assertEquals(expectedFullBoardSize, actualFullBoardSize);

    }
}