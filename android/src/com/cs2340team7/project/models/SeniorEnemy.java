package com.cs2340team7.project.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SeniorEnemy extends Enemy{
    public SeniorEnemy(int pos_x, int pos_y){
        super();
        FileHandle fileHandle = Gdx.files.internal("generalgabe.png");
        Texture texture = new Texture(fileHandle);
        this.size_x = 160;
        this.size_y =160;
        this.sprite = new Sprite(texture);
        this.sprite.setX(pos_x);
        this.sprite.setY(pos_y);
        this.sprite.setSize(this.size_x, this.size_y);
    }
    @Override
    public void move(){
        //LazySeniors dont move
    }
}
