package com.cs2340team7.project.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class BuzzEnemy extends Enemy implements MapSubscriber { //Fastest Enemy type.
    private static final Texture BUZZ_TEXTURE = new Texture("BuzzEnemy.png");
    private static final EnemyType TYPE = EnemyType.BUZZ;
    private static final int SPEED = 15;

    private int x, y;
    private TiledMap map;
    private GameDataModel gameData;
    public BuzzEnemy(int x, int y) {
        super(x, y);
    }
    @Override
    public void updateMap(TiledMap map) {
        this.map = map;
    }
}
