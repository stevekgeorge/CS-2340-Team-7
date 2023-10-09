package com.cs2340team7.project.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.cs2340team7.project.R;
import com.cs2340team7.project.databinding.ActivityGameScreenBinding;
import com.cs2340team7.project.viewmodels.GameScreenViewModel;

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
    }

    public void end(View v) {
        Intent intent = new Intent(GameScreen.this, GameOverScreen.class);
        startActivity(intent);
    }
}