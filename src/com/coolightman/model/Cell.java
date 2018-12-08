package com.coolightman.model;

public class Cell {
    private Figure cellFigure;
    private String cellCords;

    Cell(){
        cellFigure = Figure.N;
    }

    public void setFigure(Figure figure) {
        this.cellFigure = figure;
    }

    public Figure getFigure() {
        return cellFigure;
    }

    public String getCellName() {
        return cellCords;
    }

    void setCellName(String cellName) {
        this.cellCords = cellName;
    }

    void printCell(){
        System.out.print("|"+cellFigure+"|");
    }
}
