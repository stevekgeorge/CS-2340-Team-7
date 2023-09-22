package com.example.team7project.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.ViewModel;

import com.example.team7project.models.GameOverDataModel;

import java.util.HashMap;
import java.util.Map;

public class GameOverViewModel extends ViewModel {
    public GameOverDataModel GameOverData;

    public GameOverViewModel() {
        GameOverData = new GameOverDataModel(0, new HashMap<String, Integer>(), "Original");
    }

    public void Test() {
        GameOverData.SetPlayerName("New Name");
    }
}
