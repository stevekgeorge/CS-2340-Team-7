package com.cs2340team7.project.viewmodels;

import androidx.databinding.Bindable;
import androidx.lifecycle.ViewModel;

import com.cs2340team7.project.models.PlayerSelectDataModel;

public class PlayerSelectViewModel extends ViewModel {
    public PlayerSelectDataModel PlayerSelectData;

    public PlayerSelectViewModel() {
        PlayerSelectData = new PlayerSelectDataModel();
    }

    public void SetSelectedPlayer(String Selected) {
        PlayerSelectData.SelectedPlayer = Selected;
    }
}
