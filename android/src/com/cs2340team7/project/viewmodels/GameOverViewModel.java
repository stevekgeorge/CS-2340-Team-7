package com.cs2340team7.project.viewmodels;

import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.ViewModel;

import com.cs2340team7.project.models.PlayerScore;
import com.cs2340team7.project.models.GameDataModel;
import com.cs2340team7.project.views.IntroScreen;

public class GameOverViewModel extends ViewModel {
    public GameDataModel GameData;

    public GameOverViewModel() {
        GameData = GameDataModel.getData();
    }

    public void Restart(Context context) {
        GameData.Clear();
        PlayerScore.GetPlayerScore().stopDecrease();
        Intent intent = new Intent(context, IntroScreen.class);
        context.startActivity(intent);
    }
}
