package com.example.dungeonrunner.viewModels;

import androidx.lifecycle.ViewModel;

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

}
