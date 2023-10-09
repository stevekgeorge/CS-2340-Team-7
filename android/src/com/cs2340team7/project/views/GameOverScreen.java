package com.cs2340team7.project.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cs2340team7.project.databinding.ActivityGameOverScreenBinding;
import com.cs2340team7.project.viewmodels.GameOverViewModel;
import com.cs2340team7.project.R;

public class GameOverScreen extends AppCompatActivity {

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
}