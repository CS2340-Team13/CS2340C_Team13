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
        Player player = Player.getPlayer();
        PlayerMovementStrategy movementStrategy = new PlayerMovementStrategy();
        int initialY = player.getY();
        movementStrategy.move(player, MovementStrategy.MovementDirection.UP, 1000, 1000);
        int finalY = player.getY();
        assertEquals(initialY , finalY);
    }

    @Test
    public void testMoveDown() {
        Player player = Player.getPlayer();
        PlayerMovementStrategy movementStrategy = new PlayerMovementStrategy();
        int initialY = player.getY();
        movementStrategy.move(player, MovementStrategy.MovementDirection.DOWN, 1000, 1000);
        int finalY = player.getY();
        assertEquals(initialY + 50, finalY);
    }

    @Test
    public void testMoveLeft() {
        Player player = Player.getPlayer();
        PlayerMovementStrategy movementStrategy = new PlayerMovementStrategy();
        int initialX = player.getX();
        movementStrategy.move(player, MovementStrategy.MovementDirection.LEFT, 1000, 1000);
        int finalX = player.getX();
        assertEquals(initialX , finalX);
    }

    @Test
    public void testMoveRight() {
        Player player = Player.getPlayer();
        PlayerMovementStrategy movementStrategy = new PlayerMovementStrategy();
        int initialX = player.getX();
        movementStrategy.move(player, MovementStrategy.MovementDirection.RIGHT, 1000 ,1000);
        int finalX = player.getX();
        assertEquals(initialX + 50, finalX);
    }
    @Test
    public void testNoMoveUpBorder() {
        Player player = Player.getPlayer();
        PlayerMovementStrategy movementStrategy = new PlayerMovementStrategy();
        int initialY = player.getY();
        movementStrategy.move(player, MovementStrategy.MovementDirection.UP, 0, 0);
        int finalY = player.getY();
        assertNotEquals(initialY, finalY);
    }

    @Test
    public void testNoMoveDownBorder() {
        Player player = Player.getPlayer();
        PlayerMovementStrategy movementStrategy = new PlayerMovementStrategy();
        int initialY = player.getY();
        movementStrategy.move(player, MovementStrategy.MovementDirection.DOWN, 0, 0);
        int finalY = player.getY();
        assertNotEquals(initialY + 50, finalY);
    }

    @Test
    public void testNoMoveLeftBorder() {
        Player player = Player.getPlayer();
        PlayerMovementStrategy movementStrategy = new PlayerMovementStrategy();
        int initialX = player.getX();
        movementStrategy.move(player, MovementStrategy.MovementDirection.LEFT, 0, 0);
        int finalX = player.getX();
        assertNotEquals(initialX - 50, finalX);
    }

    @Test
    public void testNoMoveRightBorder() {
        Player player = Player.getPlayer();
        PlayerMovementStrategy movementStrategy = new PlayerMovementStrategy();
        int initialX = player.getX();
        movementStrategy.move(player, MovementStrategy.MovementDirection.RIGHT, 0 ,0);
        int finalX = player.getX();
        assertNotEquals(initialX + 50, finalX);
    }
    @Test
    public void testCorrectMoveDistance() {
        Player player = Player.getPlayer();
        PlayerMovementStrategy movementStrategy = new PlayerMovementStrategy();
        int distance = movementStrategy.getMOVE_DISTANCE();
        assertEquals(distance, 50);
    }
    @Test
    public void testCorrectPlayerSize() {
        Player player = Player.getPlayer();
        PlayerMovementStrategy movementStrategy = new PlayerMovementStrategy();
        int distance = movementStrategy.getPLAYER_SIZE();
        assertEquals(distance, 50);
    }
}
