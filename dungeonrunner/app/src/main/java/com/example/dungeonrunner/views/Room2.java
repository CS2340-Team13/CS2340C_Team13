package com.example.dungeonrunner.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.widget.Button;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dungeonrunner.R;
import com.example.dungeonrunner.viewModels.GameScreenViewModel;


public class Room2 extends Fragment {

    private GameScreenViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(GameScreenViewModel.class);
        return inflater.inflate(R.layout.fragment_room2, container, false);
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView playerCharacterImageView = view.findViewById(R.id.playerCharacterImageView);
        int startXValue = getResources().getInteger(R.integer.start_x);
        int startYValue = getResources().getInteger(R.integer.start_y);
        mViewModel.setPosition(playerCharacterImageView, startXValue, startYValue);

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

    }
}