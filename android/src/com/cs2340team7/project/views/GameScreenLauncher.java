package com.cs2340team7.project.views;

import android.content.Intent;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.cs2340team7.project.PlayerScore;
import com.cs2340team7.project.viewmodels.GameScreenLauncherViewModel;

public class GameScreenLauncher extends AndroidApplication {
    GameScreenLauncherViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new GameScreenLauncherViewModel();
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

        if (!PlayerScore.GetPlayerScore().running) {
            PlayerScore.GetPlayerScore().startDecrease();
        }

        if (model.GetCurrentLevel() == 1) {
            initialize(new TechGreen(this), config);
        } else if (model.GetCurrentLevel() == 2) {
            initialize(new Klaus(this), config);
        } else if (model.GetCurrentLevel() == 3) {
            initialize(new Skiles(this), config);
        } else if (model.GetCurrentLevel() == -1) {
            Intent intent = new Intent(this, GameOverScreen.class);
            startActivity(intent);
        }
    }
}