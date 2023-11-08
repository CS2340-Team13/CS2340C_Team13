package com.example.dungeonrunner.model;

public abstract class MovementStrategy {

    protected Entity entity;
    protected static int screenWidth = 0;
    protected static int screenHeight = 0;
    protected int playerWidth;
    protected int playerHeight;

    public static CollisionChecker wallCollisionChecker;

    public interface CollisionChecker {
        boolean isCollision(int x, int y, int width, int height);
    }
    public MovementStrategy (Entity entity) {
        this.entity = entity;
    }
    public enum MovementDirection {
        UP, DOWN, LEFT, RIGHT
    }

    public static void setCollisionChecker(CollisionChecker checker) { // Setter method
        wallCollisionChecker = checker;
    }

    public static void setScreenDims(int width, int height) {
        screenWidth = width;
        screenHeight = height;
    }

    public void setPlayerDims(int width, int height) {
        playerWidth = width;
        playerHeight = height;
    }

    public abstract void move(Entity player, MovementDirection direction);

    public boolean willCollide(int proposedX, int proposedY) {
        boolean wallCollision = wallCollisionChecker.isCollision(
                proposedX, proposedY, playerWidth, playerHeight);
        boolean upperBoundaryCollision = proposedY > screenHeight - playerHeight;
        boolean lowerBoundaryCollision = proposedY < 0;
        boolean leftBoundaryCollision = proposedX < 0;
        boolean rightBoundaryCollision = proposedX > screenWidth - playerWidth;

        return wallCollision || upperBoundaryCollision || lowerBoundaryCollision
                || leftBoundaryCollision || rightBoundaryCollision;
    }
}
