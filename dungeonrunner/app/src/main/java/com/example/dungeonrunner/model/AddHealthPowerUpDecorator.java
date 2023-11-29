package com.example.dungeonrunner.model;

import com.example.dungeonrunner.R;
import com.example.dungeonrunner.viewModels.GameScreenViewModel;

public class AddHealthPowerUpDecorator extends PowerUpDecorator {

    //creates a new AddHealthPowerUpDecortator.
    public AddHealthPowerUpDecorator(Character player, GameScreenViewModel gameScreenViewModel) {
        super(player, gameScreenViewModel);
        this.characterImageResource = R.drawable.medkit;
    }
    //give the player 10 extra life
    @Override public void PowerUp() {
        int newHealth = this.gameScreenViewModel.getHealthLiveData().getValue() + 10;
        this.player.setPlayerHealth(newHealth);
        gameScreenViewModel.getHealthLiveData().postValue(newHealth);
    }
  
    //needed to complete Junit without mocking
    public void testPowerUp() {
        int newHealth = this.gameScreenViewModel.getHealthLiveData().getValue() + 10;
        this.player.setPlayerHealth(newHealth);
    }

}
