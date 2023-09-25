package com.example.team7project.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.team7project.models.IntroScreenDataModel;

public class IntroScreenViewModel extends ViewModel {
    public IntroScreenDataModel IntroScreenData;
    private MutableLiveData<String> StatusMessage;

    public IntroScreenViewModel() {
        IntroScreenData = new IntroScreenDataModel("", "-1");
        StatusMessage = new MutableLiveData<>();
    }

    public void SetPlayerName(String PlayerName) {
        if (PlayerName.isEmpty()) {
            StatusMessage.setValue("Please fill out the name field.");
        } else {
            IntroScreenData.PlayerName = PlayerName;
            if (IntroScreenData.Difficulty != "-1") {
                Proceed();
            }
        }
    }

    public LiveData<String> GetStatusMessage() {
        return StatusMessage;
    }

    public void SetDifficulty (String Difficulty) {
        if (Difficulty == "-1") {
            StatusMessage.setValue("Please fill out the difficulty field.");
        } else {
            IntroScreenData.Difficulty = Difficulty;
            if (IntroScreenData.PlayerName != "") {
                Proceed();
            }
        }
    }

    public void Proceed() {
        // Handle the data (e.g., pass it to the next activity)
        // For simplicity, we'll just show a Toast message here
        String message = "START. Player Name: " + IntroScreenData.PlayerName + "\nDifficulty: " + IntroScreenData.Difficulty;
        StatusMessage.setValue(message);
    }
}

