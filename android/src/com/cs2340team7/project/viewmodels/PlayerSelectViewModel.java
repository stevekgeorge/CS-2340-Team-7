package com.cs2340team7.project.viewmodels;

import androidx.lifecycle.ViewModel;

import com.cs2340team7.project.models.GameDataModel;
import com.cs2340team7.project.models.Player;

public class PlayerSelectViewModel extends ViewModel {
    private GameDataModel gameDataModel;

    public PlayerSelectViewModel() {
        gameDataModel = GameDataModel.getData();
    }

    public GameDataModel getGameData() {
        return gameDataModel;
    }

    public void setSelectedPlayer(String selected) {
        gameDataModel.setCharacter(selected);
    }
}
