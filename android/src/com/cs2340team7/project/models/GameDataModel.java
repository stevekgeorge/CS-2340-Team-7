package com.cs2340team7.project.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.cs2340team7.project.BR;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

/**
 * main DataModel class that contains necessary data for the player
 */
public class GameDataModel extends BaseObservable {
    private String playerName;
    private String difficulty;
    private int maxHealth;
    private int currentHealth;
    private String character;
    private int currentLevel;
    private int currentScore;
    private String startTime;

    private TiledMap currentMap;

    private ArrayList<MapSubscriber> mapSubscribers = new ArrayList<MapSubscriber>();


    private static GameDataModel data;
    private Enemy enemy1;
    private Enemy enemy2;
    private EnemyFactory enemyFactory;
    private int level;
    private boolean invincibility = false;
    private GameDataModel() {
        clear();
    }

    public static GameDataModel getData() {
        if (data == null) {
            data = new GameDataModel();

        }
        return data;
    }

    @Bindable
    public String getPlayerName() {
        return playerName;
    }

    @Bindable
    public int getMaxHealth() {
        return maxHealth;
    }
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
    public void addHealth(int health) {
        currentHealth += getCurrentHealth() + health;
    }

    @Bindable
    public int getCurrentScore() {
        return currentScore;
    }
    @Bindable
    public String getDifficulty() {
        return difficulty;
    }
    public void addScore(int score) {
        currentScore += getCurrentScore() + score;
    }


    @Bindable
    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int health) {
        if (invincibility == true) {
            return;
        }
        this.currentHealth = health;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @Bindable
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public TiledMap getCurrentMap() {
        return currentMap;
    }

    public ArrayList<MapSubscriber> getMapSubscribers() {
        return mapSubscribers;
    }

    @Bindable
    public int getCurrentLevel() {
        return currentLevel;
    }

    @Bindable
    public String getCharacter() {
        return character;
    }

    @Bindable
    public boolean getInvincibility() {
        return invincibility;
    }
    public void setInvincibility(boolean invincibility) {
        if (this.invincibility == true) {
            this.invincibility = invincibility;
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    // This code will be executed after 5 seconds
                    setInvincibility(false);
                }
            }, 5000);
        } else {
            this.invincibility = false;
        }
    }

    /**
     * update method for map that notifies map subscribers for the observer design pattern
     * @param map TiledMap instance that the currentMap instance will update to
     */
    public void updateMap(TiledMap map) {
        this.currentMap = map;
        notifyMapSubscribers();
        Gdx.app.log("MOVEMENT", "update map called");

    }

    /**
     * method that adds a subscriber
     * @param subscriber the new subscriber that is being added to mapSubscribers arraylist
     */
    public void addMapSubscribers(MapSubscriber subscriber) {
        this.mapSubscribers.add(subscriber);
    }

    /**
     * method that notifies every subscriber in the arraylist of the changes that occur in the maps
     * for the observer pattern
     */
    private void notifyMapSubscribers() {
        for (MapSubscriber subscriber: mapSubscribers) {
            subscriber.updateMap(this.currentMap);
        }
    }

    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * clears the game state and returns all variables to their default values
     */

    public void clear() {
        playerName = "";
        difficulty = "";
        maxHealth = 0;
        currentHealth = 0;
        character = "";
        currentLevel = 1;
        currentScore = 0;
    }

}
