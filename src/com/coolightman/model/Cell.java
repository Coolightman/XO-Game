package com.coolightman.model;

public class Cell {
    private String cellFigure;
    private String cellXYName;

    public Cell(){
        cellFigure = "_";
        drawCell();
    }

    public void setFigure(String figure) {
        if (figure.equals("cross")){
            cellFigure = "X";
        }
        else if (figure.equals("zero")){
            cellFigure = "O";
        }
    }

    public String getFigure() {
        return cellFigure;
    }

    public String getCellXYName() {
        return cellXYName;
    }

    public void setCellXYName(String cellXYName) {
        this.cellXYName = cellXYName;
    }

    public void drawCell(){
        System.out.print("|"+cellFigure+"|");
    }
}

