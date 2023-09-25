package com.example.team7project;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.content.Intent;

public class IntroScreen extends AppCompatActivity {

    private EditText name;
    private RadioGroup difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_screen);

        name = findViewById(R.id.nameInput);
        difficulty = findViewById(R.id.difficultyRadioGroup);
        Button continueButton = findViewById(R.id.startButton7);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get user input
                String playerName = name.getText().toString();
                int selectedDifficultyId = difficulty.getCheckedRadioButtonId();

                // Check if the name and difficulty are entered
                if (playerName.isEmpty() || selectedDifficultyId == -1) {
                    Toast.makeText(IntroScreen.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Retrieve selected difficulty
                    RadioButton selectedRadioButton = findViewById(selectedDifficultyId);
                    String selectedDifficulty = selectedRadioButton.getText().toString();

                    // Handle the data (e.g., pass it to the next activity)
                    // For simplicity, we'll just show a Toast message here
                    String message = "Player Name: " + playerName + "\nDifficulty: " + selectedDifficulty;
                    Toast.makeText(IntroScreen.this, message, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(IntroScreen.this, PlayerSelectScreen.class);
                    startActivity(intent);
                    // TODO: Add code to navigate to the next screen (e.g., PlayerSelectActivity)
                }
            }
        });
    }
}