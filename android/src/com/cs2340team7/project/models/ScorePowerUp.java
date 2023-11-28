package com.cs2340team7.project.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

/**
 * The ScorePowerUp class extends the BasePowerUp class,
 * representing a specific type of power-up in the game.
 * This power-up adds a predefined score to the game
 * when applied to the GameDataModel.
 * The class includes properties such as the score value,
 * activation status, position coordinates, and a custom texture.
 * It overrides the apply method to define the specific
 * behavior of the score power-up and provides a
 * constructor for setting its initial position.
 */
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

    /**
     * if a specific tile is a powerup tile, score is
     * increased and is no longer counted as a powerup tile.
     * @param model  a GameDataModel instance
     */
    @Override
    public void apply(GameDataModel model) {
        if (!tileActive) {
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
