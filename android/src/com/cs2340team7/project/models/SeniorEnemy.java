package com.cs2340team7.project.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SeniorEnemy extends Enemy {
    private int speed;
    public SeniorEnemy(int x, int y) {
        super();
        FileHandle fileHandle = Gdx.files.internal("seniorenemy.png");
        Texture texture = new Texture(fileHandle);
        this.setSizeX(160);
        this.setSizeY(160);
        this.setSprite(new Sprite(texture));
        this.getSprite().setX(x);
        this.getSprite().setY(y);
        this.getSprite().setSize(this.getSizeX(), this.getSizeY());
        this.speed = 5;
    }
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
