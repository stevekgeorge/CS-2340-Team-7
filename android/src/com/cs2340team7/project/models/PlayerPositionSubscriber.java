package com.cs2340team7.project.models;

import com.badlogic.gdx.math.Rectangle;

import java.security.PublicKey;

public interface PlayerPositionSubscriber {
    public void updatePlayerPosition(Rectangle player_rect);
}
