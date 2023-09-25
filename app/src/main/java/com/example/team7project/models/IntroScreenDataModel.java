package com.example.team7project.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class IntroScreenDataModel extends BaseObservable {
    @Bindable
    public String PlayerName;

    @Bindable
    public String Difficulty;

    public IntroScreenDataModel(String PlayerName, String Difficulty) {
        this.PlayerName = PlayerName;
        this.Difficulty = Difficulty;
    }
}
