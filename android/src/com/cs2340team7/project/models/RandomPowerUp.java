/**
 * RandomPowerUp class represents a specific type of
 * power-up that adds either health or score randomly.
 * It extends the BasePowerUp class and implements
 * the apply method to define the power-up's behavior.
 */
package com.cs2340team7.project.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

public class RandomPowerUp extends BasePowerUp {
    // Constants for power-up behavior
    private static final int DEFAULT_RANDOM = 5;

    // Instance variables
    private int random = DEFAULT_RANDOM;
    private int randomNumber;
    private boolean tileActive = true;
    private int x;
    private int y;
    private FileHandle fileHandle = Gdx.files.internal("random.png");
    private Texture texture = new Texture(fileHandle);

    /**
     * Constructor for RandomPowerUp class.
     * @param x The x-coordinate of the power-up in the game grid.
     * @param y The y-coordinate of the power-up in the game grid.
     * @param randomNumber The random number determining whether to add health or score.
     */
    public RandomPowerUp(int x, int y, int randomNumber) {
        this.x = x;
        this.y = y;
        this.randomNumber = randomNumber;
    }

    /**
     * Applies the random power-up effect to the game model.
     * If the tile is already used, the effect is not applied.
     * @param model The game data model to which the power-up is applied.
     */
    @Override
    public void apply(GameDataModel model) {
        // Check if the power-up tile is already used
        if (!tileActive) {
            return;
        }

        // Apply the random power-up effect based on the random number
        if (randomNumber == 1) {
            model.addHealth(random);
        } else {
            model.addScore(random);
        }

        // Mark the power-up tile as used
        tileActive = false;

        // Set the texture to the default power-up texture after use
        this.texture = super.getTexture();
    }

    /**
     * Disposes of resources associated with the random power-up.
     * It is important to call this method when the power-up is no longer needed.
     */
    @Override
    public void dispose() {
        texture.dispose();
        super.dispose(); // Make sure to dispose of the parent class resources
    }
}
