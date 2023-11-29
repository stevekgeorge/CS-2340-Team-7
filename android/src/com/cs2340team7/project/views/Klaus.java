package com.cs2340team7.project.views;

import android.content.Context;
import android.content.Intent;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import com.cs2340team7.project.models.BasePowerUpDecorator;
import com.cs2340team7.project.models.Enemy;
import com.cs2340team7.project.models.EnemyFactory;
import com.cs2340team7.project.models.HealthPowerUpDecorator;
import com.cs2340team7.project.models.Player;
import com.cs2340team7.project.models.RandomPowerUpDecorator;
import com.cs2340team7.project.models.ScorePowerUpDecorator;
import com.cs2340team7.project.viewmodels.KlausViewModel;

import java.util.ArrayList;
import java.util.Random;

/**
 * Klaus class that implements the second screen that the player enters.
 */

public class Klaus extends ApplicationAdapter {
    private Context context;
    private Stage stage;
    private TiledMap map;
    private OrthographicCamera camera;
    private KlausViewModel model;
    private TextButton upButton;
    private TextButton downButton;
    private TextButton leftButton;
    private TextButton rightButton;
    private TextButton attackButton;
    private Label score;
    private Label health;
    private BitmapFont font;
    private Sprite playerSprite;
    private Sprite attackSprite;
    private OrthogonalTiledMapRenderer mapRenderer;
    private Batch batch;
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private ArrayList<BasePowerUpDecorator> powerUps = new ArrayList<>();
    private Viewport fittedviewport;
    private long attackMillis;



    public Klaus(Context context) {
        this.context = context;
    }
    /**
     * create method creates creates the tilemap and adds all buttons and labels to the screen.
     * Enemies and player sprite are also generated onto the screen.
     */
    @Override
    public void create() {
        model = new KlausViewModel();

        camera = new OrthographicCamera();
        fittedviewport = new FitViewport(32 * 32, 32 * 32, camera);
        map = new TmxMapLoader().load("Klausmapp.tmx");
        stage = new Stage(fittedviewport);
        mapRenderer = new OrthogonalTiledMapRenderer(map);
        batch = new SpriteBatch();

        //sending tiledMap to GameDataModel who updates the MapSubscribers
        model.updateMap(map);

        font = new BitmapFont();
        font.getData().setScale(2);
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.fontColor = Color.WHITE;

        TextButton.TextButtonStyle textButtonStyleLarge = new TextButton.TextButtonStyle();
        BitmapFont fontLarge = new BitmapFont();
        fontLarge.getData().setScale(10);
        textButtonStyleLarge.font = fontLarge;
        textButtonStyleLarge.fontColor = Color.WHITE;

        Label.LabelStyle style = new Label.LabelStyle();
        style.font = font;
        style.fontColor = Color.WHITE;

        score = new Label("0", style);
        health = new Label("0", style);
        score.setX(40);
        score.setY(900);
        health.setX(180);
        health.setY(900);
        attackButton = new TextButton("Attack", textButtonStyle);
        upButton = new TextButton("↑", textButtonStyleLarge);
        leftButton = new TextButton("←", textButtonStyleLarge);
        rightButton = new TextButton("→", textButtonStyleLarge);
        downButton = new TextButton("↓", textButtonStyleLarge);

        attackButton.setX(600);
        attackButton.setY(900);
        upButton.setX(220);
        upButton.setY(400);
        leftButton.setX(50);
        leftButton.setY(200);
        rightButton.setX(390);
        rightButton.setY(200);
        downButton.setX(220);
        downButton.setY(200);

        stage.addActor(attackButton);
        stage.addActor(score);
        stage.addActor(health);
        stage.addActor(upButton);
        stage.addActor(leftButton);
        stage.addActor(rightButton);
        stage.addActor(downButton);

        Gdx.input.setInputProcessor(stage);


        playerSprite = model.getPlayerSprite();
        attackSprite = model.getAttackSprite();

        //using the factory
        enemies.add(EnemyFactory.generateEnemy(600, 600, Enemy.EnemyType.SENIOR));
        enemies.add(EnemyFactory.generateEnemy(400, 400, Enemy.EnemyType.FRESHMEN));

        Random random = new Random();
        int randomNumber = random.nextInt(3);

        BasePowerUpDecorator healthPowerUp = new HealthPowerUpDecorator(500, 500);
        powerUps.add(healthPowerUp);
        BasePowerUpDecorator scorePowerUp1 = new ScorePowerUpDecorator(300, 300);
        powerUps.add(scorePowerUp1);
        BasePowerUpDecorator scorePowerUp2 = new ScorePowerUpDecorator(500, 100);
        powerUps.add(scorePowerUp2);
        BasePowerUpDecorator randomPowerUp = new RandomPowerUpDecorator(100, 500, randomNumber);
        powerUps.add(randomPowerUp);
    }
    /**
     * render method that is called in each frame of the game loop. This method handles
     * the game logic that needs to be updated such as key pressed that need to be listened for
     * in every frame. The health variable is also updated based on player position and movement.
     */
    @Override
    public void render() {

        if (score != null) {
            score.setText("Score: " + String.valueOf(model.getGameData().getCurrentScore()));
        }
        if (health != null) {
            health.setText("Health: " + String.valueOf(model.getGameData().getCurrentHealth()));
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mapRenderer.setView(camera);
        mapRenderer.render();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();



        for (Enemy enemy: enemies) {
            if (System.currentTimeMillis() - enemy.getAttackMillis() < 500) {
                ((Sprite) enemy.getAttackSprite()).draw(batch);
            } else {
                ((Sprite) enemy.getEnemySprite()).draw(batch);
            }

            if (System.currentTimeMillis() - enemy.getAttackMillis()
                    >= 500 && enemy.getActiveAttack()) {
                enemy.inflictDamage();
                enemy.setActiveAttack(false);
            }
        }
        for (BasePowerUpDecorator power: powerUps) {
            ((Sprite) power.getPowerUpSprite()).draw(batch);
        }

        if (attackButton.isPressed()) {
            attackMillis = System.currentTimeMillis();
            for (Enemy enemy : enemies) {
                if (playerSprite.getBoundingRectangle().overlaps((
                        enemy.getEnemySprite().getBoundingRectangle()))) {
                    Player.getPlayer().attack(enemy);
                }

            }
        }

        if (System.currentTimeMillis() - attackMillis < 500) {
            attackSprite.draw(batch);
        } else {
            playerSprite.draw(batch);
        }


        stage.draw();
        batch.end();

        if (model.getGameData().getCurrentHealth() <= 0) {
            Intent nextLevel = new Intent(context, GameOverScreen.class);
            context.startActivity(nextLevel);
        }


        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT) || leftButton.isPressed()) {
            model.move(Player.Direction.LEFT);
        }


        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || rightButton.isPressed()) {
            model.move(Player.Direction.RIGHT);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_UP) || upButton.isPressed()) {
            model.move(Player.Direction.UP);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN) || downButton.isPressed()) {
            model.move(Player.Direction.DOWN);
        }


        if (model.exit()) {
            // Level advancement logic here
            model.advanceLevel();
            Intent nextLevel = new Intent(context, GameScreenLauncher.class);
            context.startActivity(nextLevel);
        }

    }
    /**
     * disposes of all variables that are no longer necessary when the game is closed. This is to
     * free memory and clean up resources.
     */
    @Override
    public void dispose() {
        batch.dispose();
        map.dispose();
        mapRenderer.dispose();
        stage.dispose();
    }
}
