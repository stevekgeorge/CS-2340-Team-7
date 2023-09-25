package com.example.team7project.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.team7project.R;
import com.example.team7project.databinding.ActivityGameScreenBinding;
import com.example.team7project.databinding.ActivityIntroScreenBinding;
import com.example.team7project.models.GameScreenDataModel;
import com.example.team7project.viewmodels.GameOverViewModel;
import com.example.team7project.viewmodels.GameScreenViewModel;
import com.example.team7project.viewmodels.IntroScreenViewModel;

public class GameScreen extends AppCompatActivity {
    private ActivityGameScreenBinding mainBinding;
    private GameScreenViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_game_screen);
        mainBinding.setLifecycleOwner(this);
        viewModel = new GameScreenViewModel();
        mainBinding.setViewModel(viewModel);

        Intent intent = getIntent();
        String startHealth = "0";
        if (intent.getStringExtra("difficulty").equals("Easy")) {
            startHealth = "500 HP";
        } else if (intent.getStringExtra("difficulty").equals("Medium")) {
            startHealth = "250 HP";
        } else if (intent.getStringExtra("difficulty").equals("Hard")) {
            startHealth = "100 HP";
        }

        viewModel.SetData(new GameScreenDataModel(
                intent.getStringExtra("playerName"),
                intent.getStringExtra("difficulty"), startHealth,
                intent.getStringExtra("selectedCharacter").equals("Sword Master Sid") ? 0 : 1,
                intent.getStringExtra("selectedCharacter").equals("The Purple Persian") ? 0 : 1,
                intent.getStringExtra("selectedCharacter").equals("General Gabe") ? 0 : 1));
    }

    public void end(View v) {
        Intent intent = new Intent(GameScreen.this, GameOverActivity.class);
        startActivity(intent);
    }
}