package com.cs2340team7.project.views;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.cs2340team7.project.R;
import com.cs2340team7.project.viewmodels.GameScreenLauncherViewModel;

public class GameScreenLauncher extends AndroidApplication {
    GameScreenLauncherViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new GameScreenLauncherViewModel();
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

        if (model.GetCurrentLevel() == 1) {
            initialize(new TechGreen(this), config);
        } else if (model.GetCurrentLevel() == 2) {
            initialize(new Klaus(this), config);
        }

    }
}