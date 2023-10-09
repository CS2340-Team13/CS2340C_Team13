package com.example.dungeonrunner.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dungeonrunner.R;
import com.example.dungeonrunner.viewModels.GameScreenViewModel;


public class Room1 extends Fragment {

    private GameScreenViewModel mViewModel;
    public int getNavigationAction() {
        return R.id.action_Room1_to_Room2;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(GameScreenViewModel.class);
        return inflater.inflate(R.layout.fragment_room1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView playerCharacterImageView = view.findViewById(R.id.playerCharacterImageView);
        mViewModel.setPosition(playerCharacterImageView);

        Button nextButton = view.findViewById(R.id.room1NextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(Room1.this).navigate(
                        R.id.action_Room1_to_Room2);
            }
        });
    }

}