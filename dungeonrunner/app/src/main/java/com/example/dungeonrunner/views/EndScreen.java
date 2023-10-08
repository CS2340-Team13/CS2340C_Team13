package com.example.dungeonrunner.views;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dungeonrunner.viewModels.EndScreenViewModel;
import com.example.dungeonrunner.R;
public class EndScreen extends Fragment {

    private EndScreenViewModel mViewModel;

    private static final String TAG = "endScreenLog";

    public static EndScreen newInstance() {
        return new EndScreen();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_end_screen, container, false);
        Button restartButton = view.findViewById(R.id.restartButton);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = NavHostFragment.findNavController(EndScreen.this);
                navController.navigate(R.id.action_EndScreen_to_StartScreen);
            }
        });
        return view;
    }

    private void onRestartButtonClicked() {
        Log.d(TAG, "Restart Button Clicked");
        NavController navController = NavHostFragment.findNavController(EndScreen.this);
        navController.navigate(R.id.action_EndScreen_to_StartScreen);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(EndScreenViewModel.class);
        // TO-DO: Use the ViewModel
    }

}