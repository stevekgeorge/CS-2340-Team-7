/**
 * IntroScreen class represents the introductory screen of the game where the player
 * enters their name and selects the game difficulty.
 */
package com.cs2340team7.project.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.cs2340team7.project.R;
import com.cs2340team7.project.databinding.ActivityIntroScreenBinding;
import com.cs2340team7.project.viewmodels.IntroScreenViewModel;

public class IntroScreen extends AppCompatActivity {

    private ActivityIntroScreenBinding mainBinding;
    private IntroScreenViewModel viewModel;
    private EditText name;
    private RadioGroup difficulty;

    /**
     * Overrides the onCreate method of AppCompatActivity to initialize the activity.
     * Sets up data binding, UI elements, and event listeners.
     * @param savedInstanceState The saved instance state bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set up data binding
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_intro_screen);
        mainBinding.setLifecycleOwner(this);
        viewModel = new IntroScreenViewModel();
        mainBinding.setViewModel(viewModel);

        // Initialize UI elements
        name = findViewById(R.id.nameInput);
        difficulty = findViewById(R.id.difficultyRadioGroup);
        Button continueButton = findViewById(R.id.startButton7);
        Button exitButton = findViewById(R.id.exitButton);

        // Observe the status message from the ViewModel to handle game start or display error messages
        viewModel.getStatusMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (!s.isEmpty()) {
                    if (s.substring(0, 6).equals("START.")) {
                        // Start the player selection screen on successful input
                        Intent intent = new Intent(IntroScreen.this, PlayerSelectScreen.class);
                        startActivity(intent);
                    } else {
                        // Display error message using a Toast
                        Toast.makeText(getBaseContext(), s, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Set up the continueButton click listener to gather user input and update ViewModel
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get user input
                String playerName = name.getText().toString();
                int selectedDifficultyId = difficulty.getCheckedRadioButtonId();
                viewModel.setPlayerName(playerName);
                RadioButton button = findViewById(selectedDifficultyId);
                viewModel.setDifficulty(String.valueOf(button.getText()));
            }
        });

        // Set up the exitButton click listener to exit the application
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
    }
}
