package com.cs2340team7.project.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class TAEnemy extends Enemy { //TA enemies do not move.
    private static final Texture TA_TEXTURE = new Texture("TAEnemy.png");
    private static final EnemyType TYPE = EnemyType.TA;
    private int x, y;
    private static final int SPEED = 0;

    private TiledMap map;
    private GameDataModel gameData;
    public TAEnemy(int x, int y) {
        super(x, y);
    }
    public Texture getTexture() {
        return TA_TEXTURE;
    }
}
