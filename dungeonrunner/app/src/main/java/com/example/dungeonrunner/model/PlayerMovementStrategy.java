package com.example.dungeonrunner.model;

import android.graphics.Point;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.dungeonrunner.viewModels.GameScreenViewModel;
import com.example.dungeonrunner.viewModels.Observable;
import com.example.dungeonrunner.viewModels.EnemyObserver;
import com.example.dungeonrunner.viewModels.Observer;
import com.example.dungeonrunner.viewModels.PlayerObservable;

import java.util.ArrayList;
import java.util.List;

public class PlayerMovementStrategy extends MovementStrategy implements PlayerObservable { // implements Observable
    private static final int MOVE_DISTANCE = 25;

    public PlayerMovementStrategy(Character character) {
        super(character);
    }

    public enum MovementDirection {
        UP, DOWN, LEFT, RIGHT
    }

    MovementDirection currentMovement;

    // List to store enemy observes
    private final List<EnemyObserver> enemyObservers = new ArrayList<>();


    @Override
    public void move() {
        int proposedX = character.getX();
        int proposedY = character.getY();

        switch (currentMovement) {

        case UP:
            proposedY -= MOVE_DISTANCE;
            break;
        case DOWN:
            proposedY += MOVE_DISTANCE;
            break;
        case LEFT:
            proposedX -= MOVE_DISTANCE;
            break;
        case RIGHT:
            proposedX += MOVE_DISTANCE;
            break;
        default:
            break;
        }

        if (!willCollide(proposedX, proposedY)) {
            character.setX(proposedX);
            character.setY(proposedY);
        }

        notifyObserver(); // notify enemies with player's location
        // Notify observer
        // notify observer updates each enemy
        // each enemy update checks its position against the player's position and decreases player health accordingly
    }

    public void handleKeyEvent(View view, GameScreenViewModel gameScreenViewModel,
                                      ImageView characterImageView) {
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    System.out.println("KEYDOWN");
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_DPAD_UP:
                            currentMovement = MovementDirection.UP;
                            break;
                        case KeyEvent.KEYCODE_DPAD_DOWN:
                            currentMovement = MovementDirection.DOWN;
                            break;
                        case KeyEvent.KEYCODE_DPAD_LEFT:
                            currentMovement = MovementDirection.LEFT;
                            break;
                        case KeyEvent.KEYCODE_DPAD_RIGHT:
                            currentMovement = MovementDirection.RIGHT;
                            break;
                        default:
                            return true;
                    }
                }
                move();
                gameScreenViewModel.playerPositionLiveData.postValue(new Point(character.getX(), character.getY()));
                gameScreenViewModel.plot(characterImageView, character);
                return true;
            }
        });
    }


    @Override
    public void registerObserver(EnemyObserver o) {
        enemyObservers.add(o);
    }
    public void removeObserver(EnemyObserver o) {
        enemyObservers.remove(o);
    }

    @Override
    public void notifyObserver() {
        for (EnemyObserver observer : enemyObservers) {
            observer.update(new Point(character.getX(), character.getY())); // 'null' can be replaced with relevant data
        }
    }


}
