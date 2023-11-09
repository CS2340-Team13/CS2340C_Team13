package com.example.dungeonrunner.model;

import android.graphics.Point;
import android.view.View;
import android.widget.ImageView;

import com.example.dungeonrunner.viewModels.GameScreenViewModel;

public class EnemyMovementStrategy extends MovementStrategy{

    private static final int MOVE_DISTANCE = 25;

    public EnemyMovementStrategy(Character character) {
        super(character);
    }



    @Override
    public void move() {
        if (character == null) {
            throw new NullPointerException("Character is null");
        }
        int proposedX = character.getX();
        int proposedY = character.getY();



           if (character.getName().equalsIgnoreCase("enemy1")) {
               proposedY -= 5;
           };
            if (character.getName().equalsIgnoreCase("enemy2")) {
                proposedX -= 5;
            };
            if (character.getName().equalsIgnoreCase("enemy3")) {
                proposedY -= 10;
            };
            if (character.getName().equalsIgnoreCase("enemy4")) {
                proposedX -= 10;
            };

            if (!willCollide(proposedX, proposedY)) {
            character.setX(proposedX);
            character.setY(proposedY);
        } else {
                character.setX(1650);
                character.setY(750);
            }

        // Notify observer
        // notify observer updates each enemy
        // each enemy update checks its position against the player's position and decreases player health accordingly
    }
    public int getMoveDistance(){
        return this.MOVE_DISTANCE;
    }
}

