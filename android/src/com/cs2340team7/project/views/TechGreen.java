package com.cs2340team7.project.views;

import android.content.Context;
import android.content.Intent;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.*;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.cs2340team7.project.models.GameDataModel;
//import com.cs2340team7.project.models.purplePersian;
import com.cs2340team7.project.models.GeneralGabe;
import com.cs2340team7.project.models.SwordMasterSid;
import com.cs2340team7.project.viewmodels.TechGreenViewModel;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Spinner;

public class TechGreen extends ApplicationAdapter {
    public enum SpriteType {
        PERSIAN,
        GABE,
        SID

    }
    private Context context;
    private Stage stage;
    private TiledMap map;
    private OrthographicCamera camera;
    private TiledMapRenderer mapRenderer;
    private TextButton nextButton;
    private TechGreenViewModel model;
    private Label score;
    private Sprite sprite;
    private SpriteBatch batch;
    private Texture texture;
    private BitmapFont font;
    private GameDataModel dataModel;
    private SpriteType chosenSprite;
    private float spriteX;
    private float spriteY;
    private float speed = 10.0f;

    public TechGreen(Context context) {
        this.context = context;
    }

    @Override
    public void create() {
        model = new TechGreenViewModel();

        font = new BitmapFont();
        font.getData().setScale(5);

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

        Label.LabelStyle style = new Label.LabelStyle();
        style.font = font;
        style.fontColor = Color.WHITE;

        score = new Label("0", style);
        score.setX(50);
        score.setY(2100);

        stage.addActor(score);

        Gdx.input.setInputProcessor(stage);

        batch = new SpriteBatch();
        String character = dataModel.getData().getCharacter();
        String filePath = null;
        switch (character) {
            case "Persian" :
                filePath = "thepurplepersian.png";
                chosenSprite = SpriteType.PERSIAN;
                break;
            case "Gabe" :
                filePath = "generalgabe.png";
                chosenSprite = SpriteType.GABE;
                break;
            case "Sid" :
                filePath = "swordmastersid.png";
                chosenSprite = SpriteType.SID;
                break;
        }
        FileHandle fileHandle = Gdx.files.internal(filePath);
        texture = new Texture(fileHandle);
        sprite = new Sprite(texture);
        spriteX = Gdx.graphics.getWidth() / 2 - texture.getWidth() / 2;
        spriteY = Gdx.graphics.getHeight() / 2 + texture.getHeight() / 2;
        System.out.println("sprite height" + sprite.getHeight());
        sprite.setSize(320, 320);
        System.out.println("sprite height 2" + sprite.getHeight());
    }

    public void setSprite(Sprite sprite, float x, float y) {
        this.sprite = sprite;
        spriteX = x;
        spriteY = y;
    }
    @Override
    public void render() {

        if (score != null) {
            score.setText(String.valueOf(model.getGameData().getCurrentScore()));
        }
        mapRenderer.setView(camera);
        mapRenderer.render();
        camera.update();
        batch.begin();
        stage.draw();

        batch.draw(sprite, spriteX, spriteY, spriteX, spriteY,sprite.getWidth(),sprite.getHeight(),sprite.getScaleX(),sprite.getScaleY(),sprite.getRotation());
        if(Gdx.input.isKeyPressed(Keys.DPAD_LEFT))
            spriteX -= Gdx.graphics.getDeltaTime() * 3000;
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            spriteX += Gdx.graphics.getDeltaTime() * 3000;
        if(Gdx.input.isKeyPressed(Keys.DPAD_UP))
            spriteY += Gdx.graphics.getDeltaTime() * 3000;
        if(Gdx.input.isKeyPressed(Keys.DPAD_DOWN))
            spriteY -= Gdx.graphics.getDeltaTime() * 3000;

        // Define the destination point's coordinates
        float destinationX = 850; // Replace with your specific coordinates
        float destinationY = 1500; // Replace with your specific coordinates

        if (spriteX >= destinationX && spriteY >= destinationY) {
            // Level advancement logic here
            model.advanceLevel();
            Intent nextLevel = new Intent(context, GameScreenLauncher.class);
            context.startActivity(nextLevel);
        }
        batch.end();
    }
    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
    }
}