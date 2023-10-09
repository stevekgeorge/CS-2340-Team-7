package com.cs2340team7.project.viewmodels;

import androidx.lifecycle.ViewModel;

import com.cs2340team7.project.models.GameDataModel;

public class GameScreenViewModel extends ViewModel {
    public GameDataModel GameData;

    public GameScreenViewModel() {
        GameData = GameDataModel.getData();
    }
}
