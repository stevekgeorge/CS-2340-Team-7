package com.cs2340team7.project.models;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Attack {
    private Sprite sprite;
    public void executeAttack(Rectangle playerRect) {
        // Each attack kills the enemy in one strike
        if (sprite.getBoundingRectangle().overlaps((playerRect))) {

        }
    }
}
