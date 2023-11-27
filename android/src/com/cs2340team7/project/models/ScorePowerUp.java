package com.cs2340team7.project.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

public class ScorePowerUp extends BasePowerUp {
    private int score = 10;
    private boolean tileActive = true;
    private int x;
    private int y;
    private FileHandle fileHandle = Gdx.files.internal("coin.png");
    private Texture texture = new Texture(fileHandle);
    public ScorePowerUp(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public void apply(GameDataModel model) {
        if (tileActive == false) {
            return;
        }
        model.addScore(score);
        tileActive = false;
        this.texture = super.getTexture();
    }
    @Override
    public void dispose() {
        texture.dispose();
        super.dispose(); // Make sure to dispose of the parent class resources
    }
}
