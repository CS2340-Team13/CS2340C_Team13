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
import com.example.dungeonrunner.viewModels.ConfigScreenViewModel;
import com.example.dungeonrunner.viewModels.GameScreenViewModel;


public class Room3 extends Fragment {

    private GameScreenViewModel mViewModel;
    private TextView scoreTextView;
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mViewModel = new ViewModelProvider(requireActivity()).get(GameScreenViewModel.class);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_room3, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView playerCharacterImageView = view.findViewById(R.id.playerCharacterImageView);
        mViewModel.setPosition(playerCharacterImageView);

        Button prevButton = view.findViewById(R.id.room3PrevButton);
        prevButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(Room3.this).navigate(
                        R.id.action_Room3_to_Room2);
            }
        });

        Button endButton = view.findViewById(R.id.room3EndButton);
        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(Room3.this).navigate(
                        R.id.action_Room3_to_EndScreen);
            }
        });

        scoreTextView = view.findViewById(R.id.scoreTextView);
        mViewModel.getScoreLiveData().observe(getViewLifecycleOwner(), newScore -> {
            scoreTextView.setText("Score: " + newScore);
        });
    }
}