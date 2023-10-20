package com.cs2340team7.project.models;
import android.view.KeyEvent;

import com.badlogic.gdx.Game;

public class purplePersian implements movementStrategy {


    private static int SPEED = 1;
    Player player;
    GameDataModel dataModel;
    String filePath = "android/res/drawable/thepurplepersian.png";

    @Override
    public void move(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
            player.updatePosition(player.getX(), player.getY() + SPEED);
        } else if (keyCode == keyEvent.KEYCODE_DPAD_DOWN) {
            player.updatePosition(player.getX(), player.getY() - SPEED);
        } else if (keyCode == keyEvent.KEYCODE_DPAD_RIGHT) {
            player.updatePosition(player.getX() + SPEED, player.getY());
        } else if (keyCode == keyEvent.KEYCODE_DPAD_LEFT) {
            player.updatePosition(player.getX() - SPEED, player.getY());
        }
    }
}
