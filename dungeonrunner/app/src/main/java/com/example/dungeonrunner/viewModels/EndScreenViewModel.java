package com.example.dungeonrunner.viewModels;

import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.lifecycle.ViewModel;

import com.example.dungeonrunner.R;
import com.example.dungeonrunner.model.Leaderboard;
import com.example.dungeonrunner.model.Player;
import com.example.dungeonrunner.model.ScoreUnit;

import java.util.ArrayList;

public class EndScreenViewModel extends ViewModel {

    private Leaderboard leaderboard = Leaderboard.getLeaderboard();
    private Player player = Player.getPlayer();

    public ArrayList<ScoreUnit> getResults(int score) {
        leaderboard.addToList(player.getPlayerName(), score);
        leaderboard.sortList();
        return leaderboard.getResults();
    }
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
    public void setPosition(ImageView imageView) {

        int characterImageResource = getCharacterImageResource();
        imageView.setImageResource(characterImageResource);
    }
}
