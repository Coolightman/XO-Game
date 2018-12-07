package com.coolightman.model;

public class Cell {
    private String cellFigure;
    private String cellXYCords;

    Cell(){
        cellFigure = "_";
    }

    public void setFigure(String figure) {
        this.cellFigure = figure;
    }

    public String getFigure() {
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
