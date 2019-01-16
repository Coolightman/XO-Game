package com.coolightman.model;

public class Player {
    private String namePlayer;
    private final Figure figurePlayer;

    public Player(String namePlayer, Figure figurePlayer) {
        this.namePlayer = namePlayer;
        this.figurePlayer = figurePlayer;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public Figure getFigurePlayer() {
        return figurePlayer;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }
}
