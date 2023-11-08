package com.example.dungeonrunner.views;

import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.dungeonrunner.model.MovementStrategy;
import com.example.dungeonrunner.model.Player;
import com.example.dungeonrunner.viewModels.GameScreenViewModel;

public class PlayerMoveHelper {

    private static Player player = Player.getPlayer();

    public static void handleKeyEvent(View view, GameScreenViewModel gameScreenViewModel,
                                      ImageView playerCharacterImageView) {
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    System.out.println("KEYDOWN");
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
                            gameScreenViewModel.movePlayer(
                                    MovementStrategy.MovementDirection.RIGHT);
                            break;
                        default:
                            break;
                    }
                }

                gameScreenViewModel.plot(playerCharacterImageView, player);
                return true;
            }
        });
    }
}

