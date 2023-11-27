package com.cs2340team7.project.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

public class RandomPowerUp extends BasePowerUp {
    private int random = 5;
    private int randomNumber;
    private boolean tileActive = true;
    private int x;
    private int y;
    private FileHandle fileHandle = Gdx.files.internal("KlausFire.png");
    private Texture texture = new Texture(fileHandle);
    public RandomPowerUp(int x, int y, int randomNumber) {
        this.x = x;
        this.y = y;
        this.randomNumber = randomNumber;
    }
    @Override
    public void apply(GameDataModel model) {
        if (tileActive == false) {
            return;
        }
        if (randomNumber == 1) {
            model.addHealth(random);
        } else {
            model.addScore(random);
        }
        tileActive = false;
        this.texture = super.getTexture();
    }
    @Override
    public void dispose() {
        texture.dispose();
        super.dispose(); // Make sure to dispose of the parent class resources
    }
}
