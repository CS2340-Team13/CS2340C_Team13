package com.example.dungeonrunner.viewModels;

import androidx.lifecycle.ViewModel;

import com.example.dungeonrunner.R;
import com.example.dungeonrunner.model.Player;

public class GameScreenViewModel extends ViewModel {

    private Player player = Player.getPlayer();
    public int getCharacterImageResource() {
        String selectedCharacter = player.getSelectedCharacter();
        int characterImageResource = 0;
        if (selectedCharacter.equals("character1")) {
            characterImageResource = R.drawable.character1_image;
        } else if (selectedCharacter.equals("character2")) {
            characterImageResource = R.drawable.character2_image;
        } else if (selectedCharacter.equals("character3")) {
            characterImageResource = R.drawable.character3_image;
        }
        return characterImageResource;
    }

    public int getHealth() {
        String gameDifficulty = player.getGameDifficulty();
        int health = 100;
        if (gameDifficulty.equals("Easy")) {
            health = 100;
        }else if (gameDifficulty.equals("Medium")) {
            health = 75;
        } else if (gameDifficulty.equals("Hard")) {
            health = 50;
        }
        return health;
    }

    public String getPlayerName() {
        return player.getPlayerName();
    }

    public String getGameDifficulty() {
        return player.getGameDifficulty();
    }
}