package com.example.dungeonrunner.model;

public class PlayerMovementStrategy implements MovementStrategy {

    private final int MOVE_DISTANCE = 50;

    @Override
    public void move(Player player, MovementDirection direction) {
        switch (direction) {
            case UP:
                player.setY(player.getY() - MOVE_DISTANCE);
                break;
            case DOWN:
                player.setY(player.getY() + MOVE_DISTANCE);
                break;
            case LEFT:
                player.setX(player.getX() - MOVE_DISTANCE);
                break;
            case RIGHT:
                player.setX(player.getX() + MOVE_DISTANCE);
                break;
        }
    }
}