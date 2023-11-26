package com.cs2340team7.project.models;
import com.badlogic.gdx.Gdx;

public class PurplePersian implements MovementStrategy {
    private static int speed = 8;

    private Player player;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player newPlayer) {
        player = newPlayer;
    }

    PurplePersian(Player player) {
        this.player = player;
    }

    /**
     * method that moves sprite based on button that is clicked.
     * checks to see if sprite is moving off the screen or not.
     * Updates every subscriber in the PlayerPositionSubscriber arraylist of the sprites new location.
     * Implementation of the movementStrategy interface is part of the strategy design pattern
     * and updating subscribers is part of the observer design pattern.
     * @param direction
     */

    @Override
    public void move(Player.Direction direction) {
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