package com.example.dungeonrunner.model;

public interface MovementStrategy {
    
    enum MovementDirection {
        UP, DOWN, LEFT, RIGHT
    }
    void move(Player player, MovementDirection direction;
}
