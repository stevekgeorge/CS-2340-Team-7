package com.cs2340team7.project.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

public abstract class BasePowerUp implements PowerUps {
    private FileHandle fileHandle = Gdx.files.internal("KlausWall.png");
    private Texture replacementTile = new Texture(fileHandle);
    @Override
    public abstract void apply(GameDataModel model);
    public Texture getTexture() {
        return replacementTile;
    }
    public void dispose() {
        replacementTile.dispose();
    }
}
