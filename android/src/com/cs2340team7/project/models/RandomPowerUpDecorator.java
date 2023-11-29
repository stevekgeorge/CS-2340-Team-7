package com.cs2340team7.project.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class RandomPowerUpDecorator extends BasePowerUpDecorator {
    private int randomNumber;
    public RandomPowerUpDecorator(int x, int y, int randomNumber) {
        super();
        FileHandle fileHandle = Gdx.files.internal("random.png");
        Texture texture = new Texture(fileHandle);
        super.setSizeX(64);
        super.setSizeY(64);
        super.setPowerUpSprite(new Sprite(texture));
        super.getPowerUpSprite().setX(x);
        super.getPowerUpSprite().setY(y);
        super.getPowerUpSprite().setSize(this.getSizeX(), this.getSizeY());
    }
    @Override
    public void updatePlayerPosition(Rectangle playerRect) {
        if (super.getPowerUpSprite().getBoundingRectangle().
                overlaps((playerRect)) && super.getPowerUpActive()) {
            if (randomNumber == 0) {
                super.model.setCurrentHealth(model.getCurrentHealth() + 25);
            } else if (randomNumber == 1) {
                super.model.setCurrentScore(model.getCurrentScore() + 15);
            } else if (randomNumber == 2) {
                super.model.setCurrentHealth(model.getCurrentHealth() + 25);
                super.model.setCurrentScore(model.getCurrentScore() + 15);
            }
            super.updatePlayerPosition(playerRect);
        }
    }
}