package com.cs2340team7.project.models;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

/**
 * This abstract class represents an enemy in the game. It provides common properties
 * and methods that all enemy types share.
 */
public abstract class Enemy implements PlayerPositionSubscriber {
    /**
     * Enumeration representing different types of enemies.
     */
    public static enum EnemyType { BUZZ, FRESHMEN, SENIOR, TA }
    private int sizeX;
    private int sizeY;
    private int health;
    private int damage;
    private GameDataModel model;
    private boolean damagedByCurrentEnemy = false;
    private int speed;
    private Sprite enemySprite;

    /**
     * Constructs an enemy object with default health and damage based on the game difficulty.
     * The difficulty levels are "Easy", "Medium", and "Hard".
     */
    public Enemy() {
        this.model = GameDataModel.getData();

        // Set default health based on difficulty
        if (model.getDifficulty() == "Easy") {
            this.health = 25;
        } else if (model.getDifficulty() == "Medium") {
            this.health = 50;
        } else {
            this.health = 100;
        }

        // Set default damage based on difficulty
        if (model.getDifficulty() == "Easy") {
            this.damage = 10;
        } else if (model.getDifficulty() == "Medium") {
            this.damage = 15;
        } else {
            this.damage = 25;
        }
    }
    /**
     * Abstract method to be implemented by subclasses. It defines the movement
     * behavior of the enemy.
     *
     * @param direction The direction in which the enemy should move.
     */
    public abstract void move(Player.Direction direction);

    /**
     * Updates the position of the player and performs actions based on the player's position.
     *
     * @param playerRect The rectangle representing the player's position.
     */
    public void updatePlayerPosition(Rectangle playerRect) {

        if (enemySprite.getBoundingRectangle().overlaps((playerRect)) && !damagedByCurrentEnemy) {
            model.setCurrentHealth(model.getCurrentHealth() - damage);
            damagedByCurrentEnemy = true;
            model.setCurrentScore(model.getCurrentScore() - 1);
        } else if (!enemySprite.getBoundingRectangle().overlaps((playerRect))) {
            damagedByCurrentEnemy = false;
        }

        //add more to this method if you want the enemy to do different things when
        //the player moves
        // Determine the direction based on the player's position relative to the enemy
        float playerX = playerRect.getX();
        float playerY = playerRect.getY();

        float enemyX = enemySprite.getX();
        float enemyY = enemySprite.getY();

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

    /**
     * Updates the position of the enemy.
     *
     * @param newX The new X-coordinate of the enemy.
     * @param newY The new Y-coordinate of the enemy.
     */
    public void updatePosition(int newX, int newY) {
        enemySprite.setX(newX);
        enemySprite.setY(newY);
    }
    public void setEnemySprite(Sprite enemySprite) {
        this.enemySprite = enemySprite;
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
    public Sprite getEnemySprite() {
        return enemySprite;
    }
    public int getPosX() {
        return (int) enemySprite.getX();
    }
    public int getPosY() {
        return (int) enemySprite.getY();
    }
    public int getHealth() {
        return health;
    }
    public int getDamage() {
        return damage;
    }
    public int getSpeed() {
        return speed;
    }
    public GameDataModel getGameData() {
        return model;
    }

}