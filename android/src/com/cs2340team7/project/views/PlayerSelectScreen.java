/**
 * PlayerSelectScreen class represents the screen where the player selects a character before starting the game.
 * It allows the player to choose from different characters and initiates the game with the selected character.
 */
package com.cs2340team7.project.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.cs2340team7.project.databinding.ActivityPlayerSelectScreenBinding;
import com.cs2340team7.project.viewmodels.PlayerSelectViewModel;
import com.cs2340team7.project.R;

import java.text.DateFormat;
import java.util.Calendar;

public class PlayerSelectScreen extends AppCompatActivity {

    private ActivityPlayerSelectScreenBinding mainBinding;
    private PlayerSelectViewModel viewModel;
    private Intent beginIntent;

    /**
     * Overrides the onCreate method of AppCompatActivity to initialize the player selection screen.
     * It sets up data binding, UI elements, and event listeners for character selection.
     * @param savedInstanceState The saved instance state bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set up data binding
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_player_select_screen);
        mainBinding.setLifecycleOwner(this);
        viewModel = new PlayerSelectViewModel();
        mainBinding.setViewModel(viewModel);

        // Initialize UI elements
        ImageButton character1Button = findViewById(R.id.character1Button);
        ImageButton character2Button = findViewById(R.id.character2Button);
        ImageButton character3Button = findViewById(R.id.character3Button);

        // Create an intent to start the game screen
        beginIntent = new Intent(PlayerSelectScreen.this, GameScreenLauncher.class);

        // Set up click listeners for character buttons
        character1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setSelectedPlayer("Sid");
                viewModel.getGameData().setStartTime(
                        DateFormat.getDateTimeInstance(
                                DateFormat.SHORT,
                                DateFormat.SHORT).format(
                                Calendar.getInstance().getTime()).toString());
                startActivity(beginIntent);
            }
        });

        character2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setSelectedPlayer("Gabe");
                viewModel.getGameData().setStartTime(
                        DateFormat.getDateTimeInstance(
                                DateFormat.SHORT,
                                DateFormat.SHORT).format(
                                Calendar.getInstance().getTime()).toString());

                startActivity(beginIntent);
            }
        });

        character3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setSelectedPlayer("Persian");
                viewModel.getGameData().setStartTime(
                        DateFormat.getDateTimeInstance(
                                DateFormat.SHORT,
                                DateFormat.SHORT).format(
                                Calendar.getInstance().getTime()).toString());
                startActivity(beginIntent);
            }
        });
    }
}
