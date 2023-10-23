package com.example.dungeonrunner.model;

public class PlayerMovementStrategy implements MovementStrategy {

    private final int MOVE_DISTANCE = 50;
    private final int PLAYER_SIZE = 50;  // Assuming this is the size of your player sprite

    @Override
    public void move(Player player, MovementDirection direction, int screenWidth, int screenHeight) {
        switch (direction) {
            case UP:
                if (player.getY() - MOVE_DISTANCE >= 0) {
                    player.setY(player.getY() - MOVE_DISTANCE);
                }
                break;
            case DOWN:
                if (player.getY() + MOVE_DISTANCE + PLAYER_SIZE <= screenHeight - 150) {
                    player.setY(player.getY() + MOVE_DISTANCE);
                }
                break;
            case LEFT:
                if (player.getX() - MOVE_DISTANCE >= 0) {
                    player.setX(player.getX() - MOVE_DISTANCE);
                }
                break;
            case RIGHT:
                if (player.getX() + MOVE_DISTANCE + PLAYER_SIZE <= screenWidth - 110) {
                    player.setX(player.getX() + MOVE_DISTANCE);
                }
                break;
        }
    }
}
