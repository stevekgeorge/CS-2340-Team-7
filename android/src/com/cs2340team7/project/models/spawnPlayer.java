package com.cs2340team7.project.models;
import android.view.KeyEvent;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.InputProcessor;

// code from https://stackoverflow.com/questions/5833065/how-to-move-a-sprite-with-the-keyboard-keys-using-libgdx
public class Game implements ApplicationListener {
    private SpriteBatch batch;
    private Texture texture;
    private Sprite character;
    private int characterX;
    private int characterY;

    @Override
    public void create() {
        batch = new SpriteBatch();
        FileHandle fileHandle = Gdx.files.internal("sprite.png");
        texture = new Texture(fileHandle);
        character = new Sprite(texture, 0, 158, 32, 64);
        characterX = 0;
        characterY = 0;
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(character, characterX, characterY);
        batch.end();
    }