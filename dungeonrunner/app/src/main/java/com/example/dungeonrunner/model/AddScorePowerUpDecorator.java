package com.example.dungeonrunner.model;

import com.example.dungeonrunner.R;

public class AddScorePowerUpDecorator extends PowerUpDecorator{
    public AddScorePowerUpDecorator(Character player) {
        super(player);
        this.characterImageResource = R.drawable.scoreboost;
    }
    @Override public void PowerUp() {
        this.player.setScore((this.player.getScore() + 10));
    }
}
