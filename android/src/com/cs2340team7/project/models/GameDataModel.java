package com.cs2340team7.project.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;


public class GameDataModel extends BaseObservable {
    private String playerName;
    private String difficulty;
    private int maxHealth;
    private int currentHealth;
    private String character;
    private int currentLevel;
    private int currentScore;
    private String startTime;

    private static GameDataModel data;
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

    @Bindable
    public int getCurrentLevel() {
        return currentLevel;
    }

    @Bindable
    public String getCharacter() {
        return character;
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
