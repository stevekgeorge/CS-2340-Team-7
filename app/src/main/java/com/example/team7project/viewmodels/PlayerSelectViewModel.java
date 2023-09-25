package com.example.team7project.viewmodels;

import androidx.databinding.Bindable;
import androidx.lifecycle.ViewModel;

import com.example.team7project.models.PlayerSelectDataModel;

public class PlayerSelectViewModel extends ViewModel {
    public PlayerSelectDataModel PlayerSelectData;

    public PlayerSelectViewModel() {
        PlayerSelectData = new PlayerSelectDataModel();
    }

    public void SetSelectedPlayer(String Selected) {
        PlayerSelectData.SelectedPlayer = Selected;
    }
}
