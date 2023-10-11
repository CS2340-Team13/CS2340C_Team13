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
import android.widget.EditText;
import android.widget.TextView;

import com.example.dungeonrunner.R;
import com.example.dungeonrunner.viewModels.EndScreenViewModel;
import com.example.dungeonrunner.viewModels.GameScreenViewModel;

public class EndScreen extends Fragment {

    private EndScreenViewModel mViewModel;

    private GameScreenViewModel gmViewModel;

    private static final String TAG = "endScreenLog";

    private TextView curScoreTextView;

    public static EndScreen newInstance() {
        return new EndScreen();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_end_screen, container, false);
    }

    @Override
    //add score to the leaderboard, then display.
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button restartButton = view.findViewById(R.id.restartButton);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { onRestartButtonClicked(); }
        });

        curScoreTextView = view.findViewById(R.id.curScore);
        curScoreTextView.setText("Your score was " + gmViewModel.getScoreLiveData());

    }

    private void onRestartButtonClicked() {
        Log.d(TAG, "Restart Button Clicked");
        NavController navController = NavHostFragment.findNavController(EndScreen.this);
        navController.navigate(R.id.action_EndScreen_to_StartScreen);
    }
}