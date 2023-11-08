package com.example.dungeonrunner.model;

import android.graphics.Point;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.dungeonrunner.viewModels.GameScreenViewModel;

public class PlayerMovementStrategy extends MovementStrategy { // implements Observable
    private static final int MOVE_DISTANCE = 25;

    public PlayerMovementStrategy(Character character) {
        super(character);
    }


    public enum MovementDirection {
        UP, DOWN, LEFT, RIGHT
    }

    MovementDirection currentMovement;

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

}
