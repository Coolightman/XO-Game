package com.coolightman.model;

public class PlayerBuilder {
    private String name;
    private Figure figure;

    public PlayerBuilder buildName(String val){
        this.name = val;
        return this;
    }

    public PlayerBuilder buildFigure(Figure val){
        this.figure = val;
        return this;
    }

    String getName() {
        return name;
    }

    Figure getFigure() {
        return figure;
    }

    public Player build(){
        return new Player(this);
    }
}
