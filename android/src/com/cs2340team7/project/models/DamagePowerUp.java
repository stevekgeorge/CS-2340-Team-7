package com.cs2340team7.project.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

public class DamagePowerUp extends BasePowerUp {
    private FileHandle fileHandle = Gdx.files.internal("KlausFire.png");
    private Texture texture = new Texture(fileHandle);
    @Override
    public void apply(GameDataModel model) {
        model.addHealth(-10);
    }
}
