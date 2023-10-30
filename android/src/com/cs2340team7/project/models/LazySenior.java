package com.cs2340team7.project.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class LazySenior extends Enemy{
    public LazySenior(int pos_x, int pos_y){
        super();
        this.damage = 10;
        FileHandle fileHandle = Gdx.files.internal("thepurplepersian.png");
        this.texture = new Texture(fileHandle);
        this.size_x = 160;
        this.size_y =160;
        this.pos_x = pos_x;
        this.pos_y = pos_y;
    }
    @Override
    public void move(){
        //LazySeniors dont move
    }
}
