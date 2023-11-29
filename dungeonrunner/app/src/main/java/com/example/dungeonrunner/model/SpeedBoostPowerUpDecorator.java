package com.example.dungeonrunner.model;

import com.example.dungeonrunner.R;
import com.example.dungeonrunner.viewModels.GameScreenViewModel;

public class SpeedBoostPowerUpDecorator extends PowerUpDecorator{
    //creates a SpeedBoostPowerUpDecorator
    public SpeedBoostPowerUpDecorator(Character player, GameScreenViewModel gameScreenViewModel) {
        super(player, gameScreenViewModel);
        this.characterImageResource = R.drawable.speedboost;
    }
    //adds 20 move speed to the player.
    @Override public void PowerUp(){
        this.player.setmoveSpeed(this.player.getMoveSpeed() + 20);
    }
}
