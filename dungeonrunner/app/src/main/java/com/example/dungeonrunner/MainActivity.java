package com.example.dungeonrunner;


import android.os.Bundle;
import android.widget.Button;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "HERE1");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);
        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(v -> {
            Log.d(TAG, "HERE");
        });

    }
}
