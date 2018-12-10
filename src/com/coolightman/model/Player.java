package com.coolightman.model;

public class Player {
    private String namePlayer;
    private Figure figurePlayer;

    Player(PlayerBuilder playerBuilder) {
        this.namePlayer = playerBuilder.getName();
        this.figurePlayer = playerBuilder.getFigure();
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public Figure getPlayerFigure() {
        return figurePlayer;
    }
}
