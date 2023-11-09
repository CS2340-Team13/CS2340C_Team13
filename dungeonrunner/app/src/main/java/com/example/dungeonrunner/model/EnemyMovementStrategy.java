package com.example.dungeonrunner.model;

import android.graphics.Point;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.dungeonrunner.viewModels.GameScreenViewModel;

public class EnemyMovementStrategy extends MovementStrategy { // implements Observable
    private static final int MOVE_DISTANCE = 25;

    public EnemyMovementStrategy(Character character) {
        super(character);
    }



    @Override
    public void move() {
        int proposedX = character.getX();
        int proposedY = character.getY();

        if (character.getName().equalsIgnoreCase("enemy1")) {
            proposedY -= MOVE_DISTANCE;
        }
        if (character.getName().equalsIgnoreCase("enemy2")) {
            proposedX -= MOVE_DISTANCE;
        }
        if (character.getName().equalsIgnoreCase("enemy3")) {
            proposedY -= MOVE_DISTANCE * 2;
        }
        if (character.getName().equalsIgnoreCase("enemy4")) {
            proposedX -= MOVE_DISTANCE * 2;
        }

        if (!willCollide(proposedX, proposedY)) {
            character.setX(proposedX);
            character.setY(proposedY);
        } else {
            character.setX(50);
            character.setY(50);
        }


        // Notify observer
        // notify observer updates each enemy
        // each enemy update checks its position against the player's position and decreases player health accordingly
    }
}