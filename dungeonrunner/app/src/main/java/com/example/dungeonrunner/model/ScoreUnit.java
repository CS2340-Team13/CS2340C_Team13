package com.example.dungeonrunner.model;

public class ScoreUnit {
    private String Name;
    private int Score;

    public ScoreUnit(String name, int score) {
        this.Name = name;
        this.Score = score;
    }

    public String getName() {return this.Name;}

    public int getScore() {return this.Score;}

    public boolean isBigger(ScoreUnit s1, ScoreUnit s2) {
        return s1.Score >= s2.Score;
    }
}
