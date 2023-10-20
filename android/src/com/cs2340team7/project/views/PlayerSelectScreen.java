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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_player_select_screen);
        mainBinding.setLifecycleOwner(this);
        viewModel = new PlayerSelectViewModel();
        mainBinding.setViewModel(viewModel);

        ImageButton character1Button = findViewById(R.id.character1Button);
        ImageButton character2Button = findViewById(R.id.character2Button);
        ImageButton character3Button = findViewById(R.id.character3Button);

        beginIntent = new Intent(PlayerSelectScreen.this, GameScreenLauncher.class);
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