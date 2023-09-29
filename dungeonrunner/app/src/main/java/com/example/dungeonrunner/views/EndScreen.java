package com.example.dungeonrunner.views;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dungeonrunner.viewModels.EndScreenViewModel;
import com.example.dungeonrunner.R;

public class EndScreen extends Fragment {

    private EndScreenViewModel mViewModel;

    public static EndScreen newInstance() {
        return new EndScreen();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(EndScreenViewModel.class);
        return inflater.inflate(R.layout.fragment_end_screen, container, false);
    }
}