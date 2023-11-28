package com.cs2340team7.project.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public abstract class BasePowerUpDecorator implements PlayerPositionSubscriber {
    private int powerUpValue;
    protected GameDataModel model;
    private int sizeX;
    private int sizeY;
    private boolean powerUpActive = true;
    private Sprite powerUpSprite;

    public BasePowerUpDecorator() {
        Player.getPlayer().addPlayerPositionSubscribers(this);
        this.model = GameDataModel.getData();
    }
    public int getPowerUpValue() {
        return powerUpValue;
    }
    @Override
    public void updatePlayerPosition(Rectangle playerRect) {
        if (powerUpSprite.getBoundingRectangle().overlaps((playerRect)) && powerUpActive) {
            powerUpActive = false;
            FileHandle fileHandle = Gdx.files.internal("replacement.png");
            Texture texture = new Texture(fileHandle);
            this.setSizeX(0);
            this.setSizeY(0);
            this.setPowerUpSprite(new Sprite(texture));
            this.getPowerUpSprite().setX(getPosX());
            this.getPowerUpSprite().setY(getPosY());
            this.getPowerUpSprite().setSize(this.getSizeX(), this.getSizeY());
        }
    }
    public boolean getPowerUpActive() {
        return powerUpActive;
    }
    public void setPowerUpSprite(Sprite powerUpSprite) {
        this.powerUpSprite = powerUpSprite;
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
    public Sprite getPowerUpSprite() {
        return powerUpSprite;
    }
    public int getPosX() {
        return (int) powerUpSprite.getX();
    }
    public int getPosY() {
        return (int) powerUpSprite.getY();
    }
    public GameDataModel getGameData() {
        return model;
    }
}
