package com.cs2340team7.project.views;

import android.content.Context;
import android.content.Intent;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.cs2340team7.project.models.Enemy;
import com.cs2340team7.project.models.EnemyFactory;
import com.cs2340team7.project.models.EnemyType;
import com.cs2340team7.project.models.GameDataModel;
import com.cs2340team7.project.models.Player;
import com.cs2340team7.project.models.PlayerSprite;
import com.cs2340team7.project.viewmodels.TechGreenViewModel;

import java.util.ArrayList;

public class TechGreen extends ApplicationAdapter {
    private Context context;
    private Stage stage;
    private TiledMap map;
    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer mapRenderer;
    private TextButton nextButton;
    private TechGreenViewModel model;
    private Label score;
    private TextButton up;
    private TextButton down;
    private TextButton left;
    private TextButton right;
    private Sprite sprite;
    private Batch batch;
    private Texture texture;
    private BitmapFont font;
    private GameDataModel dataModel;
    private SpriteType chosenSprite;
    private float spriteX;
    private float spriteY;
    private float speed = 10.0f;
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    private EnemyFactory enemyFactory = new EnemyFactory();
    private Enemy enemy1 = enemyFactory.generateEnemy(600, 600, EnemyType.BUZZ);
    private Enemy enemy2 = enemyFactory.generateEnemy(700, 700, EnemyType.FRESHMEN);
    public enum SpriteType {
        PERSIAN,
        GABE,
        SID

    }
    private PlayerSprite playerSprite;
    public TechGreen(Context context) {
        this.context = context;
    }

    public TextButton getDown() { //for testing purposes
        return down;
    }

    @Override
    public void create() {
        model = new TechGreenViewModel();



        font = new BitmapFont();
        font.getData().setScale(5);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1024, 1024);
        camera.update();
        map = new TmxMapLoader().load("techgreen.tmx");
        stage = new Stage();

        //making the batch the map renders batch
        mapRenderer = new OrthogonalTiledMapRenderer(map);
        mapRenderer.setView(camera);
        batch = mapRenderer.getBatch();

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
        sprite.setSize(160, 160);
        model.setPlayerSprite(sprite);
        playerSprite = model.getPlayerSprite();

        enemies.add(enemy1);
        enemies.add(enemy2);
    }
    @Override
    public void render() {

        if (score != null) {
            score.setText(String.valueOf(model.getGameData().getCurrentScore()));
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mapRenderer.setView(camera);
        mapRenderer.render();
        camera.update();

        batch.begin();
        playerSprite.draw(batch);
        for (Enemy enemy: enemies){
           batch.draw(enemy.getTexture(),600, ((float) (Gdx.graphics.getHeight() / Gdx.graphics.getWidth()))*600, 160, ((float) (Gdx.graphics.getHeight() / Gdx.graphics.getWidth()))*160);
        }

        stage.draw();
        batch.end();


        if (Gdx.input.isKeyPressed(Keys.DPAD_LEFT) || right.isPressed()) {
            model.move(Player.Direction.LEFT);
        }


        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || left.isPressed()) {
            model.move(Player.Direction.RIGHT);
        }

        if (Gdx.input.isKeyPressed(Keys.DPAD_UP) || up.isPressed()) {
            model.move(Player.Direction.UP);
        }

        if (Gdx.input.isKeyPressed(Keys.DPAD_DOWN) || down.isPressed()) {
            model.move(Player.Direction.DOWN);
        }


        if (model.exit()) {
            // Level advancement logic here
            model.advanceLevel();
            Intent nextLevel = new Intent(context, GameScreenLauncher.class);
            context.startActivity(nextLevel);
        }

    }
    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
        map.dispose();
        mapRenderer.dispose();

    }
}