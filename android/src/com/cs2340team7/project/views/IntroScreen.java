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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_intro_screen);
        mainBinding.setLifecycleOwner(this);
        viewModel = new IntroScreenViewModel();
        mainBinding.setViewModel(viewModel);

        name = findViewById(R.id.nameInput);
        difficulty = findViewById(R.id.difficultyRadioGroup);
        Button continueButton = findViewById(R.id.startButton7);

        viewModel.GetStatusMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != "") {
                    Toast.makeText(IntroScreen.this, s, Toast.LENGTH_SHORT).show();
                    if (s.substring(0, 6).equals("START.")) {
                        Intent intent = new Intent(IntroScreen.this, PlayerSelectScreen.class);
                        intent.putExtra("playerName", viewModel.IntroScreenData.PlayerName);
                        intent.putExtra("difficulty", viewModel.IntroScreenData.Difficulty);
                        startActivity(intent);
                    }
                }
            }
        });

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get user input
                String playerName = name.getText().toString();
                int selectedDifficultyId = difficulty.getCheckedRadioButtonId();
                viewModel.SetPlayerName(playerName);
                RadioButton button = (RadioButton)findViewById(selectedDifficultyId);
                viewModel.SetDifficulty(String.valueOf(button.getText()));
            }
        });
    }
}