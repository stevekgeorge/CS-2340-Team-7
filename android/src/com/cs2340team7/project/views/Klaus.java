package com.cs2340team7.project.views;

import android.content.Context;
import android.content.Intent;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.cs2340team7.project.viewmodels.KlausViewModel;
import com.cs2340team7.project.viewmodels.TechGreenViewModel;

public class Klaus extends ApplicationAdapter {
    Context context;
    Stage stage;
    TiledMap map;
    OrthographicCamera camera;
    TiledMapRenderer mapRenderer;
    TextButton nextButton;
    KlausViewModel model;

    public Klaus(Context context) {
        this.context = context;
    }

    @Override
    public void create() {
        model = new KlausViewModel();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 800);
        camera.update();
        map = new TmxMapLoader().load("Klausmapp.tmx");
        stage = new Stage();

        mapRenderer = new OrthogonalTiledMapRenderer(map);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        BitmapFont font = new BitmapFont();
        font.getData().setScale(5);
        textButtonStyle.font = font;
        textButtonStyle.fontColor = Color.WHITE;

        nextButton = new TextButton("NEXT", textButtonStyle);
        nextButton.setColor(Color.RED);

        nextButton.setWidth(500);
        nextButton.setHeight(200);

        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                model.AdvanceLevel();
                Intent nextLevel = new Intent(context, GameOverScreen.class);
                context.startActivity(nextLevel);
            }
        });

        stage.addActor(nextButton);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render() {
        mapRenderer.setView(camera);
        mapRenderer.render();
        camera.update();
        stage.draw();
    }
}
