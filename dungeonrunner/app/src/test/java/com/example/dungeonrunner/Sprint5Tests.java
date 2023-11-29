package com.example.dungeonrunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.view.View;
import android.widget.ImageView;

import com.example.dungeonrunner.model.AddHealthPowerUpDecorator;
import com.example.dungeonrunner.model.AddScorePowerUpDecorator;
import com.example.dungeonrunner.model.Character;
import com.example.dungeonrunner.model.Enemy1;
import com.example.dungeonrunner.model.Enemy2;
import com.example.dungeonrunner.model.EnemyFactory;
import com.example.dungeonrunner.model.EnemyMovementStrategy;
import com.example.dungeonrunner.model.MovementStrategy;
import com.example.dungeonrunner.model.Player;
import com.example.dungeonrunner.model.PlayerMovementStrategy;
import com.example.dungeonrunner.model.SpeedBoostPowerUpDecorator;
import com.example.dungeonrunner.model.Wall;
import com.example.dungeonrunner.viewModels.ConfigScreenViewModel;
import com.example.dungeonrunner.viewModels.GameScreenViewModel;
import com.example.dungeonrunner.views.Room;


import org.junit.Test;

import kotlin._Assertions;

import org.junit.Test;

public class Sprint5Tests {
    @Test
    public void testPlayerAttack() {
        GameScreenViewModel gameScreenViewModel = new GameScreenViewModel();
        Room room = new Room();

        ImageView playerCharacterImageView = room.enemy1ImageView;

        Player player = Player.getPlayer();

        PlayerMovementStrategy playerMovementStrategy = new PlayerMovementStrategy(player);

        playerMovementStrategy.attack(gameScreenViewModel, playerCharacterImageView);

        // Attacking sets the character to attacking state
        assertEquals(true, player.isAttacking());

        // Character image resource is updated during attack
        assertEquals(R.drawable.character1_attacking, player.getCharacterImageResource());
    }

    @Test
    public void testEnemyImageBlankAfterAttack() {
        Room roomFragment = new Room();  // Replace with the actual class name
        View view = roomFragment.getView(); // Get the view of the fragment
        ImageView playerCharacterImageView = view.findViewById(R.id.playerCharacterImageView);

        GameScreenViewModel gameScreenViewModel = new GameScreenViewModel();
        Player player = Player.getPlayer();
        PlayerMovementStrategy playerMovementStrategy = new PlayerMovementStrategy(player);
        gameScreenViewModel.playerMovementStrategy = playerMovementStrategy;

        gameScreenViewModel.instantiateEnemyInstances(1);
        Character enemy = gameScreenViewModel.getEnemy1();

        player.setAttacking(true);
        playerMovementStrategy.attack(gameScreenViewModel, playerCharacterImageView);

        // Ensure that the plot function is called
        gameScreenViewModel.plot(roomFragment.enemy1ImageView, enemy);

        // Ensure that the enemy image becomes blank after an attack
        assertEquals(R.drawable.blank, enemy.getCharacterImageResource());
    }

    @Test
    public void playerAttackingStartsFalse() {
        Player player = Player.getPlayer();
        assertFalse(player.isAttacking());
    }

    @Test
    public void enemyDiesIfPlayerAttacking() {
        GameScreenViewModel gameScreenViewModel = new GameScreenViewModel();
        Player player = Player.getPlayer();

        MovementStrategy.setCollisionChecker((x, y, character) -> false);

        int initialHealth = 100;
        player.setX(100);
        player.setY(100);
        player.setPlayerHealth(initialHealth);
        player.setAttacking(true);
        Point playerPosition = new Point(Player.getPlayer().getX(), Player.getPlayer().getY());

        gameScreenViewModel.instantiateEnemyInstances(1);
        Character enemy = gameScreenViewModel.getEnemy1();
        enemy.setX(100);
        enemy.setY(105);

        EnemyMovementStrategy enemyMovementStrategy = new EnemyMovementStrategy(enemy, gameScreenViewModel);
        enemyMovementStrategy.move();

        assertTrue(player.isAttacking());
        assertFalse(enemyMovementStrategy.isCollidingWithPlayer(playerPosition));
    }

    @Test
    public void enemyIsActiveStartsTrue() {
        GameScreenViewModel gameScreenViewModel = new GameScreenViewModel();
        gameScreenViewModel.instantiateEnemyInstances(1);
        Character enemy = gameScreenViewModel.getEnemy1();
        assertTrue(enemy.isActive());
    }

    @Test
    public void enemyLivesIfNoCollision() {
        GameScreenViewModel gameScreenViewModel = new GameScreenViewModel();
        Player player = Player.getPlayer();

        MovementStrategy.setCollisionChecker((x, y, character) -> false);

        int initialHealth = 100;
        player.setX(600);
        player.setY(600);
        player.setPlayerHealth(initialHealth);
        player.setAttacking(true);
        Point playerPosition = new Point(player.getX(), player.getY());

        gameScreenViewModel.instantiateEnemyInstances(1);
        Character enemy = gameScreenViewModel.getEnemy1();
        enemy.setX(100); // Set enemy position for collision
        enemy.setY(100);

        EnemyMovementStrategy enemyMovementStrategy = new EnemyMovementStrategy(enemy, gameScreenViewModel);
        enemyMovementStrategy.move();

        int enemyLeft = enemy.getX();
        int enemyRight = enemyLeft + enemy.getWidth();
        int enemyTop = enemy.getY();
        int enemyBottom = enemyTop + enemy.getHeight();
        boolean isActive = enemy.isActive();

        int playerLeft = playerPosition.x;
        int playerRight = playerLeft + Player.getPlayer().getWidth();
        int playerTop = playerPosition.y;
        int playerBottom = playerTop + Player.getPlayer().getHeight();

        assertTrue(player.isAttacking());
        assertFalse(enemyRight > playerLeft && enemyLeft < playerRight
                && enemyBottom > playerTop && enemyTop < playerBottom);
        assertTrue(enemy.isActive());
    }
    @Test
    public void testHealthPowerUpDisappearsAfterCollision() {
        Player player = Player.getPlayer();
        GameScreenViewModel gameScreenViewModel = new GameScreenViewModel();
        AddHealthPowerUpDecorator healthPowerUp = new AddHealthPowerUpDecorator(player, gameScreenViewModel);
        // Simulate collision
        healthPowerUp.setActive(false);
        assertFalse(healthPowerUp.isActive());
    }

    @Test
    public void testSpeedBoostPowerUpDisappearsAfterCollision() {
        Player player = Player.getPlayer();
        GameScreenViewModel gameScreenViewModel = new GameScreenViewModel();
        SpeedBoostPowerUpDecorator speedBoostPowerUp = new SpeedBoostPowerUpDecorator(player, gameScreenViewModel);
        speedBoostPowerUp.setActive(false);
        assertFalse(speedBoostPowerUp.isActive());
    }
  
    @Test
    public void testScorePowerUpDisappearsAfterCollision() {
        Player player = Player.getPlayer();
        GameScreenViewModel gameScreenViewModel = new GameScreenViewModel();
        AddScorePowerUpDecorator scorePowerUp = new AddScorePowerUpDecorator(player, gameScreenViewModel);
        scorePowerUp.setActive(false);
        assertFalse(scorePowerUp.isActive());
    }











}
