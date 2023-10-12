package com.example.dungeonrunner;

import org.junit.Test;
import com.example.dungeonrunner.model.*;
import com.example.dungeonrunner.viewModels.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class Sprint2Tests {
    @Test
    public void correctEasyHealth() {
        Player player = Player.getPlayer();
        player.setGameDifficulty("Easy");
        GameScreenViewModel mViewModel = new GameScreenViewModel();
        assertEquals(mViewModel.getHealth(), 100);
    }

    @Test
    public void correctMediumHealth() {
        Player player = Player.getPlayer();
        player.setGameDifficulty("Medium");
        GameScreenViewModel viewModel = new GameScreenViewModel();
        assertEquals(viewModel.getHealth(), 75);
    }

    @Test
    public void correctDifficultHealth() {
        Player player = Player.getPlayer();
        player.setGameDifficulty("Hard");
        GameScreenViewModel viewModel = new GameScreenViewModel();
        assertEquals(viewModel.getHealth(), 50);
    }

    @Test
    public void nameNotNull() {
        ConfigScreenViewModel mViewModel = new ConfigScreenViewModel();
        assertEquals(mViewModel.submit("", "character1", 1, 0, 0),
                "Name can't be empty or null");
    }

    @Test
    public void playerIsSingleton() {
        Player player1 = Player.getPlayer();
        player1.setX(100);
        player1.setY(200);

        Player player2 = Player.getPlayer();

        int [] player1Coords = {player1.getX(), player1.getY()};
        int [] player2Coords = {player2.getX(), player2.getY()};

        assertArrayEquals(player1Coords, player2Coords);
    }
    @Test
    public void submissionValid() {
        ConfigScreenViewModel mViewModel = new ConfigScreenViewModel();
        String result = mViewModel.submit("", "selectedCharacter", 0, 0, 0);
        assertFalse(result.equals(""));
    }

    @Test
    public void correctTopScorer() {
        Leaderboard tf = new Leaderboard();
        tf.addToList("ted", 15);
        tf.addToList("dave", 19);
        tf.addToList("ned", 17);
        tf.addToList("tef", 12);
        tf.addToList("david", 20);
        tf.addToList("ash", 16);
        tf.sortList();
        assertEquals(tf.getResults().get(0).getScore(), 20);
    }

    @Test
    public void scoreUnitCompare() {
        ScoreUnit a = new ScoreUnit("sheila", 30);
        ScoreUnit b = new ScoreUnit("paula", 55);
        assertFalse(a.isBigger(a,b));
    }

    @Test
    public void populatesArray_isCorrect() {
        Leaderboard tf = new Leaderboard();
        tf.addToList("ted", 15);
        tf.addToList("dave", 19);
        tf.addToList("ned", 17);
        tf.addToList("tef", 12);
        tf.addToList("david", 20);
        tf.addToList("ash", 16);
        assertEquals(6, tf.getResults().size());
    }

    @Test
    public void sortArrayTrimsToFive_isCorrect() {
        Leaderboard tf = new Leaderboard();
        tf.addToList("ted", 15);
        tf.addToList("dave", 19);
        tf.addToList("ned", 17);
        tf.addToList("tef", 12);
        tf.addToList("david", 20);
        tf.addToList("ash", 16);
        tf.sortList();
        assertEquals(5, tf.getResults().size());
    }
}
