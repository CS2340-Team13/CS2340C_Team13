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
        int health;
        if (difficultyId == R.id.radioButtonEasy) {
            health = 100;
        } else if (difficultyId == R.id.radioButtonMedium) {
            health = 75;
        } else if (difficultyId == R.id.radioButtonHard) {
            health = 50;
        } else {
            health = 0; // or handle this case differently
        }

        player.setName(playerName);
        player.setSelectedCharacter(selectedCharacter);
        player.setPlayerHealth(health);
        player.setX(startX);
        player.setY(startY);

        // DO STUFF WITH GAME DIFFICULTY

        return "";
    }


}