package com.cs2340team7.project.models;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Game;
import com.cs2340team7.project.views.Klaus;
import com.cs2340team7.project.views.Skiles;
import com.cs2340team7.project.views.TechGreen;

public class purplePersian implements movementStrategy {
    private static int SPEED = 1;

    Player player;

    purplePersian(Player player) {
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

