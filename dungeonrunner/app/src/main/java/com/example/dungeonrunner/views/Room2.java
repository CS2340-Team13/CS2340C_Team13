package com.example.dungeonrunner.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dungeonrunner.R;
import com.example.dungeonrunner.viewModels.GameScreenViewModel;


public class Room2 extends Fragment {

    private GameScreenViewModel gameScreenViewModel;
    private TextView scoreTextView;
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        gameScreenViewModel = new ViewModelProvider(requireActivity()).get(GameScreenViewModel.class);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_room2, container, false);
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView playerCharacterImageView = view.findViewById(R.id.playerCharacterImageView);
        gameScreenViewModel.setPosition(playerCharacterImageView);

        Button nextButton = view.findViewById(R.id.room2NextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(Room2.this).navigate(
                        R.id.action_Room2_to_Room3);
            }
        });

        Button prevButton = view.findViewById(R.id.room2PrevButton);
        prevButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(Room2.this).navigate(
                        R.id.action_Room2_to_Room1);
            }
        });

        scoreTextView = view.findViewById(R.id.scoreTextView);
        gameScreenViewModel.getScoreLiveData().observe(getViewLifecycleOwner(), newScore -> {
            scoreTextView.setText("Score: " + newScore);
        });
    }
}