package com.cs2340team7.project.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class BuzzEnemy extends Enemy { //Fastest Enemy type.
    private int speed;
    public BuzzEnemy(int x, int y) {
        super();
        FileHandle fileHandle = Gdx.files.internal("buzzenemy.png");
        Texture texture = new Texture(fileHandle);

        FileHandle attackHandle = Gdx.files.internal("buzzenemyattacking.png");
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
        this.speed = 5;
    }
    @Override
    public void move(Player.Direction direction) {
        //UpdatePlayer loop for the method for the enemy movements
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

    public int getSpeed() {
        return speed;
    }
}
