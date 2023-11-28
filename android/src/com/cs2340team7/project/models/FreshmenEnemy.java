package com.cs2340team7.project.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
/**
 * The FreshmenEnemy class represents an enemy in the game that is the second fastest among enemy types.
 * It extends the Enemy class and includes additional properties specific to freshmen-level enemies.
 */
public class FreshmenEnemy extends Enemy { //Second fastest enemy type.
    private int speed;
    /**
     * Constructs a new instance of the FreshmenEnemy class with the specified initial position.
     *
     * @param x The initial X-coordinate of the freshmen enemy.
     * @param y The initial Y-coordinate of the freshmen enemy.
     */
    public FreshmenEnemy(int x, int y) {
        super();
        FileHandle fileHandle = Gdx.files.internal("freshmenenemy.png");
        Texture texture = new Texture(fileHandle);
        this.setSizeX(160);
        this.setSizeY(160);
        this.setEnemySprite(new Sprite(texture));
        this.getEnemySprite().setX(x);
        this.getEnemySprite().setY(y);
        this.getEnemySprite().setSize(this.getSizeX(), this.getSizeY());
        this.speed = 3;
    }
    /**
     * Moves the freshmen enemy in the specified direction.
     * Overrides the move method in the superclass (Enemy).
     *
     * @param direction The direction in which the freshmen enemy should move.
     */
    @Override
    public void move(Player.Direction direction) {
        //UpdatePlayer loop for the method for the enemy movements
        if (direction == Player.Direction.UP) {
            this.updatePosition(this.getPosX(), this.getPosY() + speed);
        } else if (direction == Player.Direction.DOWN) {
            this.updatePosition(this.getPosX(), this.getPosX() - speed);
        } else if (direction == Player.Direction.LEFT) {
            this.updatePosition(this.getPosX() - speed, this.getPosY());
        } else if (direction == Player.Direction.RIGHT) {
            this.updatePosition(this.getPosX() + speed, this.getPosY());
        }
    }
}
