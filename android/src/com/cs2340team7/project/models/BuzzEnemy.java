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
        this.setSizeX(160);
        this.setSizeY(160);
        this.setSprite(new Sprite(texture));
        this.getSprite().setX(x);
        this.getSprite().setY(y);
        this.getSprite().setSize(this.getSizeX(), this.getSizeY());
        this.speed = 15;
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
}
