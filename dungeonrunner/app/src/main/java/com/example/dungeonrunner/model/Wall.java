package com.example.dungeonrunner.model;

public class Wall {
    private int x;
    private int y;
    private int width;
    private int height;

    public Wall(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean intersects(int x, int y, int playerWidth, int playerHeight) {
        return x < this.x + this.width
                && x + playerWidth > this.x
                && y < this.y + this.height
                && y + playerHeight > this.y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
