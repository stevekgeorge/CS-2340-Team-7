package com.cs2340team7.project.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class TAEnemy extends Enemy { //TA enemies do not move.
    private int speed;
    public TAEnemy(int x, int y) {
        super();
        FileHandle fileHandle = Gdx.files.internal("taenemy.png"); //The files are causing the problem, can't find them!!!
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
    public void move(Player.Direction direction) {
        //TAs do not move, you come to the TAs...
    }
}
