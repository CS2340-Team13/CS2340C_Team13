package com.example.dungeonrunner.model;

public class Player {
    private String playerName;
    private String selectedCharacter;
    private String gameDifficulty;
    private volatile static Player player;


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

    public void setSelectedCharater(String selectedCharacter) {
        this.selectedCharacter = selectedCharacter;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getGameDifficulty() {
        return gameDifficulty;
    }

    public void setGameDifficulty(String gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
    }
}
