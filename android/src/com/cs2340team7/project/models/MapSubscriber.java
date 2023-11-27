package com.cs2340team7.project.models;

import com.badlogic.gdx.maps.tiled.TiledMap;

/**
 * interface MapSubscriber for implementing Obeserver design pattern.
 * UpdateMap method is implemented in GameDataModel and Player class.
 */
public interface MapSubscriber {
    public void updateMap(TiledMap map);
}
