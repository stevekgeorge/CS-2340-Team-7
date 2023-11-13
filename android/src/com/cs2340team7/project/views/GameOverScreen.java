package com.cs2340team7.project.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.badlogic.gdx.Gdx;
import com.cs2340team7.project.models.GameDataModel;
import com.cs2340team7.project.models.Leaderboard;
import com.cs2340team7.project.databinding.ActivityGameOverScreenBinding;
import com.cs2340team7.project.viewmodels.GameOverViewModel;
import com.cs2340team7.project.R;

import java.util.List;

public class GameOverScreen extends AppCompatActivity {

    private ActivityGameOverScreenBinding mainBinding;
    private GameOverViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_game_over_screen);
        mainBinding.setLifecycleOwner(this);
        viewModel = new GameOverViewModel();
        mainBinding.setViewModel(viewModel);

        TableLayout leaderboard = findViewById(R.id.leaderboard);
        List<Leaderboard.LeaderboardEntry> entries = Leaderboard.getLeaderboard().getEntries();

        if (entries.size() == 0) {
            entries.add(Leaderboard.getLeaderboard().dummy());
        }

        for (int i = 0; i < Math.min(entries.size(), 10); i++) {
            Leaderboard.LeaderboardEntry entry = entries.get(i);

            TableRow row = new TableRow(this);

            TextView name = new TextView(this);
            name.setTextSize(20);
            TextView score = new TextView(this);
            score.setTextSize(20);
            TextView date = new TextView(this);
            date.setTextSize(20);

            name.setText(entry.getPlayerName());
            score.setText(String.valueOf(entry.getScore()));
            date.setText(entry.getDate());

            row.addView(name);
            row.addView(score);
            row.addView(date);

            leaderboard.addView(row);
        }
    }

    public void restart(View v) {
        viewModel.restart(this);
    }
}