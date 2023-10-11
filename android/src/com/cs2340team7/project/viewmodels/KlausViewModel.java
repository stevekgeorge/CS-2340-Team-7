package com.cs2340team7.project.viewmodels;

import androidx.lifecycle.ViewModel;

import com.cs2340team7.project.models.GameDataModel;

public class KlausViewModel extends ViewModel {
    public GameDataModel GameData;
    public KlausViewModel() {
        GameData = GameDataModel.getData();
    }

    public void AdvanceLevel() {
        GameData.CurrentLevel = 3;
    }
}
