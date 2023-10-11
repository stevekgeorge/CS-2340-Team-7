package com.cs2340team7.project.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cs2340team7.project.models.GameDataModel;

import java.util.HashMap;
import java.util.Map;

public class IntroScreenViewModel extends ViewModel {
    public GameDataModel GameData;
    private MutableLiveData<String> StatusMessage;
    private Map<String, Integer> Difficulties;

    public IntroScreenViewModel() {
        GameData = GameDataModel.getData();
        StatusMessage = new MutableLiveData<>();

        Difficulties = new HashMap<>();
        Difficulties.put("Easy", 500);
        Difficulties.put("Medium", 250);
        Difficulties.put("Hard", 100);
    }

    public void SetPlayerName(String PlayerName) {
        if (PlayerName.isEmpty()) {
            StatusMessage.postValue("Please fill out the name field.");
        } else {
            GameData.PlayerName = PlayerName;
            if (GameData.Difficulty != "-1") {
                Proceed();
            }
        }
    }

    public LiveData<String> GetStatusMessage() {
        return StatusMessage;
    }

    public void SetDifficulty (String Difficulty) {
        if (Difficulty == "-1") {
            StatusMessage.postValue("Please fill out the difficulty field.");
        } else {
            GameData.Difficulty = Difficulty;
            GameData.MaxHealth = Difficulties.get(Difficulty);
            GameData.CurrentHealth = GameData.MaxHealth;
            if (GameData.PlayerName != "") {
                Proceed();
            }
        }
    }

    public void Proceed() {
        String message = "START. Player Name: " + GameData.PlayerName + "\nDifficulty: " + GameData.Difficulty;
        StatusMessage.postValue(message);
    }
}

