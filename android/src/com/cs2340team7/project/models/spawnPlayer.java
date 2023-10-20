package com.cs2340team7.project.models;
import android.view.KeyEvent;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.InputProcessor;

// code from https://stackoverflow.com/questions/5833065/how-to-move-a-sprite-with-the-keyboard-keys-using-libgdx
public class spawnPlayer extends Player implements ApplicationListener {
    private GameDataModel dataModel;
    private Texture texture;
    private int x;
    private int y;
    private SpriteBatch batch;
    private Sprite character;
    private OrthographicCamera camera;
    String filePath;

    @Override
    public void create() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        String name = dataModel.getCharacter();
        switch(name) {
            case "Persian":
                filePath = "drawable/thepurplepersian.png";
                break;
            case "Gabe":
                filePath = "drawable/generalgabe.png";
                break;
            case "Sid":
                filePath = "drawable/swordmastersid.png";
                break;
        }
        character = new Sprite(new Texture(filePath));
        character.setSize(120, 30);
        character.setPosition(0, 0);

        //FileHandle fileHandle = Gdx.files.internal(filePath);
        //texture = new Texture(fileHandle);
        //character = new Sprite(texture, 0, 0, 32, 64);
    }

    @Override
    public void render() {
//        int level = dataModel.getCurrentLevel();
//        switch(level) {
//            case 1: //"tech green"
//                character.setPosition(-400, 0);
//                x = 0;
//                y = 0;
//                break;
//            case 2: //"klaus"
//                character.setPosition(50, 50);
//                x = 50;
//                y = 50;
//                break;
//            case 3://"skiles"
//                character.setPosition(100, 100);
//                x = 100;
//                y = 100;
//                break;
//        }
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        character.draw(batch);
        batch.end();
    }

    @Override
    public void resume() {
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void dispose() {
    }
}