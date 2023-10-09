package com.cs2340team7.project.viewmodels;

import androidx.lifecycle.ViewModel;

import com.cs2340team7.project.models.GameDataModel;

import java.util.HashMap;

public class GameOverViewModel extends ViewModel {
    public GameDataModel GameData;

    public GameOverViewModel() {
        GameData = GameDataModel.getData();
    }
}
