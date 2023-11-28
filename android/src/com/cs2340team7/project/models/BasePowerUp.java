/**
 * BasePowerUp is an abstract class that implements the PowerUps interface.
 * It serves as the base class for different power-up types in the game.
 */
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
    // File handle for the power-up texture
    private FileHandle fileHandle = Gdx.files.internal("KlausWall.png");

    // Texture representing the power-up
    private Texture replacementTile = new Texture(fileHandle);

    /**
     * Applies the power-up effect to the game model.
     * Subclasses must implement this method.
     * @param model The game data model to which the power-up is applied.
     */
    @Override
    public abstract void apply(GameDataModel model);

    /**
     * Gets the texture representing the power-up.
     * @return The power-up texture.
     */
    public Texture getTexture() {
        return replacementTile;
    }

    /**
     * Disposes of resources associated with the power-up.
     * It is important to call this method when the power-up is no longer needed.
     */
    public void dispose() {
        replacementTile.dispose();
    }
}
