package com.example.dungeonrunner.viewModels;

import androidx.lifecycle.ViewModel;

import com.example.dungeonrunner.R;
import com.example.dungeonrunner.model.Player;


public class ConfigScreenViewModel extends ViewModel {

    private Player player = Player.getPlayer();
    public String submit(String playerName, String selectedCharacter, int difficultyId,
                         int startX, int startY) {
        if (playerName.isEmpty() || playerName.equals("null")) {
            return "Name can't be empty or null";
        }
        String gameDifficulty;
        if (difficultyId == R.id.radioButtonEasy) {
            gameDifficulty = "Easy";
        } else if (difficultyId == R.id.radioButtonMedium) {
            gameDifficulty = "Medium";
        } else if (difficultyId == R.id.radioButtonHard) {
            gameDifficulty = "Hard";
        } else {
            gameDifficulty = "Unspecified"; // or handle this case differently
        }

        player.setPlayerName(playerName);
        player.setSelectedCharacter(selectedCharacter);
        player.setGameDifficulty(gameDifficulty);
        player.setX(startX);
        player.setY(startY);
        return "";
    }


}