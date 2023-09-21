package com.example.team7project.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.team7project.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over_screen);
    }

    @BindingAdapter({"PlayerName"})
    public static void UpdateName(View view, String Name) {
        TextView nameLabel = (TextView) view.findViewById(R.id.playerNameLabel);
        nameLabel.setText(Name);
    }
}