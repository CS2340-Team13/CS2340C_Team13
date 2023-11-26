package com.example.dungeonrunner.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dungeonrunner.R;
import com.example.dungeonrunner.model.Wall;
import com.example.dungeonrunner.viewModels.GameScreenViewModel;
import com.example.dungeonrunner.viewModels.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class Room extends Fragment implements Observer {

    private GameScreenViewModel gameScreenViewModel;
    private TextView scoreTextView;

    private TextView healthTextView;
    private boolean playerInPortal;
    private int roomID = 1;
    private List<View> wallViews = new ArrayList<>();
    public ImageView enemy1ImageView;
    public  ImageView enemy2ImageView;
    public ImageView powerUp1ImageView;
    public  ImageView powerUp2ImageView;
    private Timer timer = new Timer();
    private Handler handler = new Handler();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            roomID = getArguments().getInt("roomID", 1);
        }
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        gameScreenViewModel = new ViewModelProvider(
                requireActivity()).get(GameScreenViewModel.class);
        gameScreenViewModel.registerObserver(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("ID START:" + roomID);
        int layoutResId = getLayoutResIdForRoom();
        return inflater.inflate(layoutResId, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (!gameScreenViewModel.isTimerRunning()) {
            gameScreenViewModel.startTimer();
        }

        view.setFocusableInTouchMode(true);
        view.requestFocus();

        gameScreenViewModel.resetPlayerPosition();
        ImageView playerCharacterImageView = view.findViewById(R.id.playerCharacterImageView);
        gameScreenViewModel.playerMovementStrategy.handleKeyEvent(view, gameScreenViewModel, playerCharacterImageView);


        scoreTextView = view.findViewById(R.id.scoreTextView);
        gameScreenViewModel.getScoreLiveData().observe(getViewLifecycleOwner(), newScore -> {
            scoreTextView.setText("Score: " + newScore);
            if (newScore <= 0) {
                gameScreenViewModel.stopTimer();
                NavHostFragment.findNavController(Room.this).navigate(R.id.action_Room_to_GameOverScreen);
            }
        });

        healthTextView = view.findViewById(R.id.playerHealthTextView);
        gameScreenViewModel.getHealthLiveData().observe(getViewLifecycleOwner(), newHealth -> {
            healthTextView.setText("Health: " + newHealth);
            if (newHealth <= 0) {
                NavHostFragment.findNavController(Room.this).navigate(R.id.action_Room_to_GameOverScreen);
            }
        });

        gameScreenViewModel.getPlayerPositionLiveData().observe(
                getViewLifecycleOwner(), newPosition -> {
                ImageView portalImageView = getView().findViewById(R.id.portalImageView);

                if (viewsOverlap(playerCharacterImageView, portalImageView)) {
                    if (!playerInPortal) {
                        playerInPortal = true;
                        gameScreenViewModel.playerReachedPortal(playerCharacterImageView);
                    }
                } else {
                    playerInPortal = false;
                }
            });

        gameScreenViewModel.getWallsLiveData().observe(this, newWallsList -> {
            renderWalls(newWallsList);
        });


        view.post(() -> {

            int screenWidth = view.getWidth();
            int screenHeight = view.getHeight();
            int playerWidth = playerCharacterImageView.getWidth();
            int playerHeight = playerCharacterImageView.getHeight();
            gameScreenViewModel.configureMovementStrategy(screenWidth, screenHeight);
            gameScreenViewModel.configurePlayerMovement(playerWidth, playerHeight);
            gameScreenViewModel.instantiateEnemyInstances(roomID);
            gameScreenViewModel.instantiatePowerUps(roomID);
            setEnemyImageViews(view);
            setPowerUpImageViews(view);
            gameScreenViewModel.spawnPowerUps(powerUp1ImageView, powerUp2ImageView, gameScreenViewModel.PU1, gameScreenViewModel.PU2);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(() -> gameScreenViewModel.updateEnemy(enemy1ImageView, enemy2ImageView, gameScreenViewModel.enemyMovementStrategy1, gameScreenViewModel.enemyMovementStrategy2));
                }
            }, 0, 20);
        });
    }


    private int getLayoutResIdForRoom() {
        switch (roomID) {
        case 1:
            return R.layout.fragment_room1;
        case 2:
            return R.layout.fragment_room2;
        case 3:
            return R.layout.fragment_room3;
        default:
            return R.layout.fragment_room1;
        }
    }

    private void setEnemyImageViews(View view) {
        if (roomID == 1) {
            enemy1ImageView = view.findViewById(R.id.enemy1Room1ImageView);
            enemy2ImageView = view.findViewById(R.id.enemy2Room1ImageView);
        }
        if (roomID == 2) {
            enemy1ImageView = view.findViewById(R.id.enemy1Room2ImageView);
            enemy2ImageView = view.findViewById(R.id.enemy2Room2ImageView);
        }
        if (roomID == 3) {
            enemy1ImageView = view.findViewById(R.id.enemy1Room3ImageView);
            enemy2ImageView = view.findViewById(R.id.enemy2Room3ImageView);
        }
    }
        private void setPowerUpImageViews(View view) {
            if (roomID == 1) {
                powerUp1ImageView = view.findViewById(R.id.PowerUp1Room1ImageView);
                powerUp2ImageView = view.findViewById(R.id.PowerUp2Room1ImageView);
            }
            if (roomID == 2) {
                powerUp1ImageView = view.findViewById(R.id.PowerUp1Room2ImageView);
                powerUp2ImageView = view.findViewById(R.id.PowerUp2Room2ImageView);
            }
            if (roomID == 3) {
                powerUp1ImageView = view.findViewById(R.id.PowerUp1Room3ImageView);
                powerUp2ImageView = view.findViewById(R.id.PowerUp2Room3ImageView);
            }
    }


    private static boolean viewsOverlap(View view1, View view2) {
        Rect rect1 = new Rect();
        view1.getHitRect(rect1);

        Rect rect2 = new Rect();
        view2.getHitRect(rect2);

        if (rect1.intersect(rect2)) {
            int overlapArea = rect1.width() * rect1.height();
            int halfView1Area = (view1.getWidth() * view1.getHeight()) / 2;
            return overlapArea >= halfView1Area;
        }

        return false;
    }


    public void update() {
        roomID++;
        System.out.println("RoomID: " + roomID);
        if (roomID >= 4) {
            NavHostFragment.findNavController(Room.this).navigate(R.id.action_Room_to_EndScreen);
            return;
        }

        Bundle args = new Bundle();
        args.putInt("roomID", roomID);
        NavHostFragment.findNavController(this).navigate(R.id.action_self, args);
    }

    private void renderWalls(List<Wall> walls) {
        View rootView = getView();
        if (rootView == null) {
            return;
        }

        ConstraintLayout parentLayout = rootView.findViewById(R.id.roomLayout);
        if (parentLayout == null) {
            return;
        }

        // Remove previously added wall views.
        for (View wallView : wallViews) {
            parentLayout.removeView(wallView);
        }
        wallViews.clear();

        for (Wall wall : walls) {
            List<View> wallTiles = addWallToLayout(wall, parentLayout);
            wallViews.addAll(wallTiles);
        }
    }

    private List<View> addWallToLayout(Wall wall, ConstraintLayout parentLayout) {
        int wallImageRes = R.drawable.wall;
        int tileSize = getResources().getDimensionPixelSize(R.dimen.wall_tile_size);

        int tilesWide = (int) Math.ceil((double) wall.getWidth() / tileSize);
        int tilesHigh = (int) Math.ceil((double) wall.getHeight() / tileSize);

        // Adjusting the wall's dimensions
        int adjustedWidth = tilesWide * tileSize;
        int adjustedHeight = tilesHigh * tileSize;

        List<View> addedTiles = new ArrayList<>();

        for (int i = 0; i < tilesWide; i++) {
            for (int j = 0; j < tilesHigh; j++) {

                ImageView wallTileImageView = new ImageView(getContext());
                wallTileImageView.setImageResource(wallImageRes);
                wallTileImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

                int tileWidth = ((i == tilesWide - 1) && adjustedWidth % tileSize != 0)
                        ? adjustedWidth % tileSize : tileSize;
                int tileHeight = ((j == tilesHigh - 1) && adjustedHeight % tileSize != 0)
                        ? adjustedHeight % tileSize : tileSize;

                ConstraintLayout.LayoutParams tileLayoutParams = new ConstraintLayout.LayoutParams(
                        tileWidth, tileHeight);
                tileLayoutParams.leftToLeft = ConstraintSet.PARENT_ID;
                tileLayoutParams.topToTop = ConstraintSet.PARENT_ID;
                tileLayoutParams.setMargins(wall.getX() + i * tileSize,
                        wall.getY() + j * tileSize, 0, 0);

                wallTileImageView.setLayoutParams(tileLayoutParams);
                wallTileImageView.setId(View.generateViewId());

                parentLayout.addView(wallTileImageView);
                addedTiles.add(wallTileImageView);
            }
        }

        // Update the wall's boundaries
        wall.setWidth(adjustedWidth);
        wall.setHeight(adjustedHeight);

        return addedTiles;
    }
}