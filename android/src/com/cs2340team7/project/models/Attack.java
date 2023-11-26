package com.cs2340team7.project.models;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Attack {
    private Sprite sprite;
    public void executeAttack(Rectangle playerRect) {
        if (sprite.getBoundingRectangle().overlaps((playerRect))) {

        }
    }
}
