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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dungeonrunner.R;
import com.example.dungeonrunner.model.ScoreUnit;
import com.example.dungeonrunner.viewModels.EndScreenViewModel;
import com.example.dungeonrunner.viewModels.GameScreenViewModel;

import java.util.ArrayList;

public class GameOverScreen extends Fragment {

    private EndScreenViewModel endScreenViewModel;

    private GameScreenViewModel gmViewModel;

    private static final String TAG = "endScreenLog";

    private TextView curScoreTextView;

    private String[] scoreIDs = {"score1", "score2", "score3", "score4", "score5"};
    private String packageName;

    public static GameOverScreen newInstance() {
        return new GameOverScreen();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        gmViewModel = new ViewModelProvider(requireActivity()).get(GameScreenViewModel.class);
        endScreenViewModel = new ViewModelProvider(requireActivity()).get(EndScreenViewModel.class);
        packageName = getContext().getPackageName();
        return inflater.inflate(R.layout.fragment_end_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button restartButton = view.findViewById(R.id.restartButton);

        ImageView playerCharacterImageView = view.findViewById(R.id.playerCharacterImageView);
        endScreenViewModel.setPosition(playerCharacterImageView);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRestartButtonClicked();
            }
        });

        int score = 0;
        curScoreTextView = view.findViewById(R.id.curScore);
        curScoreTextView.setText("Your score was " + score);
        ArrayList<ScoreUnit> results = endScreenViewModel.getResults(score);
        for (int i = 0; i < results.size(); i++) {
            int resID = getContext().getResources().getIdentifier(scoreIDs[i], "id", packageName);
            TextView scoreRowView = view.findViewById(resID);
            scoreRowView.setText(results.get(i).toString());
        }

    }

    private void onRestartButtonClicked() {
        Log.d(TAG, "Restart Button Clicked");
        NavController navController = NavHostFragment.findNavController(EndScreen.this);
        navController.navigate(R.id.action_EndScreen_to_StartScreen);
    }
}