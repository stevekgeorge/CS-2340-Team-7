package com.cs2340team7.project.models;
import android.view.KeyEvent;

import com.badlogic.gdx.maps.tiled.TiledMap;

public class GeneralGabeMovement implements movementStrategy {
    private static int SPEED = 10;
    Player player;

    public void move(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
            if (player.canMove(player.getX(), player.getY() + SPEED)) {
                player.updatePosition(player.getX(), player.getY() + SPEED);
            }
        } else if (keyCode == keyEvent.KEYCODE_DPAD_DOWN) {
            if (player.canMove(player.getX(), player.getY() - SPEED)) {
                player.updatePosition(player.getX(), player.getY() - SPEED);
            }
        } else if (keyCode == keyEvent.KEYCODE_DPAD_RIGHT) {
            if (player.canMove(player.getX() + SPEED, player.getY())) {
                player.updatePosition(player.getX() + SPEED, player.getY() );
            }
        } else if (keyCode == keyEvent.KEYCODE_DPAD_LEFT) {
            if (player.canMove(player.getX() - SPEED, player.getY())) {
                player.updatePosition(player.getX()- SPEED, player.getY());
            }
        }
    }
}
