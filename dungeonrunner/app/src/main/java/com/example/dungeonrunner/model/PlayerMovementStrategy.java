package com.example.dungeonrunner.model;

public class PlayerMovementStrategy extends MovementStrategy {

    private static final int MOVE_DISTANCE = 50;
    private static final int PLAYER_SIZE = 50;

    private static int playerWidth;
    private static int playerHeight;


    public interface CollisionChecker {
        boolean isCollision(int x, int y, int width, int height);
    }

    private CollisionChecker collisionChecker;

    public void setCollisionChecker(CollisionChecker checker) {
        this.collisionChecker = checker;
    }


    public static void setPlayerDims(int width, int height) {
        playerWidth = width;
        playerHeight = height;
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
        }

        boolean willCollide = false;
        if (collisionChecker != null) {
            willCollide = collisionChecker.isCollision(proposedX, proposedY, playerWidth, playerHeight);
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

    public int getMOVE_DISTANCE() {return MOVE_DISTANCE;}

    public int getPLAYER_SIZE() {return PLAYER_SIZE;}
}
