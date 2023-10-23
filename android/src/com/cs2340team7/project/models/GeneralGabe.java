package com.cs2340team7.project.models;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class GeneralGabe implements MovementStrategy {
    private static int speed = 10;

    private Player player;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player newPlayer) {
        player = newPlayer;
    }

    @Override
    public void move() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if (player.canMove(player.getX(), player.getY() + speed)) {
                player.updatePosition(player.getX(), player.getY() + speed);
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if (player.canMove(player.getX(), player.getY() - speed)) {
                player.updatePosition(player.getX(), player.getY() - speed);
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (player.canMove(player.getX() + speed, player.getY())) {
                player.updatePosition(player.getX() + speed, player.getY());
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (player.canMove(player.getX() - speed, player.getY())) {
                player.updatePosition(player.getX() - speed, player.getY());
            }
        }
    }
}
