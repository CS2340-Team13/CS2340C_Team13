package com.example.dungeonrunner;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class StartScreen extends Fragment {

    private StartScreenViewModel mViewModel;
    private static final String TAG = "startScreenLog";

    public static StartScreen newInstance() {
        return new StartScreen();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_screen, container, false);

        Button startButton = view.findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStartButtonClicked();
            }
        });

        Button exitButton = view.findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onExitButtonClicked();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(StartScreenViewModel.class);
        // TO-DO: Use the ViewModel
    }

    private void onStartButtonClicked() {
        Log.d(TAG, "Start Button Clicked");
        NavHostFragment.findNavController(this).navigate(R.id.action_StartScreen_to_ConfigScreen);
    }

    private void onExitButtonClicked() {
        Log.d(TAG, "Exit Button Clicked");
        if (getActivity() != null) {
            getActivity().finish();
        }
    }
}
