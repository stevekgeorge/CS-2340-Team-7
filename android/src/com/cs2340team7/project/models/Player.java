package com.cs2340team7.project.models;

import androidx.lifecycle.ViewModel;

import java.util.Timer;
import java.util.TimerTask;

public class Player extends ViewModel {
    public Timer timer;
    public GameDataModel GameData;
    public Boolean running;

    private static Player Player;
    private Player() {
        GameData = GameDataModel.getData();
        GameData.CurrentScore = 20;
        running = false;
    }

    public static Player GetPlayerScore() {
        if (Player == null) {
            Player = new Player();
        }
        return Player;
    }

    public int getScore() {
        return GameData.CurrentScore;
    }
    public void startDecrease() {
        running = true;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                decreaseScore();
            }
        }, 3000, 3000);

    }
    public void decreaseScore() {
        if (GameData.CurrentScore > 0) {
            GameData.CurrentScore--;
        } else {
            timer.cancel();
        }
    }
    public void stopDecrease() {
        running = false;
        timer.cancel();
        GameData.CurrentScore = 20;
    }
}
