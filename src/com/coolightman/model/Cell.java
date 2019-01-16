package com.coolightman.model;

public class Cell {
    private Figure cellFigure;
    private final int cellNumber;

    Cell(int cellNumber) {
        cellFigure = Figure.EMPTY;
        this.cellNumber = cellNumber;
    }

    public void setFigure(Figure figure) {
        this.cellFigure = figure;
    }

    public Figure getFigure() {
        return cellFigure;
    }

    public int getCellName() {
        return cellNumber;
    }

    void printCell() {
        System.out.print("|" + cellFigure + "|");
    }
}
