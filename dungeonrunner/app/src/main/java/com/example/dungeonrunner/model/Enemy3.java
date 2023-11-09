package com.example.dungeonrunner.model;

import com.example.dungeonrunner.R;

public class Enemy3 extends Character {
    private String name = "enemy3";
    private int x;
    private int y;
    private int width;
    private int height;


    public Enemy3 (String enemyT, int x, int y, int width, int height) {
        name = enemyT;
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.characterImageResource = R.drawable.enemy3;


    }

    @Override public String getName() {
        return name;
    }

    @Override public void setName(String playerName) {
        this.name = playerName;
    }

    @Override public int getX() {
        return x;
    }

    @Override public void setX(int x) {
        this.x = x;
    }

    @Override public int getY() {
        return y;
    }

    @Override public void setY(int y) {
        this.y = y;
    }

    @Override public int getHeight() {
        return height;
    }

    @Override public void setHeight(int height) {
        this.height = height;
    }

    @Override public int getWidth() {
        return width;
    }

    @Override public void setWidth(int width) {
        this.width = width;
    }


}
