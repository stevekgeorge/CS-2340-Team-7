package com.cs2340team7.project.viewmodels;

import androidx.lifecycle.ViewModel;

import com.cs2340team7.project.models.GameScreenDataModel;

public class GameScreenViewModel extends ViewModel {
    public GameScreenDataModel GameScreenData;

    public GameScreenViewModel() {
        GameScreenData = new GameScreenDataModel("", "", "", 0, 0, 0);
    }

    public void SetData(GameScreenDataModel NewData) {
        GameScreenData = NewData;
    }
}
