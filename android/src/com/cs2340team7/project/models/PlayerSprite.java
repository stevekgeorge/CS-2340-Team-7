package com.cs2340team7.project.models;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayerSprite extends Sprite {
    private Player player;
    public PlayerSprite(Sprite sprite, Player player){
        super(sprite);
        this.player = player;
    }

    @Override
    public void draw(Batch spriteBatch){
        super.draw(spriteBatch);
    }
}
