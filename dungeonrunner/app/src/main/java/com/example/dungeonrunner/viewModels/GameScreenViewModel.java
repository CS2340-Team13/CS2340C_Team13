package com.example.dungeonrunner.viewModels;

import android.util.Log;
import android.widget.ImageView;
import android.os.CountDownTimer;
import androidx.lifecycle.MutableLiveData;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.lifecycle.ViewModel;

import com.example.dungeonrunner.R;
import com.example.dungeonrunner.model.Player;

public class GameScreenViewModel extends ViewModel {

    private Player player = Player.getPlayer();
    private int score = 100;
    private boolean timerRunning = false;
    private CountDownTimer timer;

    // used for updating timer easily among fragments
    private static MutableLiveData<Integer> scoreLiveData = new MutableLiveData<>();

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
        }else if (gameDifficulty.equals("Medium")) {
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
        timerRunning = true;
        timer = new CountDownTimer(100000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                score--;
                scoreLiveData.setValue(score);
            }
            @Override
            public void onFinish() {
                scoreLiveData.setValue(-1);
            }
        }.start();
    }

    public boolean isTimerRunning() {
        return timerRunning;
    }

    public MutableLiveData<Integer> getScoreLiveData() {
        return scoreLiveData;
    }
}