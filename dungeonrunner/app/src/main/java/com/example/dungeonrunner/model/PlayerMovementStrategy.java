package com.example.dungeonrunner.model;

public class PlayerMovementStrategy extends MovementStrategy { // implements Observable

    private static final int MOVE_DISTANCE = 25;

    public PlayerMovementStrategy(Entity entity) {
        super(entity);
    }

    @Override
    public void move(Entity player, MovementDirection direction) {
        int proposedX = player.getX();
        int proposedY = player.getY();

        switch (direction) {

        case UP:
            proposedY -= MOVE_DISTANCE;
            break;
        case DOWN:
            proposedY += MOVE_DISTANCE;
            break;
        case LEFT:
            proposedX -= MOVE_DISTANCE;
            break;
        case RIGHT:
            proposedX += MOVE_DISTANCE;
            break;
        default:
            break;
        }

        if (!willCollide(proposedX, proposedY)) {
            player.setX(proposedX);
            player.setY(proposedY);
        }


        // Notify observer
        // notify observer updates each enemy
        // each enemy update checks its position against the player's position and decreases player health accordingly
    }

}
