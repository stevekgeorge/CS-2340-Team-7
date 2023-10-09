package com.cs2340team7.project.viewmodels;

import androidx.lifecycle.ViewModel;

import com.cs2340team7.project.models.GameDataModel;

public class PlayerSelectViewModel extends ViewModel {
    public GameDataModel GameData;

    public PlayerSelectViewModel() {
        GameData = GameDataModel.getData();
    }

    public void SetSelectedPlayer(String Selected) {
        GameData.Character = Selected;
    }
}
