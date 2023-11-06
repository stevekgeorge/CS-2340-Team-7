package com.cs2340team7.project.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class FreshmenEnemy extends Enemy { //Second fastest enemy type.
    private static final Texture FRESHMEN_TEXTURE = new Texture("FreshmenEnemy.png");
    private static final EnemyType TYPE = EnemyType.FRESHMEN;
    private int x, y;
    private static final int SPEED = 10;

    private TiledMap map;
    private GameDataModel gameData;
    public FreshmenEnemy(int x, int y) {
        super();
        FileHandle fileHandle = Gdx.files.internal("generalgabe.png");
        Texture texture = new Texture(fileHandle);
        this.size_x = 160;
        this.size_y =160;
        this.sprite = new Sprite(texture);
        this.sprite.setX(x);
        this.sprite.setY(y);
        this.sprite.setSize(this.size_x, this.size_y);
    }

    public Texture getTexture() {
        return FRESHMEN_TEXTURE;
    }
    public void move(){

    }
}
