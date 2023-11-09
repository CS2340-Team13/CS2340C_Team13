package com.example.dungeonrunner.model;

public abstract class MovementStrategy {

    protected Character character;
    protected static int screenWidth = 0;
    protected static int screenHeight = 0;
    protected int playerWidth;
    protected int playerHeight;

    public static CollisionChecker wallCollisionChecker;

    public MovementStrategy (Character character) {
        this.character = character;
    }

    public static void setScreenDims(int width, int height) {
        screenWidth = width;
        screenHeight = height;
    }

    public void setPlayerDims(int width, int height) {
        playerWidth = width;
        playerHeight = height;
    }

    public abstract void move();

    public boolean willCollide(int proposedX, int proposedY) {
        if (wallCollisionChecker == null) System.out.println("error");
        boolean wallCollision = wallCollisionChecker.isCollision(
                proposedX, proposedY, this.character);
        boolean upperBoundaryCollision = proposedY > screenHeight - playerHeight;
        boolean lowerBoundaryCollision = proposedY < 0;
        boolean leftBoundaryCollision = proposedX < 0;
        boolean rightBoundaryCollision = proposedX > screenWidth - playerWidth;

        return wallCollision || upperBoundaryCollision || lowerBoundaryCollision
                || leftBoundaryCollision || rightBoundaryCollision;
    }

    public static void setCollisionChecker(CollisionChecker checker) { // Setter method
        wallCollisionChecker = checker;
    }

    public interface CollisionChecker {
        boolean isCollision(int x, int y, Character character);
    }
}
