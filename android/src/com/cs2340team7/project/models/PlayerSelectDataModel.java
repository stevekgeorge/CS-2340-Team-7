package com.cs2340team7.project.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class PlayerSelectDataModel extends BaseObservable {
    @Bindable
    public String SelectedPlayer;

    public PlayerSelectDataModel() {
        this("");
    }

    public PlayerSelectDataModel(String SelectedPlayer) {
        this.SelectedPlayer = SelectedPlayer;
    }
}
