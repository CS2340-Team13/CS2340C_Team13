package com.example.dungeonrunner.model;

public class Character {
    protected int characterImageResource;
    protected String name;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean active = true;

    public String getName() { return name; }

    public void setName(String playerName) {
        this.name = playerName;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }

    public int getCharacterImageResource() { return characterImageResource; }

    public boolean isActive() { return this.active; }
    public void setActive(boolean active) { this.active = active; }

}
