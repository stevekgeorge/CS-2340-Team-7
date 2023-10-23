package com.cs2340team7.project.models;
import com.badlogic.gdx.Gdx;

public class PurplePersian implements MovementStrategy {
    private static int SPEED = 1;

    Player player;

    PurplePersian(Player player) {
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