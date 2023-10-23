package com.example.dungeonrunner.model;

public class PlayerMovementStrategy extends MovementStrategy {

    private static final int MOVE_DISTANCE = 50;

    private static int playerWidth;
    private static int playerHeight;

    public interface CollisionChecker {
        boolean isCollision(int x, int y, int width, int height);
    }

    private CollisionChecker collisionChecker; // Declare the variable

    public void setCollisionChecker(CollisionChecker checker) { // Setter method
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
<<<<<<< HEAD
                proposedY += MOVE_DISTANCE;
=======
                if (player.getY() + MOVE_DISTANCE + PLAYER_SIZE <= screenHeight - 150) {
                    player.setY(player.getY() + MOVE_DISTANCE);
                }
>>>>>>> 4612b7adea3231dbd97561c5cdae462496addc60
                break;
            case LEFT:
                proposedX -= MOVE_DISTANCE;
                break;
            case RIGHT:
<<<<<<< HEAD
                proposedX += MOVE_DISTANCE;
=======
                if (player.getX() + MOVE_DISTANCE + PLAYER_SIZE <= screenWidth - 110) {
                    player.setX(player.getX() + MOVE_DISTANCE);
                }
>>>>>>> 4612b7adea3231dbd97561c5cdae462496addc60
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

}
