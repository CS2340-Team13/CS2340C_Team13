package com.example.dungeonrunner.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.widget.Button;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.dungeonrunner.R;


public class Room3 extends Fragment {

    public int getNavigationAction() {
        return R.id.action_Room3_to_EndScreen;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for Room 1.
        View view = inflater.inflate(R.layout.fragment_room3, container, false);

//        Button nextButton = view.findViewById(R.id.room3NextButton);
//        nextButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                NavHostFragment.findNavController(Room3.this).navigate(
//                        R.id.action_Room3_to_EndScreen);
//            }
//        });

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

        return view;
    }
}