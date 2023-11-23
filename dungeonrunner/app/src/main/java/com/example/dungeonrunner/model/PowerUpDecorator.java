package com.example.dungeonrunner.model;

public abstract class PowerUpDecorator extends Character {
    protected Character player;

    public PowerUpDecorator (Character player){
        this.player = player;
    }

    // to be implemented in the concrete classes
    public void PowerUp(){
    }
}
