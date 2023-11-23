package com.example.dungeonrunner.model;

import com.example.dungeonrunner.R;

public class SpeedBoostPowerUpDecorator extends PowerUpDecorator{

    public SpeedBoostPowerUpDecorator(Character player) {
        super(player);
        this.characterImageResource = R.drawable.speedboost;
    }
    @Override public void PowerUp(){
        this.player.setmoveSpeed(this.player.getMoveSpeed() + 10);
    }
}
