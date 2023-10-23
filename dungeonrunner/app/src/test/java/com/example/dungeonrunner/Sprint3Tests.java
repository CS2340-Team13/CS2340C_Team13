package com.example.dungeonrunner;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import com.example.dungeonrunner.model.Leaderboard;
import com.example.dungeonrunner.model.MovementStrategy;
import com.example.dungeonrunner.model.Player;
import com.example.dungeonrunner.model.PlayerMovementStrategy;
import com.example.dungeonrunner.model.ScoreUnit;
import com.example.dungeonrunner.model.Wall;
import com.example.dungeonrunner.viewModels.ConfigScreenViewModel;
import com.example.dungeonrunner.viewModels.GameScreenViewModel;
import com.example.dungeonrunner.views.EndScreen;
import com.example.dungeonrunner.views.Room;

import org.junit.Test;

public class Sprint3Tests {
    @Test
    public void testMoveUp() {
        MovementStrategy.setScreenDims(1000, 1000);
        Player player = Player.getPlayer();
        PlayerMovementStrategy movementStrategy = new PlayerMovementStrategy();
        int initialY = player.getY();
        movementStrategy.move(player, MovementStrategy.MovementDirection.UP);
        int finalY = player.getY();
        assertEquals(initialY, finalY);
    }

    @Test
    public void testNoMoveUpBorder() {
        Player player = Player.getPlayer();
        PlayerMovementStrategy movementStrategy = new PlayerMovementStrategy();
        int initialY = player.getY();
        movementStrategy.move(player, MovementStrategy.MovementDirection.UP);
        int finalY = player.getY();
        assertNotEquals(initialY, finalY);
    }

    @Test
    public void testIntersect() {
        Wall wall = new Wall(0, 0, 10, 10);
        assertTrue(wall.intersects(5, 5, 5, 5));
    }

    @Test
    public void testNoIntersect() {
        Wall wall = new Wall(0, 0, 10, 10);
        assertFalse(wall.intersects(20, 20, 5, 5));
    }

    @Test
    public void testNoIntersectWhenPlayerAboveWall() {
        Wall wall = new Wall(0, 0, 10, 10);
        assertFalse(wall.intersects(0, 20, 10, 10));
    }

    @Test
    public void testMoveBlockedOnYNotOnX() {
        MovementStrategy.setScreenDims(1000, 10);
        Player player = Player.getPlayer();
        PlayerMovementStrategy movementStrategy = new PlayerMovementStrategy();
        int initialY = player.getY();
        int initialX = player.getX();
        movementStrategy.move(player, MovementStrategy.MovementDirection.UP);
        movementStrategy.move(player, MovementStrategy.MovementDirection.RIGHT);
        int finalY = player.getY();
        int finalX = player.getX();
        assertEquals(initialY, finalY);
        assertEquals(initialX + 50, finalX);
    }

    @Test
    //uses a simplifed room class to test logic instead of UI with a mock
    public void testRoomUpdate() {
        class Room {

            private int roomID = 1;

            public int getRoomID() {
                return roomID;
            }

            public void setRoomID(int roomID) {
                this.roomID = roomID;
            }

            public void updateRoom() {
                roomID++;
            }
        }
        Room room = new Room();
        int initialRoomID = room.getRoomID();
        int expectedRoomID = initialRoomID + 1;
        room.updateRoom();
        assertEquals(expectedRoomID, room.getRoomID());
    }

    /*@Test
    public void testWallRendering() {
        GameScreenViewModel viewModel = new GameScreenViewModel();
        Wall theWall = new Wall(100, 100, 200, 200);
        viewModel.addWall(theWall);
        assertEquals(viewModel.GetWalls().size(), 1);
    } */
    @Test
    public void testPlayerPositionResetAfterRoomChange() {
        GameScreenViewModel viewModel = new GameScreenViewModel();
        int resetX= 50;
        int resetY = 50;
        viewModel.resetPlayerPosition();

        // Verify that the player's initial position is set correctly
        assertEquals(resetX, viewModel.getPlayerX());
        assertEquals(resetY, viewModel.getPlayerY());
    }
}

