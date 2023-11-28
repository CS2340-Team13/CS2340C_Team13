package com.example.dungeonrunner.model;

import com.example.dungeonrunner.R;
import com.example.dungeonrunner.viewModels.GameScreenViewModel;

public class AddScorePowerUpDecorator extends PowerUpDecorator{
    public AddScorePowerUpDecorator(Character player, GameScreenViewModel gameScreenViewModel) {
        super(player, gameScreenViewModel);
        this.characterImageResource = R.drawable.scoreboost;
    }
    @Override public void PowerUp() {
        this.player.setScore((this.player.getScore() + 10));
    }
}
