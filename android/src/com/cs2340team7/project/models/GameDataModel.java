package com.cs2340team7.project.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class GameDataModel extends BaseObservable {
    @Bindable
    public String PlayerName;
    @Bindable
    public String Difficulty;
    @Bindable
    public int MaxHealth;
    @Bindable
    public int CurrentHealth;
    @Bindable
    public String Character;
    @Bindable
    public int CurrentLevel;
    @Bindable
    public int CurrentScore;

    private static GameDataModel data;
    private GameDataModel() {
        Clear();
    }

    public static GameDataModel getData() {
        if (data == null) {
            data = new GameDataModel();
        }
        return data;
    }

    public void Clear() {
        PlayerName = "";
        Difficulty = "";
        MaxHealth = 0;
        CurrentHealth = 0;
        Character = "";
        CurrentLevel = 1;
        CurrentScore = 0;
    }
}
