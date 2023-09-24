package com.example.dungeonrunner;

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

public class configScreen extends Fragment {

    private ConfigScreenViewModel mViewModel;
    private static final String TAG = "configScreenLog";

    public static configScreen newInstance() {
        return new configScreen();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_config_screen, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ConfigScreenViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpNameInput(view);
        setUpDifficultySelection(view);
        setUpCharacterSelection(view);
        setUpStartButton(view);
    }

    private void setUpNameInput(View view) {
        // Setup for name input, if any special logic is required
        // Currently, we are just retrieving the name on the button click
    }

    private void setUpDifficultySelection(View view) {
        RadioGroup radioGroupDifficulty = view.findViewById(R.id.radioGroupDifficulty);
        // Any additional setup for difficulty RadioGroup, if required
    }

    private void setUpCharacterSelection(View view) {
        ImageView character1ImageView = view.findViewById(R.id.imageViewCharacter1);
        ImageView character2ImageView = view.findViewById(R.id.imageViewCharacter2);
        ImageView character3ImageView = view.findViewById(R.id.imageViewCharacter3);

        // Using tags to easily identify selected character
        character1ImageView.setTag("character1");
        character2ImageView.setTag("character2");
        character3ImageView.setTag("character3");
        View.OnClickListener characterClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset all to normal opacity
                character1ImageView.setAlpha(0.5f);
                character2ImageView.setAlpha(0.5f);
                character3ImageView.setAlpha(0.5f);

                // Highlight selected character
                v.setAlpha(1.0f);
            }
        };

        character1ImageView.setOnClickListener(characterClickListener);
        character2ImageView.setOnClickListener(characterClickListener);
        character3ImageView.setOnClickListener(characterClickListener);
    }

    private void setUpStartButton(View view) {
        EditText editTextName = view.findViewById(R.id.editTextName);
        RadioGroup radioGroupDifficulty = view.findViewById(R.id.radioGroupDifficulty);
        ImageView character1ImageView = view.findViewById(R.id.imageViewCharacter1);
        ImageView character2ImageView = view.findViewById(R.id.imageViewCharacter2);
        ImageView character3ImageView = view.findViewById(R.id.imageViewCharacter3);

        Button startButton = view.findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ... Logic to handle the button click, gather data, and navigate
                String name = editTextName.getText().toString().trim();
                if (name.isEmpty() || name.equals("null")) {
                    editTextName.setError("Invalid name. Can't be empty or null");
                    return;
                }
                // move to next screen;
                Log.d(TAG, "Start Button Clicked - Config Screen");

                // creating bundle to pass data to next screen
                Bundle bundle = new Bundle();
                bundle.putString("playerName", name);

                String character;
                if (character1ImageView.getAlpha() == 1.0f) {
                    character = "character1";
                } else if (character2ImageView.getAlpha() == 1.0f) {
                    character = "character2";
                } else if (character3ImageView.getAlpha() == 1.0f) {
                    character = "character3";
                } else {
                    character = "Unspecified";
                }
                bundle.putString("selectedCharacter", character);

                int selectedId = radioGroupDifficulty.getCheckedRadioButtonId();
                String difficulty;
                if (selectedId == R.id.radioButtonEasy) {
                    difficulty = "Easy";
                } else if (selectedId == R.id.radioButtonMedium) {
                    difficulty = "Medium";
                } else if (selectedId == R.id.radioButtonHard) {
                    difficulty = "Hard";
                } else {
                    difficulty = "Unspecified"; // or handle this case differently
                }
                bundle.putString("gameDifficulty", difficulty);

                NavHostFragment.findNavController(configScreen.this).navigate(R.id.action_configScreen_to_gameScreen, bundle);


            }
        });
    }
}