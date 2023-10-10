package com.cs2340team7.project.viewmodels;

import androidx.lifecycle.ViewModel;

import com.cs2340team7.project.models.GameDataModel;

public class SkilesViewModel extends ViewModel {
    GameDataModel GameData;
    public SkilesViewModel() {
        GameData = GameDataModel.getData();
    }

    public void AdvanceLevel() {
        GameData.CurrentLevel = -1;
    }
}
