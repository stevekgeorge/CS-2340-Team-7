package com.cs2340team7.project.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SeniorEnemy extends Enemy {
    private int speed;
    public SeniorEnemy(int x, int y){
        super();
        FileHandle fileHandle = Gdx.files.internal("seniorenemy.png");
        Texture texture = new Texture(fileHandle);
        this.size_x = 160;
        this.size_y =160;
        this.sprite = new Sprite(texture);
        this.sprite.setX(x);
        this.sprite.setY(y);
        this.sprite.setSize(this.size_x, this.size_y);
        this.speed = 5;
    }
    @Override
    public void move(Player.Direction direction) { //UpdatePlayer loop for the method for the enemy movements
        if (direction == Player.Direction.UP) {
            this.updatePosition(this.getPos_x() + speed, this.getPos_y());
        } else if (direction == Player.Direction.DOWN) {
            this.updatePosition(this.getPos_x() - speed, this.getPos_x());
        } else if (direction == Player.Direction.LEFT) {
            this.updatePosition(this.getPos_x(), this.getPos_y() - speed);
        } else if (direction == Player.Direction.RIGHT) {
            this.updatePosition(this.getPos_x(), this.getPos_y() + speed);
        }
    }
}