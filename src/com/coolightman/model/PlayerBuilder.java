package com.coolightman.model;

public class PlayerBuilder {
    private String name;
    private String figure;

    public PlayerBuilder buildName(String val){
        this.name = val;
        return this;
    }

    public PlayerBuilder buildFigure(String val){
        this.figure = val;
        return this;
    }

    String getName() {
        return name;
    }

    String getFigure() {
        return figure;
    }

    public Player build(){
        return new Player(this);
    }
}
