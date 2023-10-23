package com.example.dungeonrunner.model;

public abstract class MovementStrategy {

    protected static int screenWidth = 0;
    protected static int screenHeight = 0;

    public enum MovementDirection {
        UP, DOWN, LEFT, RIGHT
    }

    public static void setScreenDims(int width, int height) {
        screenWidth = width;
        screenHeight = height;
    }

    public abstract void move(Player player, MovementDirection direction);

}
