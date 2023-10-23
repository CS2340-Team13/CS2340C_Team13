package com.example.dungeonrunner.viewModels;

import android.graphics.Point;
import android.widget.ImageView;
import android.os.CountDownTimer;
import androidx.lifecycle.MutableLiveData;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.lifecycle.ViewModel;

import com.example.dungeonrunner.R;
import com.example.dungeonrunner.model.MovementStrategy;
import com.example.dungeonrunner.model.Player;
import com.example.dungeonrunner.model.PlayerMovementStrategy;

public class GameScreenViewModel extends ViewModel {

    private Player player = Player.getPlayer();
    private int score = 100;
    private boolean timerRunning = false;
    private CountDownTimer timer;

    // used for updating timer easily among fragments
    private static MutableLiveData<Integer> scoreLiveData = new MutableLiveData<>();

    private MutableLiveData<Point> playerPositionLiveData = new MutableLiveData<>(new Point(player.getX(), player.getY()));

    private MovementStrategy playerMovementStrategy = new PlayerMovementStrategy();


    public int getCharacterImageResource() {
        String selectedCharacter = player.getSelectedCharacter();
        int characterImageResource = 0;
        if (selectedCharacter.equals("character1")) {
            characterImageResource = R.drawable.character1_image;
        } else if (selectedCharacter.equals("character2")) {
            characterImageResource = R.drawable.character2_image;
        } else if (selectedCharacter.equals("character3")) {
            characterImageResource = R.drawable.character3_image;
        }
        return characterImageResource;
    }

    public int getHealth() {
        String gameDifficulty = player.getGameDifficulty();
        int health = 100;
        if (gameDifficulty.equals("Easy")) {
            health = 100;
        } else if (gameDifficulty.equals("Medium")) {
            health = 75;
        } else if (gameDifficulty.equals("Hard")) {
            health = 50;
        }
        return health;
    }

    public String getPlayerName() {
        return player.getPlayerName();
    }

    public String getGameDifficulty() {
        return player.getGameDifficulty();
    }


    public void resetPlayerPosition() {
        player.setX(50);
        player.setY(50);
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

    public void setPosition(ImageView imageView) {

        int characterImageResource = getCharacterImageResource();
        imageView.setImageResource(characterImageResource);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone((ConstraintLayout) imageView.getParent());
        constraintSet.connect(imageView.getId(), ConstraintSet.START,
                ConstraintLayout.LayoutParams.PARENT_ID, ConstraintSet.START, player.getX());
        constraintSet.connect(imageView.getId(), ConstraintSet.TOP,
                ConstraintLayout.LayoutParams.PARENT_ID, ConstraintSet.TOP, player.getY());
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
}