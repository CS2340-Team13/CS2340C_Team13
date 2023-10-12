package com.example.dungeonrunner.model;

import java.util.ArrayList;

public class Leaderboard {
    private ArrayList<ScoreUnit> results;
    private static volatile Leaderboard leaderboard;

    //creates new Leaderboard
    private Leaderboard() {
        this.results = new ArrayList<ScoreUnit>();
    }

    public static Leaderboard getLeaderboard() {
        if (leaderboard == null) {
            synchronized (Leaderboard.class) {
                if (leaderboard == null) {
                    leaderboard = new Leaderboard();
                }
            }
        }
        return leaderboard;
    }

    public ScoreUnit getScoreUnit(int index) {
        return results.get(index);
    }

    public ArrayList<ScoreUnit> getResults() {
        return results;
    }

    public void addToList(String s, int i) {
        ScoreUnit scoreunit = new ScoreUnit(s, i);
        results.add(scoreunit);
    }

    //Sorts the list with insertion sort then trims it to the top 5 scores.
    public void sortList() {
        for (int i = 1; i < results.size(); i++) {
            ScoreUnit temp = results.get(i);
            int j = i;

            while (j > 0 && results.get(j - 1).getScore() < temp.getScore()) {
                results.set(j, results.get(j - 1));
                j--;
            }
            results.set(j, temp);
        }
        while (results.size() > 5) {
            results.remove(results.size() - 1);
        }
    }

}