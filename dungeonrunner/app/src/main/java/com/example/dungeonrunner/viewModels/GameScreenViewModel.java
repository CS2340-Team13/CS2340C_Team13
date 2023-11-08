package com.example.dungeonrunner.viewModels;

import android.graphics.Point;
import android.widget.ImageView;
import android.os.CountDownTimer;
import androidx.lifecycle.MutableLiveData;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.lifecycle.ViewModel;

import com.example.dungeonrunner.R;
import com.example.dungeonrunner.model.Entity;
import com.example.dungeonrunner.model.MovementStrategy;
import com.example.dungeonrunner.model.Player;
import com.example.dungeonrunner.model.PlayerMovementStrategy;
import com.example.dungeonrunner.model.Wall;

import java.util.ArrayList;

public class GameScreenViewModel extends ViewModel implements Observable {

    private Player player = Player.getPlayer();
    private int score = 100;
    private boolean timerRunning = false;
    private CountDownTimer timer;

    private static MutableLiveData<Integer> scoreLiveData = new MutableLiveData<>();

    private MutableLiveData<Point> playerPositionLiveData = new MutableLiveData<>(
            new Point(player.getX(), player.getY()));
    private MutableLiveData<ArrayList<Wall>> wallsLiveData = new MutableLiveData<>();

    public MutableLiveData<ArrayList<Wall>> getWallsLiveData() {
        return wallsLiveData;
    }


    private PlayerMovementStrategy playerMovementStrategy = new PlayerMovementStrategy(player);

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

    private boolean isCollidingWithWall(int newX, int newY) {
        for (Wall wall : walls) {
            if (wall.intersects(newX, newY, player.getWidth(), player.getHeight())) {
                return true;
            }
        }
        return false;
    }


    public void configureMovementStrategy(int screenWidth, int screenHeight) {
        MovementStrategy.setScreenDims(screenWidth, screenHeight);
        MovementStrategy.setCollisionChecker((x, y, width, height) -> isCollidingWithWall(x, y));
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

    public void movePlayer(MovementStrategy.MovementDirection direction) {
        playerMovementStrategy.move(player, direction);
        playerPositionLiveData.postValue(new Point(player.getX(), player.getY()));
    }

    public int getPlayerX() {
        return player.getX();
    }

    public int getPlayerY() {
        return player.getY();
    }

    public void plot(ImageView imageView, Entity entity) {

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

    public void playerReachedPortal() {
        notifyObserver();
    }

    public  ArrayList<Wall> getWalls() {
        return this.walls;
    }
}