package com.cs2340team7.project.models;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class GeneralGabe implements movementStrategy {
    private static int SPEED = 10;

    Player player;

    @Override
    public void move() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player.updatePosition(player.getX(), player.getY() + SPEED);
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.updatePosition(player.getX(), player.getY() - SPEED);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.updatePosition(player.getX() + SPEED, player.getY());
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.updatePosition(player.getX() - SPEED, player.getY());
        }
    }
}
