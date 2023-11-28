package com.example.dungeonrunner.model;

import android.graphics.Point;

import com.example.dungeonrunner.viewModels.EnemyObserver;
import com.example.dungeonrunner.viewModels.GameScreenViewModel;

public class EnemyMovementStrategy extends MovementStrategy implements EnemyObserver {

    private long lastCollisionTime = 0;
    private static final long COLLISION_COOLDOWN = 1000; // 1 second cooldown
    private static final int MOVE_DISTANCE = 25;
    GameScreenViewModel gameScreenViewModel;

    public EnemyMovementStrategy(Character character, GameScreenViewModel gameScreenViewModel) {
        super(character);
        this.gameScreenViewModel = gameScreenViewModel;
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

        // to ensure when player is stationary, collision is detected.
        Point playerPosition = new Point(Player.getPlayer().getX(), Player.getPlayer().getY());
        update(playerPosition);
    }
    public int getMoveDistance(){
        return this.MOVE_DISTANCE;
    }


    @Override
    public void update(Point playerPosition) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastCollisionTime > COLLISION_COOLDOWN) {
            if (isCollidingWithPlayer(playerPosition)) {
                gameScreenViewModel.reducePlayerHealth();
                lastCollisionTime = currentTime;
            }
        }
    }

    private boolean isCollidingWithPlayer(Point playerPosition) {
        int enemyLeft = character.getX();
        int enemyRight = enemyLeft + character.getWidth();
        int enemyTop = character.getY();
        int enemyBottom = enemyTop + character.getHeight();
        boolean isActive = character.isActive();

        int playerLeft = playerPosition.x;
        int playerRight = playerLeft + Player.getPlayer().getWidth();
        int playerTop = playerPosition.y;
        int playerBottom = playerTop + Player.getPlayer().getHeight();

        // Check if the enemy and player rectangles overlap
        boolean collision = enemyRight > playerLeft && enemyLeft < playerRight
                && enemyBottom > playerTop && enemyTop < playerBottom;

        if (collision) {
            if (Player.getPlayer().isAttacking()) {
                character.setActive(false);
            }
        }

        return isActive && collision;
    }

    public boolean checkCollisionWithPlayer(Point playerPosition) {
        return isCollidingWithPlayer(playerPosition);
    }

}

