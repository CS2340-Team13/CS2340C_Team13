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

public class gameScreen extends Fragment {
    private GameScreenViewModel mViewModel;

    public static gameScreen newInstance() {
        return new gameScreen();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_screen, container, false);

        String playerName = getArguments().getString("playerName");
        String gameDifficulty = getArguments().getString("gameDifficulty");
        String selectedCharacter = getArguments().getString("selectedCharacter");

        // Find UI elements and set their values or click listeners as needed
        TextView playerNameTextView = view.findViewById(R.id.playerNameTextView);
        TextView gameDifficultyTextView = view.findViewById(R.id.gameDifficultyTextView);
        Button endButton = view.findViewById(R.id.endButton);  // You can set a click listener for this button if needed

        // Setting the values
        playerNameTextView.setText("Player Name: " + playerName);
        gameDifficultyTextView.setText("Difficulty: " + gameDifficulty);

        ImageView playerCharacterImageView = view.findViewById(R.id.playerCharacterImageView);
        // Determine the correct image based on the selectedCharacter string
        int characterImageResource = 0;
        switch (selectedCharacter) {
            case "character1":
                characterImageResource = R.drawable.character1_image;
                break;
            case "character2":
                characterImageResource = R.drawable.character2_image;
                break;
            case "character3":
                characterImageResource = R.drawable.character3_image;
                break;
        }

        playerCharacterImageView.setImageResource(characterImageResource);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(GameScreenViewModel.class);
        // TODO: Use the ViewModel
    }

}