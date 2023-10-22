package com.cs2340team7.project.models;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Sprite {
    private TextureRegion textureRegion;
    private int x;
    private int y;


    public Sprite(TextureRegion textureRegion, int x, int y) {
        this.textureRegion = textureRegion;
        this.x = x;
        this.y = y;
    }
}
