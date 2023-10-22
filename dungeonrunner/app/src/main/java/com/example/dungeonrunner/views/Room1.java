package com.example.dungeonrunner.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.widget.Button;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dungeonrunner.R;
import com.example.dungeonrunner.viewModels.GameScreenViewModel;


public class Room1 extends Fragment {

    private GameScreenViewModel gameScreenViewModel;
    private TextView scoreTextView;
    public int getNavigationAction() {
        return R.id.action_Room1_to_Room2;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        gameScreenViewModel = new ViewModelProvider(
                requireActivity()).get(GameScreenViewModel.class);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //gameScreenViewModel = new ViewModelProvider(this).get(GameScreenViewModel.class);
        return inflater.inflate(R.layout.fragment_room1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        if (!gameScreenViewModel.isTimerRunning()) {
            gameScreenViewModel.startTimer();
        }

        // need to set the view in focus so that the key presses are captured.
        view.setFocusableInTouchMode(true);
        view.requestFocus();

        ImageView playerCharacterImageView = view.findViewById(R.id.playerCharacterImageView);
        gameScreenViewModel.setPosition(playerCharacterImageView);
        PlayerMoveHelper.handleKeyEvent(view, gameScreenViewModel, playerCharacterImageView);

        scoreTextView = view.findViewById(R.id.scoreTextView);
        gameScreenViewModel.getScoreLiveData().observe(getViewLifecycleOwner(), newScore -> {
            scoreTextView.setText("Score: " + newScore);
        });

        // to check if player reaches exit zone
        gameScreenViewModel.getPlayerPositionLiveData().observe(getViewLifecycleOwner(), newPosition -> {
            checkForRoomTransition();
        });
    }

    private void checkForRoomTransition() {
        ImageView portalImageView = getView().findViewById(R.id.portalImageView);
        ImageView playerCharacterImageView = getView().findViewById(R.id.playerCharacterImageView);

        // if overlap of player and portal, switch room.
        if (viewsOverlap(playerCharacterImageView, portalImageView)) {
            NavHostFragment.findNavController(Room1.this).navigate(R.id.action_Room1_to_Room2);
        }
    }
    private static boolean viewsOverlap(View view1, View view2) {
        Rect rect1 = new Rect();
        view1.getHitRect(rect1);

        Rect rect2 = new Rect();
        view2.getHitRect(rect2);

        // Check for sufficient overlap
        if (rect1.intersect(rect2)) {
            int overlapArea = rect1.width() * rect1.height();
            int halfView1Area = (view1.getWidth() * view1.getHeight()) / 2;
            return overlapArea >= halfView1Area;
        }

        return false;
    }

}