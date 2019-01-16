package com.coolightman.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void getNamePlayer() {
        String initialValue = "Player_1";
        String expectedValue = "Player_1";

        Player player = new Player(initialValue, Figure.X);
        String arrivedValue = player.getNamePlayer();
        assertEquals(expectedValue, arrivedValue);
    }

    @Test
    public void getFigurePlayer() {
        Figure initialValue = Figure.X;
        Figure expectedValue = Figure.X;

        Player player = new Player("Player_1", initialValue);
        Figure arrivedValue = player.getFigurePlayer();
        assertEquals(expectedValue, arrivedValue);
    }

    @Test
    public void setNamePlayer() {
        String initialValue = "Player_1";
        String expectedValue = "Vasya";

        Player player = new Player(initialValue, Figure.X);
        player.setNamePlayer(expectedValue);
        String arrivedValue = player.getNamePlayer();
        assertEquals(expectedValue, arrivedValue);

    }
}