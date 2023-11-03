package com.cs2340team7.project.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class FreshmenEnemy extends Enemy implements MapSubscriber { //Second fastest enemy type.
    private static final Texture FRESHMEN_TEXTURE = new Texture("FreshmenEnemy.png");
    private static final EnemyType TYPE = EnemyType.FRESHMEN;
    private int x, y;
    private static final int SPEED = 10;

    private TiledMap map;
    private GameDataModel gameData;
    public FreshmenEnemy(int x, int y) {
        super(x, y);
        this.map = map;
    }

    @Override
    public void updateMap(TiledMap map) {
        this.map = map;
    }
}
