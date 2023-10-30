package com.cs2340team7.project.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;

public class BuzzEnemy extends Enemy implements MapSubscriber, MovementStrategy {
    private static final Texture BUZZ_TEXTURE = new Texture("BuzzEnemy.png");
    private static final EnemyType TYPE = EnemyType.BUZZ;
    private TiledMap map;
    private GameDataModel gameData;
    public BuzzEnemy(int x, int y, TiledMap map) {
        super(x, y);
        this.map = map;
        gameData.addMapSubscribers(this);
    }

    @Override
    public void updateMap(TiledMap map) {

    }

    @Override
    public void move(Player.Direction direction) {

    }
}
