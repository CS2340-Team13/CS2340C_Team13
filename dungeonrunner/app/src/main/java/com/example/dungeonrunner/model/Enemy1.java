package com.example.dungeonrunner.model;

import com.example.dungeonrunner.R;

public class Enemy1 extends Character {
    private int moveType;
    private boolean invertMove;

    public Enemy1 (String enemyT, int x, int y, int width, int height) {
        name = enemyT;
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.characterImageResource = R.drawable.enemy1;
    }
}
