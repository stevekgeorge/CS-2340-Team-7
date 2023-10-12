package com.cs2340team7.project.views;

import android.content.Intent;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.cs2340team7.project.models.Player;
import com.cs2340team7.project.viewmodels.GameScreenLauncherViewModel;

public class GameScreenLauncher extends AndroidApplication {
    private GameScreenLauncherViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new GameScreenLauncherViewModel();
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

        if (!Player.getPlayer().getRunning()) {
            Player.getPlayer().startDecrease();
        }

        if (model.getCurrentLevel() == 1) {
            initialize(new TechGreen(this), config);
        } else if (model.getCurrentLevel() == 2) {
            initialize(new Klaus(this), config);
        } else if (model.getCurrentLevel() == 3) {
            initialize(new Skiles(this), config);
        } else if (model.getCurrentLevel() == -1) {
            Intent intent = new Intent(this, GameOverScreen.class);
            startActivity(intent);
        }
    }
}