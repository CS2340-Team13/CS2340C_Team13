package com.example.dungeonrunner.model;


import com.example.dungeonrunner.R;

public class Player extends Character {
    private String selectedCharacter;
    private static volatile Player player;
    private int playerHealth;
    private int score;

    private Player() {

    }

    public static Player getPlayer() {
        if (player == null) {
            synchronized (Player.class) {
                if (player == null) {
                    player = new Player();
                }
            }
        }
        return player;
    }

    public String getSelectedCharacter() {
        return selectedCharacter;
    }

    public void setSelectedCharacter(String selectedCharacter) {
        this.selectedCharacter = selectedCharacter;
        if (selectedCharacter.equals("character1")) {
            this.characterImageResource = R.drawable.character1_image;
        } else if (selectedCharacter.equals("character2")) {
            this.characterImageResource = R.drawable.character2_image;
        } else if (selectedCharacter.equals("character3")) {
            this.characterImageResource = R.drawable.character3_image;
        }
    }
    public void setPlayerHealth(int health) {
        this.playerHealth = health;
    }
    public int getPlayerHealth() {
        return this.playerHealth;
    }
    public int getScore() { return this.score; }
    public void setScore(int score) { this.score = score; }
}
