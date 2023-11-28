package com.cs2340team7.project.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class RandomPowerUp extends BasePowerUp {
    private int random = 5;
    private int randomNumber;
    private int x;
    private int y;
    public RandomPowerUp(int x, int y, int randomNumber) {
        super();
        this.x = x;
        this.y = y;
        FileHandle fileHandle = Gdx.files.internal("random.png");
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
        if (randomNumber == 0) {
            getGameData().addHealth(random);
        } else {
            getGameData().addScore(random);
        }
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
