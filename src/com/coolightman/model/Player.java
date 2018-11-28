package com.coolightman.model;

import java.util.Scanner;

public class Player {
    private String namePlayer;
    private String figurePlayer;
    private static int numberPlayer=0;

    public Player() {
        numberPlayer++;
        System.out.println("Choose Player "+numberPlayer+" Name:");
        Scanner scanner = new Scanner(System.in);
        this.namePlayer = scanner.next();
    }

    public void setPlayerFigure(String figure){
        this.figurePlayer = figure;
        String charFigure;
        if (figure.equals("cross")){
            charFigure = "<X>";
        }
        else if(figure.equals("zero")){
            charFigure = "<O>";
        }
        else charFigure = null;
        System.out.println("Player "+namePlayer+" gets figure "+charFigure);
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public String getPlayerFigure() {
        return figurePlayer;
    }
}
