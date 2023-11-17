package com.example.dungeonrunner.viewModels;

import android.graphics.Point;
import android.util.Log;
import android.widget.ImageView;
import android.os.CountDownTimer;
import androidx.lifecycle.MutableLiveData;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.lifecycle.ViewModel;

import com.example.dungeonrunner.R;
import com.example.dungeonrunner.model.Character;
import com.example.dungeonrunner.model.EnemyFactory;
import com.example.dungeonrunner.model.MovementStrategy;
import com.example.dungeonrunner.model.Player;
import com.example.dungeonrunner.model.PlayerMovementStrategy;
import com.example.dungeonrunner.model.EnemyMovementStrategy;
import com.example.dungeonrunner.model.Wall;

import java.util.ArrayList;

public class GameScreenViewModel extends ViewModel implements Observable {

    private static Player player = Player.getPlayer();
    private Character enemy1;
    private Character enemy2;
    private int score = 100;
    private boolean timerRunning = false;
    private CountDownTimer timer;
    private EnemyFactory EF;

    private static MutableLiveData<Integer> scoreLiveData = new MutableLiveData<>();

    private static MutableLiveData<Integer> healthLiveData = new MutableLiveData<>(player.getPlayerHealth());

    public MutableLiveData<Point> playerPositionLiveData = new MutableLiveData<>(
            new Point(player.getX(), player.getY()));
    private MutableLiveData<ArrayList<Wall>> wallsLiveData = new MutableLiveData<>();

    public MutableLiveData<ArrayList<Wall>> getWallsLiveData() {
        return wallsLiveData;
    }


    public PlayerMovementStrategy playerMovementStrategy = new PlayerMovementStrategy(player);
    public EnemyMovementStrategy enemyMovementStrategy1;
    public EnemyMovementStrategy enemyMovementStrategy2;


    private ArrayList<Wall> walls = new ArrayList<Wall>();

    private Observer roomObserver;

    public void registerObserver(Observer observer) {
        this.roomObserver = observer;
    }

    public void notifyObserver() {
        if (roomObserver != null) {
            roomObserver.update();
        }
    }

    public void addWall(Wall wall) {
        walls.add(wall);
        wallsLiveData.postValue(new ArrayList<>(walls));
    }


    public void generateWallInTheMiddle(int screenW, int screenH) {
        int wallWidth = 32;
        int wallHeight = 400;

        int startX = (screenW - wallWidth) / 2;
        int startY = (screenH - wallHeight) / 2;

        Wall wall = new Wall(startX, startY, wallWidth, wallHeight);
        addWall(wall);
    }

    private boolean isCollidingWithWall(int newX, int newY, Character entity) {
        for (Wall wall : walls) {
            if (wall.intersects(newX, newY, entity.getWidth(), entity.getHeight())) {
                return true;
            }
        }
        return false;
    }


    public void configureMovementStrategy(int screenWidth, int screenHeight) {
        MovementStrategy.setScreenDims(screenWidth, screenHeight);
        MovementStrategy.setCollisionChecker((x, y, entity) -> isCollidingWithWall(x, y, entity));
        generateWallInTheMiddle(screenWidth, screenHeight);
    }

    public void configurePlayerMovement(int playerWidth, int playerHeight) {
        playerMovementStrategy.setPlayerDims(playerWidth, playerHeight);
        player.setWidth(playerWidth);
        player.setHeight(playerHeight);
    }


    public void resetPlayerPosition() {
        player.setX(0);
        player.setY(0);
    }
    public void instantiateEnemyInstances(int roomID){
        if (roomID == 1) {
            enemy1 = EF.makeEnemy("enemy1", 1650,750,45,45);
            enemy2 = EF.makeEnemy("enemy2", 1650,750,45,45);
        }
        if (roomID == 2) {
            enemy1 = EF.makeEnemy("enemy2", 1650,750,45,45);
            enemy2 = EF.makeEnemy("enemy3", 1650,750,45,45);
        }
        if (roomID == 3) {
            enemy1 = EF.makeEnemy("enemy3", 1650,750,45,45);
            enemy2 = EF.makeEnemy("enemy4", 1650,750,45,45);
        }
        enemyMovementStrategy1 = new EnemyMovementStrategy(enemy1, this);
        enemyMovementStrategy2 = new EnemyMovementStrategy(enemy2, this);

        playerMovementStrategy.registerObserver(enemyMovementStrategy1);
        playerMovementStrategy.registerObserver(enemyMovementStrategy2);
    }


    public void plot(ImageView imageView, Character entity) {
        if (!entity.isActive()) {
            imageView.setImageResource(R.drawable.blank);
            return;
        }
        imageView.setImageResource(entity.getCharacterImageResource());
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone((ConstraintLayout) imageView.getParent());
        constraintSet.connect(imageView.getId(), ConstraintSet.START,
                ConstraintLayout.LayoutParams.PARENT_ID, ConstraintSet.START, entity.getX());
        constraintSet.connect(imageView.getId(), ConstraintSet.TOP,
                ConstraintLayout.LayoutParams.PARENT_ID, ConstraintSet.TOP, entity.getY());
        constraintSet.applyTo((ConstraintLayout) imageView.getParent());
    }

    public void startTimer() {
        player.setScore(100);
        timerRunning = true;
        timer = new CountDownTimer(100000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (timerRunning) {
                    int score = player.getScore() - 1;
                    player.setScore(score);
                    scoreLiveData.setValue(score);
                } else {
                    timer.cancel();
                }
            }
            @Override
            public void onFinish() {
                scoreLiveData.setValue(0);
            }
        }.start();
    }

    public boolean isTimerRunning() {
        return timerRunning;
    }


    public void stopTimer() {
        timerRunning = false;
    }

    public MutableLiveData<Integer> getScoreLiveData() {
        return scoreLiveData;
    }

    public MutableLiveData<Integer> getHealthLiveData() { return healthLiveData; }

    public MutableLiveData<Point> getPlayerPositionLiveData() {
        return playerPositionLiveData;
    }

    public void playerReachedPortal(ImageView playerCharacterImageView) {
        resetPlayerPosition();
        plot(playerCharacterImageView, player);
        notifyObserver();
    }

    //Every second, plot (enemy1ImageView, enemy1), plot (enemy2ImageView, enemy2)
    public void updateEnemy(ImageView E1IV, ImageView E2IV, EnemyMovementStrategy E1, EnemyMovementStrategy E2) {
        E1.move();
        E2.move();
        plot(E1IV,enemy1);
        plot(E2IV, enemy2);
    }

    public void reducePlayerHealth() {
        Integer currentHealth = healthLiveData.getValue();
        int damage = 0;

        if (player.getPlayerHealth() == 100) {
            // easy
            damage = 5;
        } else if (player.getPlayerHealth() == 75) {
            damage = 10;
        } else if (player.getPlayerHealth() == 50) {
            damage = 15;
        }

        if (currentHealth != null) {
            int newHealth = Math.max(currentHealth - damage, 0); // Prevent health from going below zero
            healthLiveData.setValue(newHealth);
        }
    }

    public  ArrayList<Wall> getWalls() {
        return this.walls;
    }
    public Character getEnemy1() {return this.enemy1;}
    public Character getEnemy2() {return this.enemy2;}
    public int getHealthData() {
        return healthLiveData.getValue();
    }
}