package com.example.team7project.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.team7project.R;
import com.example.team7project.databinding.ActivityGameOverScreenBinding;
import com.example.team7project.viewmodels.GameOverViewModel;

public class GameOverActivity extends AppCompatActivity {

    ActivityGameOverScreenBinding mainBinding;
    GameOverViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_game_over_screen);
        mainBinding.setLifecycleOwner(this);
        viewModel = new GameOverViewModel();
        mainBinding.setViewModel(viewModel);
    }

    public void Test(View v) {
        viewModel.Test();
    }
}