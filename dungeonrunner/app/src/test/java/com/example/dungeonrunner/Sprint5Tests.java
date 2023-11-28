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

import org.junit.Test;

public class Sprint5Tests {
    @Test
    public void testPlayerAttack() {
        GameScreenViewModel gameScreenViewModel = new GameScreenViewModel();
        View view;

        ImageView playerCharacterImageView = view.findViewById(R.id.playerCharacterImageView);

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

//        gameScreenViewModel.registerObserver(playerMovementStrategy);


        player.setAttacking(true);
        playerMovementStrategy.attack(gameScreenViewModel, playerCharacterImageView);

        // Ensure that the plot function is called
        gameScreenViewModel.plot(roomFragment.enemy1ImageView, enemy);

        // Ensure that the enemy image becomes blank after an attack
        assertEquals(R.drawable.blank, enemy.getCharacterImageResource());
    }


}
