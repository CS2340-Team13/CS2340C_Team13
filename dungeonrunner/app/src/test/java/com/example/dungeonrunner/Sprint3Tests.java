package com.example.dungeonrunner;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import com.example.dungeonrunner.model.Leaderboard;
import com.example.dungeonrunner.model.MovementStrategy;
import com.example.dungeonrunner.model.Player;
import com.example.dungeonrunner.model.PlayerMovementStrategy;
import com.example.dungeonrunner.model.ScoreUnit;
import com.example.dungeonrunner.viewModels.ConfigScreenViewModel;
import com.example.dungeonrunner.viewModels.GameScreenViewModel;
import com.example.dungeonrunner.views.EndScreen;

import org.junit.Test;

public class Sprint3Tests {
    @Test
    public void testMoveUp() {
        MovementStrategy.setScreenDims(1000, 1000);
        Player player = Player.getPlayer();
        PlayerMovementStrategy movementStrategy = new PlayerMovementStrategy();
        int initialY = player.getY();
        movementStrategy.move(player, MovementStrategy.MovementDirection.UP);
        int finalY = player.getY();
        assertEquals(initialY , finalY);
    }

    @Test
    public void testNoMoveUpBorder() {
        Player player = Player.getPlayer();
        PlayerMovementStrategy movementStrategy = new PlayerMovementStrategy();
        int initialY = player.getY();
        movementStrategy.move(player, MovementStrategy.MovementDirection.UP);
        int finalY = player.getY();
        assertNotEquals(initialY, finalY);
    }
}
