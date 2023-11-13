package com.cs2340team7.project.models;

public class GeneralGabe implements MovementStrategy {
    private static int speed = 10;

    private Player player;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player newPlayer) {
        player = newPlayer;
    }

    GeneralGabe(Player player) {
        this.player = player;
    }

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
