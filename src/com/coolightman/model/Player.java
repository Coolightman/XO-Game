package com.coolightman.model;

public class Player {
    private String namePlayer;
    private final String figurePlayer;

    public Player(String namePlayer, String figurePlayer) {
        this.namePlayer = namePlayer;
        this.figurePlayer = figurePlayer;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public String getFigurePlayer() {
        return figurePlayer;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }
}
