package com.example.dungeonrunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.example.dungeonrunner.model.MovementStrategy;
import com.example.dungeonrunner.model.Player;
import com.example.dungeonrunner.model.PlayerMovementStrategy;
import com.example.dungeonrunner.model.Wall;
import com.example.dungeonrunner.viewModels.GameScreenViewModel;
import com.example.dungeonrunner.views.Room;


import org.junit.Test;

public class Sprint4Tests {
    @Test
    public void gameScreenLoaded() {

    }
    @Test
    public void canMakeEnemy() {
        GameScreenViewModel gm = new GameScreenViewModel();
        gm.instantiateEnemyInstances(1);
        assertNotNull(gm.getEnemy1());
    }
    @Test
    public void enemyHasCorrectAttribute() {
        GameScreenViewModel gm = new GameScreenViewModel();
        gm.instantiateEnemyInstances(1);
        assertEquals(gm.getEnemy1().getName(), "enemy1");
    }
    @Test
    public void enemyCanMove() {
        GameScreenViewModel gm = new GameScreenViewModel();
        gm.instantiateEnemyInstances(1);
        int pos = gm.getEnemy1().getY();
        gm.getEnemy1().setY(pos-5);
        assertEquals(pos - 5, gm.getEnemy1().getY());
    }
    @Test
    public void DifferentEnemiesHaveDifferentAttributes(){
        GameScreenViewModel gm = new GameScreenViewModel();
        Room room = new Room();
        gm.instantiateEnemyInstances(1);
        assertNotEquals(gm.getEnemy1().getName(),gm.getEnemy2().getName());
    }
}

