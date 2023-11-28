package com.cs2340team7.project.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class ScorePowerUpDecorator extends BasePowerUpDecorator {
    private int scoreBonus = 15;
    public ScorePowerUpDecorator(int x, int y) {
        super();
        FileHandle fileHandle = Gdx.files.internal("coin.png");
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
        if (super.getPowerUpSprite().getBoundingRectangle().overlaps((playerRect)) && super.getPowerUpActive()) {
            super.model.setCurrentScore(model.getCurrentScore() + scoreBonus);
            super.updatePlayerPosition(playerRect);
        }
    }
    public int getScoreBonus() {
        return scoreBonus;
    }
}