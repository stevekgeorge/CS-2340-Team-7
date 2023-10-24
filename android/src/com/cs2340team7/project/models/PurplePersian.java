package com.cs2340team7.project.models;
import com.badlogic.gdx.Gdx;

public class PurplePersian implements MovementStrategy {
    private static int speed = 1;

    private Player player;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player newPlayer) {
        player = newPlayer;
    }
    public int getSpeed(){ //testing only
        return speed;
    }

    PurplePersian(Player player) {
        this.player = player;
    }

    @Override
    public void move(Player.Direction direction) {
        Gdx.app.log("MOVEMENT", "Gen Gabe move was called");
        if (direction == Player.Direction.UP) {
            if (player.canMove(player.getX(), player.getY() + speed)) {
                player.updatePosition(player.getX(), player.getY() + speed);
            }
        } else if (direction == Player.Direction.DOWN) {
            if (player.canMove(player.getX(), player.getY() - speed)) {
                player.updatePosition(player.getX(), player.getY() - speed);
            }
        } else if (direction == Player.Direction.LEFT) {
            if (player.canMove(player.getX() - speed, player.getY())) {
                player.updatePosition(player.getX() - speed, player.getY());
            }
        } else if (direction == Player.Direction.RIGHT) {
            if (player.canMove(player.getX() + speed, player.getY())) {
                player.updatePosition(player.getX() + speed, player.getY());
            }
        }
    }
}