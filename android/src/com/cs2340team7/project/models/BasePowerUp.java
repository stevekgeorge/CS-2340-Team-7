package com.cs2340team7.project.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

/**
 * This abstract class represents an enemy in the game. It provides common properties
 * and methods that all enemy types share.
 */
public abstract class BasePowerUp implements PlayerPositionSubscriber, PowerUps {
    private int sizeX;
    private int sizeY;
    private GameDataModel model;
    private Sprite powerUp;
    private FileHandle fileHandle = Gdx.files.internal("replacement.png");
    private Texture replacement = new Texture(fileHandle);
    private boolean tileActive = true;

    public BasePowerUp() {
        this.model = GameDataModel.getData();
    }
    @Override
    public abstract void apply();
    @Override
    public abstract void updatePlayerPosition(Rectangle playerRect);


    public void setPowerUp(Sprite powerUp) {
        this.powerUp = powerUp;
    }
    public boolean getTileActive() {
        return tileActive;
    }
    public void setTileActive(boolean value) {
        this.tileActive = value;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }
    public Sprite getPowerUp() {
        return powerUp;
    }
    public Texture getReplacementTile() {
        return replacement;
    }

    public GameDataModel getGameData() {
        return model;
    }
}
