package com.cs2340team7.project.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cs2340team7.project.models.GameDataModel;

import java.util.HashMap;
import java.util.Map;

public class IntroScreenViewModel extends ViewModel {
    private GameDataModel gameData;
    private MutableLiveData<String> statusMessage;
    private Map<String, Integer> difficulties;

    public IntroScreenViewModel() {
        gameData = GameDataModel.getData();
        statusMessage = new MutableLiveData<>();

        difficulties = new HashMap<>();
        difficulties.put("Easy", 100);
        difficulties.put("Medium", 75);
        difficulties.put("Hard", 50);
    }

    public void setPlayerName(String playerName) {
        if (playerName.isEmpty()) {
            statusMessage.postValue("Please fill out the name field.");
        } else {
            gameData.setPlayerName(playerName);
            if (gameData.getDifficulty() != "-1") {
                proceed();
            }
        }
    }

    public GameDataModel getGameData() {
        return gameData;
    }

    public LiveData<String> getStatusMessage() {
        return statusMessage;
    }

    public void setDifficulty(String difficulty) {
        if (difficulty == "-1") {
            statusMessage.postValue("Please fill out the difficulty field.");
        } else {
            gameData.setDifficulty(difficulty);
            gameData.setMaxHealth(difficulties.get(difficulty));
            gameData.setCurrentHealth(gameData.getMaxHealth());
            if (gameData.getPlayerName() != "") {
                proceed();
            }
        }
    }

    public void proceed() {
        String message = "START. Player Name: "
                + gameData.getPlayerName()
                + "\nDifficulty: " + gameData.getDifficulty();
        statusMessage.postValue(message);
    }
}

