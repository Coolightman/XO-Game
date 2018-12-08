package com.coolightman.model;

public class Player {
    private String namePlayer;
    private Figure figurePlayer;
    private static int NUMBER_OF_PLAYER=0;

    public Player() {
        NUMBER_OF_PLAYER++;//
        namePlayer = "Player_"+NUMBER_OF_PLAYER;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    public void setPlayerFigure(Figure figure){
        this.figurePlayer = figure;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public Figure getPlayerFigure() {
        return figurePlayer;
    }
}
