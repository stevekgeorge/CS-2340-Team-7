package com.cs2340team7.project.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

/**
 * an extension of the BasePowerUp abstract class that
 * deals specifically with Health Powerups that are collected.
 */
public class HealthPowerUp extends BasePowerUp {
    private int health = 50;
    private boolean tileActive = true;
    private int x;
    private int y;
    private FileHandle fileHandle = Gdx.files.internal("medkit.png");
    private Texture texture = new Texture(fileHandle);
    public HealthPowerUp(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * override of apply method that increases the health when you collect a health powerup.
     * @param model The game data model to which the power-up is applied.
     */
    @Override
    public void apply(GameDataModel model) {
        if (!tileActive) {
            return;
        }
        model.addHealth(health);
        tileActive = false;
        this.texture = super.getTexture();
    }
    @Override
    public void dispose() {
        texture.dispose();
        super.dispose(); // Make sure to dispose of the parent class resources
    }
}
