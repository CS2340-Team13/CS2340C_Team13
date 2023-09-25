package com.example.dungeonrunner;

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

public class GameScreen extends Fragment {
    private GameScreenViewModel mViewModel;

    public static GameScreen newInstance() {
        return new GameScreen();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_screen, container, false);

        String playerName = getArguments().getString("playerName");
        String gameDifficulty = getArguments().getString("gameDifficulty");
        String selectedCharacter = getArguments().getString("selectedCharacter");

        TextView playerNameTextView = view.findViewById(R.id.playerNameTextView);
        TextView gameDifficultyTextView = view.findViewById(R.id.gameDifficultyTextView);
        TextView playerHealthTextView = view.findViewById(R.id.playerHealthTextView);
        ImageView playerCharacterImageView = view.findViewById(R.id.playerCharacterImageView);

        int health = 100;
        if (gameDifficulty.equals("Medium")) {
            health = 75;
        } else if (gameDifficulty.equals("Hard")) {
            health = 50;
        }
        playerNameTextView.setText("Player Name: " + playerName);
        gameDifficultyTextView.setText("Difficulty: " + gameDifficulty);
        playerHealthTextView.setText("Health: " + health);


        int characterImageResource = 0;
        if (selectedCharacter.equals("character1")) {
            characterImageResource = R.drawable.character1_image;
        } else if (selectedCharacter.equals("character2")) {
            characterImageResource = R.drawable.character2_image;
        } else if (selectedCharacter.equals("character3")) {
            characterImageResource = R.drawable.character3_image;
        }

        playerCharacterImageView.setImageResource(characterImageResource);


        Button endButton = view.findViewById(R.id.endButton);
        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = NavHostFragment.findNavController(GameScreen.this);
                navController.navigate(R.id.action_GameScreen_to_EndScreen);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(GameScreenViewModel.class);
        // TO-DO: Use the ViewModel
    }

}