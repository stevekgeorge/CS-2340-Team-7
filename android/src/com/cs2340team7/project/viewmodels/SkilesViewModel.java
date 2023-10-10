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

    public void SetScore(int Score) {
        GameData.CurrentScore = Score;
    }

    public String GetPlayerName() {
        return GameData.PlayerName;
    }

    public int GetScore() {
        return GameData.CurrentScore;
    }
}
