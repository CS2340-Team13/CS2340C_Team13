package com.example.dungeonrunner.views;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.dungeonrunner.viewModels.GameScreenViewModel;
import com.example.dungeonrunner.R;
import com.example.dungeonrunner.model.Player;

public class GameScreen extends Fragment {
    private GameScreenViewModel mViewModel;

    public static GameScreen newInstance() {
        return new GameScreen();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(GameScreenViewModel.class);
        return inflater.inflate(R.layout.fragment_game_screen, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView playerNameTextView = view.findViewById(R.id.playerNameTextView);
        TextView gameDifficultyTextView = view.findViewById(R.id.gameDifficultyTextView);
        TextView playerHealthTextView = view.findViewById(R.id.playerHealthTextView);
        ImageView playerCharacterImageView = view.findViewById(R.id.playerCharacterImageView);

        int health = mViewModel.getHealth();
        int characterImageResource = mViewModel.getCharacterImageResource();
        String playerName = mViewModel.getPlayerName();
        String gameDifficulty = mViewModel.getGameDifficulty();

        playerNameTextView.setText("Player Name: " + playerName);
        gameDifficultyTextView.setText("Difficulty: " + gameDifficulty);
        playerHealthTextView.setText("Health: " + health);
        playerCharacterImageView.setImageResource(characterImageResource);


        Button endButton = view.findViewById(R.id.endButton);
        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(GameScreen.this).navigate(
                        R.id.action_GameScreen_to_EndScreen);
            }
        });
    }

}