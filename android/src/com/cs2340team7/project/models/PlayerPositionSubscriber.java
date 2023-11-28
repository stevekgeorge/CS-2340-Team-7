package com.cs2340team7.project.models;

import com.badlogic.gdx.math.Rectangle;

/**
 * interface for implementing observer pattern for Player Position updates.
 * The updatePlayerPosition method is implemented in Enemy and Player class.
 */
public interface PlayerPositionSubscriber {
    void updatePlayerPosition(Rectangle playerRect);
}
