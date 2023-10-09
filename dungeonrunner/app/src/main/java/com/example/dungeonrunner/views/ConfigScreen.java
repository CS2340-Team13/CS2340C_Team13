package com.example.dungeonrunner.views;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.example.dungeonrunner.viewModels.ConfigScreenViewModel;
import com.example.dungeonrunner.R;

public class ConfigScreen extends Fragment {

    private ConfigScreenViewModel mViewModel;
    private static final String TAG = "configScreenLog";
    private String selectedCharacter = "character1";

    public static ConfigScreen newInstance() {
        return new ConfigScreen();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(ConfigScreenViewModel.class);
        return inflater.inflate(R.layout.fragment_config_screen, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpCharacterSelection(view);
        setUpPlayButton(view);
    }


    private void setUpCharacterSelection(View view) {
        ImageView character1ImageView = view.findViewById(R.id.imageViewCharacter1);
        ImageView character2ImageView = view.findViewById(R.id.imageViewCharacter2);
        ImageView character3ImageView = view.findViewById(R.id.imageViewCharacter3);

        character1ImageView.setTag("character1");
        character2ImageView.setTag("character2");
        character3ImageView.setTag("character3");

        View.OnClickListener characterClickListener = v -> {
            character1ImageView.setAlpha(0.5f);
            character2ImageView.setAlpha(0.5f);
            character3ImageView.setAlpha(0.5f);
            v.setAlpha(1.0f);

            selectedCharacter = v.getTag().toString();
        };

        character1ImageView.setOnClickListener(characterClickListener);
        character2ImageView.setOnClickListener(characterClickListener);
        character3ImageView.setOnClickListener(characterClickListener);
    }

    private void setUpPlayButton(View view) {
        EditText editTextName = view.findViewById(R.id.editTextName);
        RadioGroup radioGroupDifficulty = view.findViewById(R.id.radioGroupDifficulty);
        ImageView character1ImageView = view.findViewById(R.id.imageViewCharacter1);
        ImageView character2ImageView = view.findViewById(R.id.imageViewCharacter2);
        ImageView character3ImageView = view.findViewById(R.id.imageViewCharacter3);

        Button playButton = view.findViewById(R.id.playButton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString().trim();
                int difficulty = radioGroupDifficulty.getCheckedRadioButtonId();
                String error = mViewModel.submit(name, selectedCharacter, difficulty);
                if (error.equals("")) {
                    NavHostFragment.findNavController(ConfigScreen.this).navigate(
                        R.id.action_ConfigScreen_to_Room1);
                } else {
                    editTextName.setError(error);
                }
            }
        });
    }
}