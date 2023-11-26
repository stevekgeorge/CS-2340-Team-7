package com.cs2340team7.project.models;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public abstract class Enemy implements PlayerPositionSubscriber {
    public static enum EnemyType { BUZZ, FRESHMEN, SENIOR, TA }
    private int sizeX;
    private int sizeY;
    private int health;
    private int damage;
    private GameDataModel model;
    private boolean damagedByCurrentEnemy = false;
    private int speed;


    private Sprite sprite;

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public Enemy() {
        this.model = GameDataModel.getData();
        if (model.getDifficulty() == "Easy") {
            this.health = 25;
        } else if (model.getDifficulty() == "Medium") {
            this.health = 50;
        } else {
            this.health = 100;
        }
        if (model.getDifficulty() == "Easy") {
            this.damage = 10;
        } else if (model.getDifficulty() == "Medium") {
            this.damage = 15;
        } else {
            this.damage = 25;
        }
    }
    public abstract void move(Player.Direction direction);

    public void updatePlayerPosition(Rectangle playerRect) {

        if (sprite.getBoundingRectangle().overlaps((playerRect)) && !damagedByCurrentEnemy) {
            model.setCurrentHealth(model.getCurrentHealth() - damage);
            damagedByCurrentEnemy = true;
            model.setCurrentScore(model.getCurrentScore() - 1);
        } else if (!sprite.getBoundingRectangle().overlaps((playerRect))) {
            damagedByCurrentEnemy = false;
        }
        //add more to this method if you want the enemy to do different things when
        //the player moves
        // Determine the direction based on the player's position relative to the enemy
        float playerX = playerRect.getX();
        float playerY = playerRect.getY();

        float enemyX = sprite.getX();
        float enemyY = sprite.getY();

        Player.Direction direction;

        if (playerX > enemyX) {
            direction = Player.Direction.RIGHT;
        } else if (playerX < enemyX) {
            direction = Player.Direction.LEFT;
        } else if (playerY > enemyY) {
            direction = Player.Direction.UP;
        } else if (playerY < enemyY) {
            direction = Player.Direction.DOWN;
        } else {
            // Player is at the same position as the enemy, handle as needed
            direction = Player.Direction.UP; // or any default direction
        }

        move(direction);
    }
    public Sprite getSprite() {
        return sprite;
    }
    public int getPosX() {
        return (int) sprite.getX();
    }
    public int getPosY() {
        return (int) sprite.getY();
    }
    public int getHealth() {
        return health;
    }
    public int getDamage() {
        return damage;
    }

    public void updatePosition(int newX, int newY) {
        sprite.setX(newX);
        sprite.setY(newY);
    }
    public int getSpeed() {
        return speed;
    }
    public GameDataModel getGameData() {
        return model;
    }

}