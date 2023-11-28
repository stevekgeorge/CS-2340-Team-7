package com.cs2340team7.project.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

/**
 * The BasePowerUp class is an abstract implementation of the PowerUps interface
 * providing a foundation for creating specific power-up types in a game.
 * This class includes a default texture (replacementTile) and methods for applying the power-up
 * retrieving its texture, and disposing of resources when necessary.
 */

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
