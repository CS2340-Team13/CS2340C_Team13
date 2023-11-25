package com.example.dungeonrunner.model;

public class Character {
    protected int characterImageResource;
    protected String name;
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    private int playerHealth;
    private int score;
    protected int moveSpeed = 25;
    protected boolean active = true;
    protected boolean isAttacking = false;

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
    public int getMoveSpeed() {
        return moveSpeed;
    }
    public void setmoveSpeed(int move) {
        this.moveSpeed = move;
    }
    public void setPlayerHealth(int health) {
        this.playerHealth = health;
    }
    public int getPlayerHealth() {
        return this.playerHealth;
    }
    public int getScore() { return this.score; }
    public void setScore(int score) { this.score = score; }

    public int getCharacterImageResource() { return characterImageResource; }
    public void setCharacterImageResource(int imageResource) { this.characterImageResource = imageResource; }

    public boolean isActive() { return this.active; }
    public void setActive(boolean active) { this.active = active; }
    public boolean isAttacking() { return this.isAttacking; }
    public void setAttacking(boolean attacking) { this.isAttacking = attacking; }

}
