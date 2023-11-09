package com.example.dungeonrunner.viewModels;

import android.graphics.Point;
import android.widget.ImageView;
import android.os.CountDownTimer;
import androidx.lifecycle.MutableLiveData;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.lifecycle.ViewModel;

import com.example.dungeonrunner.model.Character;
import com.example.dungeonrunner.model.EnemyFactory;
import com.example.dungeonrunner.model.MovementStrategy;
import com.example.dungeonrunner.model.Player;
import com.example.dungeonrunner.model.PlayerMovementStrategy;
import com.example.dungeonrunner.model.Wall;

import java.util.ArrayList;

public class GameScreenViewModel extends ViewModel implements Observable {

    private Player player = Player.getPlayer();
    private Character enemy1;
    private Character enemy2;
    private int score = 100;
    private boolean timerRunning = false;
    private CountDownTimer timer;
    private EnemyFactory EF;

    private static MutableLiveData<Integer> scoreLiveData = new MutableLiveData<>();

    public MutableLiveData<Point> playerPositionLiveData = new MutableLiveData<>(
            new Point(player.getX(), player.getY()));
    private MutableLiveData<ArrayList<Wall>> wallsLiveData = new MutableLiveData<>();

    public MutableLiveData<ArrayList<Wall>> getWallsLiveData() {
        return wallsLiveData;
    }


    public PlayerMovementStrategy playerMovementStrategy = new PlayerMovementStrategy(player);


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
            enemy1 = EF.makeEnemy("enemy1", 50,50,45,45);
            enemy2 = EF.makeEnemy("enemy2", 50,50,45,45);
        }
        if (roomID == 2) {
            enemy1 = EF.makeEnemy("enemy2", 50,50,45,45);
            enemy2 = EF.makeEnemy("enemy3", 50,50,45,45);
        }
        if (roomID == 3) {
            enemy1 = EF.makeEnemy("enemy3", 50,50,45,45);
            enemy2 = EF.makeEnemy("enemy4", 50,50,45,45);
        }
    }


    public void plot(ImageView imageView, Character entity) {

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
        score = 100;
        timerRunning = true;
        timer = new CountDownTimer(100000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (timerRunning) {
                    score--;
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

    public MutableLiveData<Point> getPlayerPositionLiveData() {
        return playerPositionLiveData;
    }

    public void playerReachedPortal(ImageView playerCharacterImageView) {
        resetPlayerPosition();
        plot(playerCharacterImageView, player);
        notifyObserver();
    }

    //Every second, plot (enemy1ImageView, enemy1), plot (enemy2ImageView, enemy2)

    public  ArrayList<Wall> getWalls() {
        return this.walls;
    }
}