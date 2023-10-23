package com.example.dungeonrunner.views;

import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.example.dungeonrunner.model.MovementStrategy;
import com.example.dungeonrunner.viewModels.GameScreenViewModel;

public class PlayerMoveHelper {

    private PlayerMoveHelper() {
        throw new AssertionError("Cannot instantiate utility class: PlayerMovementHelper");
    }
    public static void handleKeyEvent(View view, GameScreenViewModel gameScreenViewModel, ImageView playerCharacterImageView) {
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_DPAD_UP:
                            gameScreenViewModel.movePlayer(MovementStrategy.MovementDirection.UP);
                            break;
                        case KeyEvent.KEYCODE_DPAD_DOWN:
                            gameScreenViewModel.movePlayer(MovementStrategy.MovementDirection.DOWN);
                            break;
                        case KeyEvent.KEYCODE_DPAD_LEFT:
                            gameScreenViewModel.movePlayer(MovementStrategy.MovementDirection.LEFT);
                            break;
                        case KeyEvent.KEYCODE_DPAD_RIGHT:
                            gameScreenViewModel.movePlayer(MovementStrategy.MovementDirection.RIGHT);
                            break;
                    }
                }

                gameScreenViewModel.setPosition(playerCharacterImageView);
                return true;
            }
        });
    }

    /**
     * Update the provided ImageView's position based on the player's coordinates in the ViewModel.
     */
    public static void updatePlayerPositionOnScreen(ImageView playerCharacterImageView, GameScreenViewModel gameScreenViewModel) {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone((ConstraintLayout) playerCharacterImageView.getParent());
        constraintSet.connect(playerCharacterImageView.getId(), ConstraintSet.START,
                ConstraintLayout.LayoutParams.PARENT_ID, ConstraintSet.START, gameScreenViewModel.getPlayerX());
        constraintSet.connect(playerCharacterImageView.getId(), ConstraintSet.TOP,
                ConstraintLayout.LayoutParams.PARENT_ID, ConstraintSet.TOP, gameScreenViewModel.getPlayerY());
        constraintSet.applyTo((ConstraintLayout) playerCharacterImageView.getParent());
    }
}

