/**
 * BuzzEnemy class represents a type of enemy characterized by high speed.
 * It extends the Enemy class and defines the behavior specific to the BuzzEnemy type.
 */
package com.cs2340team7.project.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class BuzzEnemy extends Enemy {
    // Speed of the BuzzEnemy
    private static final int DEFAULT_SPEED = 5;

    // Instance variable
    private int speed;

    /**
     * Constructor for BuzzEnemy class.
     * Initializes the BuzzEnemy with a default speed and sets up its sprite.
     * @param x The initial x-coordinate of the BuzzEnemy.
     * @param y The initial y-coordinate of the BuzzEnemy.
     */
    public BuzzEnemy(int x, int y) {
        super();

        // Load texture for the BuzzEnemy sprite
        FileHandle fileHandle = Gdx.files.internal("buzzenemy.png");
        Texture texture = new Texture(fileHandle);

        // Set up BuzzEnemy properties
        this.setSizeX(160);
        this.setSizeY(160);
        this.setEnemySprite(new Sprite(texture));
        this.getEnemySprite().setX(x);
        this.getEnemySprite().setY(y);
        this.getEnemySprite().setSize(this.getSizeX(), this.getSizeY());
        this.speed = DEFAULT_SPEED;
    }

    /**
     * Moves the BuzzEnemy in the specified direction.
     * Overrides the move method from the Enemy class to define BuzzEnemy's movement behavior.
     * @param direction The direction in which the BuzzEnemy should move.
     */
    @Override
    public void move(Player.Direction direction) {
        // Update the BuzzEnemy position based on the specified direction and speed
        if (direction == Player.Direction.UP) {
            this.updatePosition(this.getPosX() - speed, this.getPosY() + speed);
        } else if (direction == Player.Direction.DOWN) {
            this.updatePosition(this.getPosX() + speed, this.getPosX() - speed);
        } else if (direction == Player.Direction.LEFT) {
            this.updatePosition(this.getPosX() - speed, this.getPosY() + speed);
        } else if (direction == Player.Direction.RIGHT) {
            this.updatePosition(this.getPosX() + speed, this.getPosY() - speed);
        }
    }

    /**
     * Gets the speed of the BuzzEnemy.
     * @return The speed of the BuzzEnemy.
     */
    public int getSpeed() {
        return speed;
    }
}
