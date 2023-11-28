package com.example.dungeonrunner.model;

import com.example.dungeonrunner.R;
import com.example.dungeonrunner.viewModels.GameScreenViewModel;

public class SpeedBoostPowerUpDecorator extends PowerUpDecorator{

    public SpeedBoostPowerUpDecorator(Character player, GameScreenViewModel gameScreenViewModel) {
        super(player, gameScreenViewModel);
        this.characterImageResource = R.drawable.speedboost;
    }
    @Override public void PowerUp(){
        this.player.setmoveSpeed(this.player.getMoveSpeed() + 20);
    }
}
