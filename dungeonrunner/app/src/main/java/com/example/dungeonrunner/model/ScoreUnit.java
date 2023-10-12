package com.example.dungeonrunner.model;


import android.os.Build;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScoreUnit {
    private String name;
    private int score;
    private String date;

    public ScoreUnit(String name, int score) {
        
        this.name = name;
        this.score = score;
        LocalDateTime now = null;
        DateTimeFormatter formatter = null;
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            now = LocalDateTime.now();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.date = now.format(formatter);
        }
    }

    public String toString() {
        return "Player: " + this.name + " | Score: " + this.score + " | Time: " + this.date;
    }

    public int getScore() {
        return this.score;
    }

    public boolean isBigger(ScoreUnit s1, ScoreUnit s2) {
        return s1.score >= s2.score;
    }
}
