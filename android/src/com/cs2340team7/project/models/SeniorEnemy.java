package com.cs2340team7.project.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.Map;

public class SeniorEnemy extends Enemy { //Slowest enemy movement.
    private static final Texture SENIOR_TEXTURE = new Texture("SeniorEnemy.png");
    private static final EnemyType TYPE = EnemyType.SENIOR;
    private int x, y;
    private static final int SPEED = 5;

    private TiledMap map;
    private GameDataModel gameData;
    public SeniorEnemy(int x, int y) {
        super(x, y);
    }
    public Texture getTexture() {
        return SENIOR_TEXTURE;
    }
}
