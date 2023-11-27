package com.cs2340team7.project.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

public class ScorePowerUp extends BasePowerUp {
    private FileHandle fileHandle = Gdx.files.internal("KlausCoin.png");
    private Texture texture = new Texture(fileHandle);
    @Override
    public void apply(GameDataModel model) {
        model.addScore(10);
    }
}
