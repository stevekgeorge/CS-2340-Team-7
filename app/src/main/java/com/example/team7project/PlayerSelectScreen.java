package com.example.team7project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class PlayerSelectScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_select_screen);

        ImageButton character1Button = findViewById(R.id.character1Button);
        ImageButton character2Button = findViewById(R.id.character2Button);
        ImageButton character3Button = findViewById(R.id.character3Button);

        character1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the next activity
                Intent intent = new Intent(PlayerSelectScreen.this, PlayerSelectScreen.class);

                // Add any necessary data as extras to the intent
                intent.putExtra("selectedCharacter", "Sword Master Sid");

                // Start the next activity
                startActivity(intent);
            }
        });
    }
}