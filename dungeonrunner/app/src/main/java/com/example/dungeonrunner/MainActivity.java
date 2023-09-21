package com.example.dungeonrunner;


import android.os.Bundle;
import android.widget.Button;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Initializing Main Activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
