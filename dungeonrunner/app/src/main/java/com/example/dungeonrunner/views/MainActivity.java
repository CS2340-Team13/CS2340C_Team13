package com.example.dungeonrunner.views;


import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dungeonrunner.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Initializing Main Activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
