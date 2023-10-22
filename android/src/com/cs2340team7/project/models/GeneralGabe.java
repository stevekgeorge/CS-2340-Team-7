package com.cs2340team7.project.models;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class GeneralGabe implements movementStrategy {
    private static int SPEED = 10;

    Player player;

    @Override
    public void move() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if (player.canMove(player.getX(), player.getY() + SPEED)) {
                player.updatePosition(player.getX(), player.getY() + SPEED);
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if (player.canMove(player.getX(), player.getY() - SPEED)) {
                player.updatePosition(player.getX(), player.getY() - SPEED);
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (player.canMove(player.getX() + SPEED, player.getY())) {
                player.updatePosition(player.getX() + SPEED, player.getY() );
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (player.canMove(player.getX() - SPEED, player.getY())) {
                player.updatePosition(player.getX()- SPEED, player.getY());
            }
        }
    }
}
