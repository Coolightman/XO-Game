package com.coolightman.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CellTest {

    @Test
    public void getFigure() {
        Figure expectedValue = Figure.EMPTY;
        int cellNumber = 1;

        Cell cell = new Cell(cellNumber);
        Figure arrivedValue = cell.getFigure();
        assertEquals(expectedValue, arrivedValue);
    }

    @Test
    public void setFigure() {
        Figure initialValue = Figure.O;
        Figure expectedValue = Figure.O;
        int cellNumber = 1;

        Cell cell = new Cell(cellNumber);
        cell.setFigure(initialValue);
        Figure arrivedValue = cell.getFigure();
        assertEquals(expectedValue, arrivedValue);
    }

    @Test
    public void getCellName() {
        int expectedValue = 1;
        Cell cell = new Cell(expectedValue);

        int arrivedValue = cell.getCellName();
        assertEquals(expectedValue, arrivedValue);
    }
}