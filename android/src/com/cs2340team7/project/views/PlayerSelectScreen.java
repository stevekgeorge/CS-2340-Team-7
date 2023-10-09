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
import com.cs2340team7.project.databinding.ActivityIntroScreenBinding;
import com.cs2340team7.project.databinding.ActivityPlayerSelectScreenBinding;

public class PlayerSelectScreen extends AppCompatActivity {

    private ActivityPlayerSelectScreenBinding mainBinding;
    private PlayerSelectViewModel viewModel;
    private Intent BeginIntent;

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

        BeginIntent = new Intent(PlayerSelectScreen.this, GameScreen.class);
        BeginIntent.putExtra("playerName", getIntent().getStringExtra("playerName"));
        BeginIntent.putExtra("difficulty", getIntent().getStringExtra("difficulty"));
        character1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.SetSelectedPlayer("Sword Master Sid");
                BeginIntent.putExtra("selectedCharacter", "Sword Master Sid");
                startActivity(BeginIntent);
            }
        });
        character2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.SetSelectedPlayer("General Gabe");
                BeginIntent.putExtra("selectedCharacter", "General Gabe");
                startActivity(BeginIntent);
            }
        });
        character3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.SetSelectedPlayer("The Purple Persian");
                BeginIntent.putExtra("selectedCharacter", "The Purple Persian");
                startActivity(BeginIntent);
            }
        });
    }
}