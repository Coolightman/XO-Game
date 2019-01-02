package com.coolightman.model;

public class Cell {
    private String cellFigure;
    private String cellCords;

    Cell(){
        cellFigure = "_";
    }

    public void setFigure(String figure) {
        this.cellFigure = figure;
    }

    public String getFigure() {
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
