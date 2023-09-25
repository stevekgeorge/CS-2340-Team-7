package com.example.team7project.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class GameScreenDataModel extends BaseObservable {
    @Bindable
    public String PlayerName;

    @Bindable
    public String Difficulty;

    @Bindable
    public String StartingHealth;

    @Bindable
    public int Sid;

    @Bindable
    public int Persian;

    @Bindable
    public int Gabe;

    public GameScreenDataModel(String PlayerName, String Difficulty, String Health, int Sid, int Persian, int Gabe) {
        this.PlayerName = PlayerName;
        this.Difficulty = Difficulty;
        this.StartingHealth = Health;
        this.Sid = Sid;
        this.Persian = Persian;
        this.Gabe = Gabe;
    }
}
