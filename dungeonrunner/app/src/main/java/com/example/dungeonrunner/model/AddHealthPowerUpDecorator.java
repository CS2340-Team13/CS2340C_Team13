package com.example.dungeonrunner.model;

import com.example.dungeonrunner.R;

public class AddHealthPowerUpDecorator extends PowerUpDecorator {

    public AddHealthPowerUpDecorator(Character player) {
        super(player);
        this.characterImageResource = R.drawable.medkit;
    }
    @Override public void PowerUp() {
        this.player.setPlayerHealth((this.player.getPlayerHealth() + 10));
    }

}
