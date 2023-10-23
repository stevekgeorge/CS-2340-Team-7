package com.cs2340team7.project.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;


import android.view.KeyEvent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class SwordMasterSid implements MovementStrategy {
    private static int SPEED = 5;
    Player player;

    SwordMasterSid(Player player){
        this.player = player;
    }
    @Override
    public void move(Player.Direction direction) {
        Gdx.app.log("MOVEMENT", "Gen Gabe move was called");
        if (direction == Player.Direction.UP) {
            if (player.canMove(player.getX(), player.getY() + SPEED)) {
                player.updatePosition(player.getX(), player.getY() + SPEED);
            }
        } else if (direction == Player.Direction.DOWN) {
            if (player.canMove(player.getX(), player.getY() - SPEED)) {
                player.updatePosition(player.getX(), player.getY() - SPEED);
            }
        } else if (direction == Player.Direction.LEFT) {
            if (player.canMove(player.getX() - SPEED, player.getY())) {
                player.updatePosition(player.getX() - SPEED, player.getY() );
            }
        } else if (direction == Player.Direction.RIGHT) {
            if (player.canMove(player.getX() + SPEED, player.getY())) {
                player.updatePosition(player.getX()+ SPEED, player.getY());
            }
        }
    }
}