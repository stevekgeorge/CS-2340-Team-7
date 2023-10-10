package com.cs2340team7.project.viewmodels;

import androidx.lifecycle.ViewModel;

import com.cs2340team7.project.models.GameDataModel;

public class GameScreenLauncherViewModel extends ViewModel {
    private GameDataModel GameData;

    public GameScreenLauncherViewModel() {
        GameData = GameDataModel.getData();
    }

    public int GetCurrentLevel() {
        return GameData.CurrentLevel;
    }
}
