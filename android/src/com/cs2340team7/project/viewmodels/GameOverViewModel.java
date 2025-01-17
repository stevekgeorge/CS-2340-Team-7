package com.cs2340team7.project.viewmodels;

import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.ViewModel;

import com.cs2340team7.project.models.Player;
import com.cs2340team7.project.models.GameDataModel;
import com.cs2340team7.project.views.IntroScreen;

public class GameOverViewModel extends ViewModel {
    private GameDataModel gameData;

    public GameOverViewModel() {
        gameData = GameDataModel.getData();
    }

    public void restart() {
        gameData.clear();
        Player.getPlayer().stopDecrease();
        Player.getPlayer().resetPlayerSubscribers();
    }

    public void restart(Context context) {
        gameData.clear();
        Player.getPlayer().resetPlayerSubscribers();
        Player.getPlayer().stopDecrease();
        Intent intent = new Intent(context, IntroScreen.class);
        context.startActivity(intent);
    }

    public GameDataModel getGameData() {
        return gameData;
    }
}
