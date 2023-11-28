/**
 * GameScreenLauncher class serves as the launcher for the game screens.
 * It initializes the appropriate game screen based on the current level and manages the game state.
 */
package com.cs2340team7.project.views;

import android.content.Intent;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.cs2340team7.project.models.Player;
import com.cs2340team7.project.viewmodels.GameScreenLauncherViewModel;

public class GameScreenLauncher extends AndroidApplication {
    private GameScreenLauncherViewModel model;

    /**
     * Overrides the onCreate method of AndroidApplication to
     * initialize the game screen launcher.
     * It sets up the view model, checks the current game state,
     * and launches the appropriate game screen.
     * @param savedInstanceState The saved instance state bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize the view model
        model = new GameScreenLauncherViewModel();
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

        // Check if the player is running and start decreasing health if not
        if (!Player.getPlayer().getRunning()) {
            Player.getPlayer().startDecrease();
        }

        // Determine the current level and initialize the corresponding game screen
        if (model.getCurrentLevel() == 1) {
            initialize(new TechGreen(this), config);
        } else if (model.getCurrentLevel() == 2) {
            initialize(new Klaus(this), config);
        } else if (model.getCurrentLevel() == 3) {
            initialize(new Skiles(this), config);
        } else if (model.getCurrentLevel() == -1) {
            // If the current level is -1, navigate to the game over screen
            Intent intent = new Intent(this, GameOverScreen.class);
            startActivity(intent);
        }
    }
}
