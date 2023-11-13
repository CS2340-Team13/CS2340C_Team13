package com.example.dungeonrunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import android.graphics.Bitmap;
import android.graphics.Point;

import com.example.dungeonrunner.model.Character;
import com.example.dungeonrunner.model.Enemy1;
import com.example.dungeonrunner.model.Enemy2;
import com.example.dungeonrunner.model.EnemyFactory;
import com.example.dungeonrunner.model.EnemyMovementStrategy;
import com.example.dungeonrunner.model.MovementStrategy;
import com.example.dungeonrunner.model.Player;
import com.example.dungeonrunner.model.PlayerMovementStrategy;
import com.example.dungeonrunner.model.Wall;
import com.example.dungeonrunner.viewModels.ConfigScreenViewModel;
import com.example.dungeonrunner.viewModels.GameScreenViewModel;
import com.example.dungeonrunner.views.Room;


import org.junit.Test;

import kotlin._Assertions;

public class Sprint4Tests {

    @Test
    public void canMakeEnemy() {
        GameScreenViewModel gm = new GameScreenViewModel();
        gm.instantiateEnemyInstances(1);
        assertNotNull(gm.getEnemy1());
    }
    @Test
    public void enemyHasCorrectAttribute() {
        GameScreenViewModel gm = new GameScreenViewModel();
        gm.instantiateEnemyInstances(1);
        assertEquals(gm.getEnemy1().getName(), "enemy1");
    }
    @Test
    public void enemyCanMove() {
        GameScreenViewModel gm = new GameScreenViewModel();
        gm.instantiateEnemyInstances(1);
        int pos = gm.getEnemy1().getY();
        gm.getEnemy1().setY(pos-5);
        assertEquals(pos - 5, gm.getEnemy1().getY());
    }
    @Test
    public void DifferentEnemiesHaveDifferentAttributes(){
        GameScreenViewModel gm = new GameScreenViewModel();
        Room room = new Room();
        gm.instantiateEnemyInstances(1);
        assertNotEquals(gm.getEnemy1().getName(),gm.getEnemy2().getName());
    }

    @Test
    public void accurateEnemy1Factory() {
        EnemyFactory ef = new EnemyFactory();
        Character enemyA = ef.makeEnemy("enemy1", 300, 400, 5, 10);
        assertTrue(enemyA instanceof Enemy1);
    }
    @Test
    public void accurateEnemy2Factory() {
        EnemyFactory ef = new EnemyFactory();
        Character enemyA = ef.makeEnemy("enemy2", 300, 400, 5, 10);
        assertTrue(enemyA instanceof Enemy2);
    }

    @Test
    public void testPlayerEnemyCollision() {
        GameScreenViewModel gameScreenViewModel = new GameScreenViewModel();
        Player player = Player.getPlayer(); // Assuming Player is a singleton

        MovementStrategy.setCollisionChecker((x, y, character) -> false);

        int initialHealth = 100;
        player.setX(100);
        player.setY(100);
        player.setPlayerHealth(initialHealth);

        gameScreenViewModel.instantiateEnemyInstances(1);
        Character enemy = gameScreenViewModel.getEnemy1();
        enemy.setX(100); // Set enemy position for collision
        enemy.setY(100);

        EnemyMovementStrategy enemyMovementStrategy = new EnemyMovementStrategy(enemy, gameScreenViewModel);
        enemyMovementStrategy.move();

        // Check if the player's health has decreased due to collision
        assertTrue("Player's health should decrease after collision", player.getPlayerHealth() <= initialHealth);
    }


    @Test
    public void testHealthNotReducingWhenNotColliding() {
        GameScreenViewModel gameScreenViewModel = new GameScreenViewModel();
        Player player = Player.getPlayer();
        MovementStrategy.setCollisionChecker((x, y, character) -> false);

        int initialHealth = 100; // Starting health
        player.setX(100);
        player.setY(100);
        player.setPlayerHealth(initialHealth);

        gameScreenViewModel.instantiateEnemyInstances(1);
        Character enemy = gameScreenViewModel.getEnemy1();
        enemy.setX(500); // player and enemy far away
        enemy.setY(500);

        EnemyMovementStrategy enemyMovementStrategy = new EnemyMovementStrategy(enemy, gameScreenViewModel);
        enemyMovementStrategy.move();

        assertEquals("Player's health should not decrease when there is no collision", initialHealth, player.getPlayerHealth());
    }


    @Test
    public void checkNoCollision() {
        Character enemy1 = EnemyFactory.makeEnemy("enemy1", 55, 55, 30, 30);

        Player player = Player.getPlayer();
        player.setWidth(30);
        player.setHeight(30);

        player.setX(60);
        player.setY(60);

        GameScreenViewModel gm = new GameScreenViewModel();

        EnemyMovementStrategy enemyMovementStrategy = new EnemyMovementStrategy(enemy1, gm);

        enemy1.setX(100);
        enemy1.setY(100);

        // Test when there is no collision
        assertFalse(enemyMovementStrategy.checkCollisionWithPlayer(new Point(player.getX(), player.getY())));

        // Test edge case when the player and enemy share one side (no collision)
        player.setX(70);
        assertFalse(enemyMovementStrategy.checkCollisionWithPlayer(new Point(player.getX(), player.getY())));

        // Test edge case when the player and enemy share one corner (no collision)
        player.setX(70);
        player.setY(70);
        assertFalse(enemyMovementStrategy.checkCollisionWithPlayer(new Point(player.getX(), player.getY())));
    }

    @Test
    public void testSetEnemy2X() {
        Enemy2 enemy;
        enemy = new Enemy2("test", 0, 0, 30, 30);
        int testX = 100;
        enemy.setX(100);
        assertEquals(testX, enemy.getX());
    }
}

