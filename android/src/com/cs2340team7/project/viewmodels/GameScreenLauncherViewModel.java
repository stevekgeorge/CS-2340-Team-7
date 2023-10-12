package com.cs2340team7.project.viewmodels;

import androidx.lifecycle.ViewModel;

import com.cs2340team7.project.models.GameDataModel;

public class GameScreenLauncherViewModel extends ViewModel {
    private GameDataModel gameData;

    public GameScreenLauncherViewModel() {
        gameData = GameDataModel.getData();
    }

    public int getCurrentLevel() {
        return gameData.getCurrentLevel();
    }
}
