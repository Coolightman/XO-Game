package com.coolightman.model;

public class Cell {
    private Figure cellFigure;
    private String cellXYCords;

    Cell(){
        cellFigure = Figure.N;
    }

    public void setFigure(Figure figure) {
        this.cellFigure = figure;
    }

    public Figure getFigure() {
        return cellFigure;
    }

    public String getCellXYName() {
        return cellXYCords;
    }

    void setCellXYName(String cellXYName) {
        this.cellXYCords = cellXYName;
    }

    void printCell(){
        System.out.print("|"+cellFigure+"|");
    }
}
