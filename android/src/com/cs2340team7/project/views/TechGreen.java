package com.cs2340team7.project.views;

import android.content.Context;
import android.content.Intent;

import com.badlogic.gdx.Application;
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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.cs2340team7.project.models.GameDataModel;
import com.cs2340team7.project.models.Player;
import com.cs2340team7.project.viewmodels.TechGreenViewModel;

public class TechGreen extends ApplicationAdapter {
    private Context context;
    private Stage stage;
    private TiledMap map;
    private OrthographicCamera camera;
    private TiledMapRenderer mapRenderer;
    private TextButton nextButton;
    private TechGreenViewModel model;
    private Label score;
    private TextButton up;
    private TextButton down;
    private TextButton left;
    private TextButton right;
    private Sprite sprite;
    private SpriteBatch batch;
    private Texture texture;
    private BitmapFont font;
    private GameDataModel dataModel;
    private SpriteType chosenSprite;
    private float spriteX;
    private float spriteY;
    private float speed = 10.0f;

    public enum SpriteType {
        PERSIAN,
        GABE,
        SID

    }
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

        //sending tiledMap to GameDataModel who updates the MapSubscribers
        model.updateMap(map);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        BitmapFont font = new BitmapFont();
        font.getData().setScale(5);
        textButtonStyle.font = font;
        textButtonStyle.fontColor = Color.WHITE;

        TextButton.TextButtonStyle textButtonStyleLarge = new TextButton.TextButtonStyle();
        BitmapFont fontLarge = new BitmapFont();
        fontLarge.getData().setScale(15);
        textButtonStyleLarge.font = fontLarge;
        textButtonStyleLarge.fontColor = Color.WHITE;

        Label.LabelStyle style = new Label.LabelStyle();
        style.font = font;
        style.fontColor = Color.WHITE;

        score = new Label("0", style);
        score.setX(50);
        score.setY(2100);

        up = new TextButton("↑", textButtonStyleLarge);
        left = new TextButton("←", textButtonStyleLarge);
        right = new TextButton("→", textButtonStyleLarge);
        down = new TextButton("↓", textButtonStyleLarge);

        up.setX(220);
        up.setY(400);
        left.setX(50);
        left.setY(200);
        right.setX(390);
        right.setY(200);
        down.setX(220);
        down.setY(200);

        stage.addActor(score);
        stage.addActor(up);
        stage.addActor(left);
        stage.addActor(right);
        stage.addActor(down);

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
        default:
            filePath = "swordmastersid.png";
            chosenSprite = SpriteType.SID;
            break;
        }
        FileHandle fileHandle = Gdx.files.internal(filePath);
        texture = new Texture(fileHandle);
        sprite = new Sprite(texture);
        spriteX = Gdx.graphics.getWidth() / 2 - texture.getWidth() / 2;
        spriteY = Gdx.graphics.getHeight() / 2 + texture.getHeight() / 2;
        model.updatePosition((int)spriteX, (int)spriteY);
        System.out.println("sprite height" + sprite.getHeight());
        sprite.setSize(160, 160);
        System.out.println("sprite height 2" + sprite.getHeight());

    }
    public Texture getTexture() {
        return texture;
    }
    public Sprite getSprite() {
        return sprite;
    }
    public float getSpriteX() {
        return spriteX;
    }
    public float getSpriteY() {
        return spriteY;
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
        if(Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) {
            model.move(Player.Direction.LEFT);
            spriteX = model.getX();
            spriteY = model.getY();
        }


        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            model.move(Player.Direction.RIGHT);
            spriteX = model.getX();
            spriteY = model.getY();
        }

        if(Gdx.input.isKeyPressed(Keys.DPAD_UP)){
            model.move(Player.Direction.UP);
            spriteX = model.getX();
            spriteY = model.getY();
        }

        if(Gdx.input.isKeyPressed(Keys.DPAD_DOWN)){
            model.move(Player.Direction.DOWN);
            spriteX = model.getX();
            spriteY = model.getY();
        }

        

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