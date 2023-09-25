package com.example.team7project.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.team7project.models.GameScreenDataModel;

public class GameScreenViewModel extends ViewModel {
    public GameScreenDataModel GameScreenData;

    public GameScreenViewModel() {
        GameScreenData = new GameScreenDataModel("", "", "", 0, 0, 0);
    }

    public void SetData(GameScreenDataModel NewData) {
        GameScreenData = NewData;
    }
}
