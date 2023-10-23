package com.example.dungeonrunner.model;

public class PlayerMovementStrategy extends MovementStrategy {

    private static final int MOVE_DISTANCE = 50;

    private static int playerWidth;
    private static int playerHeight;

    private CollisionChecker collisionChecker; // Declare the variable

    public static void setPlayerDims(int width, int height) {
        playerWidth = width;
        playerHeight = height;
    }

    public void setCollisionChecker(CollisionChecker checker) { // Setter method
        this.collisionChecker = checker;
    }

    @Override
    public void move(Player player, MovementDirection direction) {
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

        boolean willCollide = false;
        if (collisionChecker != null) {
            willCollide = collisionChecker.isCollision(
                    proposedX, proposedY, playerWidth, playerHeight);
        }

        if (!willCollide) {
            if (proposedY >= 0 && proposedY <= screenHeight - playerHeight) {
                player.setY(proposedY);
            }
            if (proposedX >= 0 && proposedX <= screenWidth - playerWidth) {
                player.setX(proposedX);
            }
        }
    }

    // Inner interface should be at the end of the class
    public interface CollisionChecker {
        boolean isCollision(int x, int y, int width, int height);
    }

}
