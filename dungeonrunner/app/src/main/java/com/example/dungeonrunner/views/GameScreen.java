package com.example.dungeonrunner.views;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
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
    private int currentRoomIndex = 0; // Index of the current room
    private Fragment[] roomFragments = {new Room1(), new Room2(), new Room3()};





    public static GameScreen newInstance() {
        return new GameScreen();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(GameScreenViewModel.class);
        View view = inflater.inflate(R.layout.fragment_game_screen, container, false);

        NavHostFragment.findNavController(this)
                .navigate(R.id.action_GameScreen_to_Room1);

        return view;
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


        Button nextButton = view.findViewById(R.id.gameScreenNextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                while (currentRoomIndex < roomFragments.length) {
                    currentRoomIndex++;
                    getChildFragmentManager().beginTransaction()
                            .replace(R.id.roomContainer, roomFragments[currentRoomIndex])
                            .commit();
                }
            }
        });





    }



}