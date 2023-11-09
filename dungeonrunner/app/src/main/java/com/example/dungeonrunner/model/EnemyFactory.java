package com.example.dungeonrunner.model;

public class EnemyFactory {
    public static Character makeEnemy(String type, int x, int y, int width, int height) {
        if ("enemy1".equalsIgnoreCase(type)) {
            return new Enemy1(type, x, y, width, height);
        }
        if ("enemy2".equalsIgnoreCase(type)) {
            return new Enemy2(type, x, y, width, height);
        }
        if ("enemy3".equalsIgnoreCase(type)) {
            return new Enemy3(type, x, y, width, height);
        }
        if ("enemy4".equalsIgnoreCase(type)) {
            return new Enemy4(type, x, y, width, height);
        }
        return null;
    }
}
