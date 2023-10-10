package com.cs2340team7.project.views;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.fonts.Font;
import android.os.Bundle;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.cs2340team7.project.R;

public class TechGreen extends ApplicationAdapter {
    Stage stage;
    TiledMap map;
    OrthographicCamera camera;
    TiledMapRenderer mapRenderer;

    TextButton nextButton;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 800);
        camera.update();
        map = new TmxMapLoader().load("techgreen.tmx");
        stage = new Stage();

        mapRenderer = new OrthogonalTiledMapRenderer(map);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        BitmapFont font = new BitmapFont();
        font.getData().setScale(5);
        textButtonStyle.font = font;
        textButtonStyle.fontColor = Color.WHITE;

        nextButton = new TextButton("NEXT", textButtonStyle);

        nextButton.setWidth(500);
        nextButton.setHeight(200);

        stage.addActor(nextButton);
    }

    @Override
    public void render() {
        mapRenderer.setView(camera);
        mapRenderer.render();
        camera.update();
        stage.draw();
    }
}