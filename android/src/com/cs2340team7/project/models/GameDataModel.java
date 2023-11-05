package com.cs2340team7.project.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;

import java.util.ArrayList;


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
    private Enemy enemy;
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

    @Bindable
    public int getCurrentScore() {
        return currentScore;
    }

    @Bindable
    public String getDifficulty() {
        return difficulty;
    }

    @Bindable
    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int health) {
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

    public void updateMap(TiledMap map) {
        this.currentMap = map;
        notifyMapSubscribers();
        Gdx.app.log("MOVEMENT", "update map called");

    }
    public void addMapSubscribers(MapSubscriber subscriber) {
        this.mapSubscribers.add(subscriber);
    }



    private void notifyMapSubscribers() {
        for (MapSubscriber subscriber: mapSubscribers) {
            subscriber.updateMap(this.currentMap);
        }
    }

    public Enemy getEnemy() {
        return enemy;
    }


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
