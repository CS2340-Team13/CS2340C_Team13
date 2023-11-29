package com.example.dungeonrunner.model;

import com.example.dungeonrunner.viewModels.GameScreenViewModel;

public abstract class PowerUpDecorator extends Character {
    protected Character player;
    protected GameScreenViewModel gameScreenViewModel;
    //abstract constructor
    public PowerUpDecorator (Character player, GameScreenViewModel gameScreenViewModel){
        this.player = player;
        this.gameScreenViewModel = gameScreenViewModel;
    }

    // to be implemented in the concrete classes
    public void PowerUp(){
    }
}
