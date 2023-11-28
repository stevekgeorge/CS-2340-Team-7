package com.cs2340team7.project.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
/**
 * The SeniorEnemy class represents an enemy in the game that has a moderate level of difficulty.
 * It extends the Enemy class and includes additional properties specific to senior-level enemies.
 */
public class SeniorEnemy extends Enemy {
    private int speed;
    /**
     * Constructs a new instance of the SeniorEnemy class with the specified initial position.
     *
     * @param x The initial X-coordinate of the senior enemy.
     * @param y The initial Y-coordinate of the senior enemy.
     */
    public SeniorEnemy(int x, int y) {
        super();
        FileHandle fileHandle = Gdx.files.internal("seniorenemy.png");
        Texture texture = new Texture(fileHandle);
        FileHandle attackHandle = Gdx.files.internal("seniorenemyattacking.png");
        Texture attackTexture = new Texture(attackHandle);

        this.setSizeX(160);
        this.setSizeY(160);
        this.setEnemySprite(new Sprite(texture));
        this.setAttackSprite(new Sprite(attackTexture));
        this.getEnemySprite().setX(x);
        this.getEnemySprite().setY(y);
        this.getAttackSprite().setX(x);
        this.getAttackSprite().setY(y);
        this.getEnemySprite().setSize(this.getSizeX(), this.getSizeY());
        this.getAttackSprite().setSize(this.getSizeX(), this.getSizeY());
        this.speed = 1;
    }
    /**
     * Moves the senior enemy in the specified direction.
     * Overrides the move method in the superclass (Enemy).
     *
     * @param direction The direction in which the senior enemy should move.
     */
    @Override
    public void move(Player.Direction direction) {
        //UpdatePlayer loop for the method for the enemy movements
        if (direction == Player.Direction.UP) {
            this.updatePosition(this.getPosX() + speed, this.getPosY());
        } else if (direction == Player.Direction.DOWN) {
            this.updatePosition(this.getPosX() - speed, this.getPosX());
        } else if (direction == Player.Direction.LEFT) {
            this.updatePosition(this.getPosX(), this.getPosY() - speed);
        } else if (direction == Player.Direction.RIGHT) {
            this.updatePosition(this.getPosX(), this.getPosY() + speed);
        }
    }
}
