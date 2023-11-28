package com.cs2340team7.project.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class HealthPowerUp extends BasePowerUp {
    private int health = 50;
    private boolean tileActive = true;
    private int x;
    private int y;

    public HealthPowerUp(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        FileHandle fileHandle = Gdx.files.internal("medkit.png");
        this.setSizeX(32);
        this.setSizeY(32);
        this.setPowerUp(new Sprite(new Texture(fileHandle)));
        this.getPowerUp().setX(x);
        this.getPowerUp().setY(y);
        this.getPowerUp().setSize(this.getSizeX(), this.getSizeY());
    }

    @Override
    public void apply() {
        if (getTileActive() == false) {
            return;
        }
        getGameData().addHealth(health);
        this.setPowerUp(new Sprite(super.getReplacementTile()));
    }

    @Override
    public void updatePlayerPosition(Rectangle playerRect) {
        if (getPowerUp().getBoundingRectangle().overlaps(playerRect)) {
            this.apply();
            setTileActive(false);
        }
    }
}
