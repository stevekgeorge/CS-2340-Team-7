package com.cs2340team7.project.views;

import android.content.Context;
import android.content.Intent;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
import com.cs2340team7.project.models.Leaderboard;
import com.cs2340team7.project.models.Player;
import com.cs2340team7.project.viewmodels.SkilesViewModel;
import com.cs2340team7.project.viewmodels.TechGreenViewModel;

public class Skiles extends ApplicationAdapter {
    private Context context;
    private Stage stage;
    private TiledMap map;
    private OrthographicCamera camera;
    private TiledMapRenderer mapRenderer;
    private TextButton nextButton;
    private SkilesViewModel model;
    private Label score;
    private Sprite sprite;
    private SpriteBatch batch;
    private Texture texture;
    private BitmapFont font;
    private GameDataModel dataModel;
    private TechGreen.SpriteType chosenSprite;
    private float spriteX;
    private float spriteY;
    private float speed = 10.0f;
    public Skiles(Context context) {
        this.context = context;
    }

    @Override
    public void create() {
        model = new SkilesViewModel();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1000, 1500);
        camera.update();
        map = new TmxMapLoader().load("Skiles.tmx");
        stage = new Stage();

        mapRenderer = new OrthogonalTiledMapRenderer(map);

        // sending TiledMap to GameDataModel who updates Subscribers
        model.updateMap(map);

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
                chosenSprite = TechGreen.SpriteType.PERSIAN;
                break;
            case "Gabe" :
                filePath = "generalgabe.png";
                chosenSprite = TechGreen.SpriteType.GABE;
                break;
            case "Sid" :
                filePath = "swordmastersid.png";
                chosenSprite = TechGreen.SpriteType.SID;
                break;
        }
        FileHandle fileHandle = Gdx.files.internal(filePath);
        texture = new Texture(fileHandle);
        sprite = new Sprite(texture);
        spriteX = Gdx.graphics.getWidth() / 2 - texture.getWidth() / 2;
        spriteY = Gdx.graphics.getHeight() / 2 + texture.getHeight() / 2;
        model.updatePosition((int)spriteX, (int)spriteY);
        sprite.setSize(320, 320);
    }

    @Override
    public void render() {
        if (score != null) {
            score.setText(String.valueOf(model.getGameData().getCurrentScore()));
        }

        mapRenderer.setView(camera);
        mapRenderer.render();
        camera.update();
        stage.draw();

        batch.begin();
        stage.draw();

        batch.draw(sprite, spriteX, spriteY, spriteX, spriteY,sprite.getWidth(),sprite.getHeight(),sprite.getScaleX(),sprite.getScaleY(),sprite.getRotation());
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)) {
            model.move(Player.Direction.LEFT);
            spriteX = model.getX();
            spriteY = model.getY();
        }


        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            model.move(Player.Direction.RIGHT);
            spriteX = model.getX();
            spriteY = model.getY();
        }

        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)){
            model.move(Player.Direction.UP);
            spriteX = model.getX();
            spriteY = model.getY();
        }

        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN)){
            model.move(Player.Direction.DOWN);
            spriteX = model.getX();
            spriteY = model.getY();
        }

        System.out.println(spriteX);
        System.out.println(spriteY);

        // Define the destination point's coordinates
        float destinationX = 750; // Replace with your specific coordinates
        float destinationY = 200; // Replace with your specific coordinates

        // Define a margin of error of 50 pixels
        float marginOfError = 50;

        if (spriteX >= destinationX && spriteY >= destinationY) {
            // Level advancement logic here
            model.advanceLevel();

            Leaderboard board = Leaderboard.getLeaderboard();
            board.addEntry(model.getPlayerName(), model.getScore(), model.getTime());

            Intent nextLevel = new Intent(context, GameOverScreen.class);
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
