package com.example.dungeonrunner.viewModels;

import android.widget.ImageView;

import androidx.lifecycle.ViewModel;

import com.example.dungeonrunner.R;
import com.example.dungeonrunner.model.Leaderboard;
import com.example.dungeonrunner.model.Player;
import com.example.dungeonrunner.model.ScoreUnit;

import java.util.ArrayList;

public class GameOverScreenViewModel extends ViewModel {
    private Leaderboard leaderboard = Leaderboard.getLeaderboard();
    private Player player = Player.getPlayer();

    public ArrayList<ScoreUnit> getResults(int score) {
        leaderboard.addToList(player.getName(), score);
        leaderboard.sortList();
        return leaderboard.getResults();
    }
    public void setPosition(ImageView imageView) {

        imageView.setImageResource(player.getCharacterImageResource());
    }
}
