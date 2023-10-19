package com.cs2340team7.project.models;

import androidx.lifecycle.ViewModel;

import java.util.Timer;
import java.util.TimerTask;

public class Player extends ViewModel {
    private Timer timer;
    private GameDataModel gameData;
    private Boolean running;
    private int x, y;

    private static Player player;
    private Player() {
        gameData = GameDataModel.getData();
        gameData.setCurrentScore(20);
        running = false;
    }

    public GameDataModel getGameData() {
        return gameData;
    }

    public boolean getRunning() {
        return running;
    }

    public static Player getPlayer() {
        if (player == null) {
            player = new Player();
        }
        return player;
    }

    public int getScore() {
        return gameData.getCurrentScore();
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
        if (gameData.getCurrentScore() > 0) {
            gameData.setCurrentScore(gameData.getCurrentScore() - 1);
        } else {
            timer.cancel();
        }
    }
    public void stopDecrease() {
        running = false;
        timer.cancel();
        gameData.setCurrentScore(20);
    }
    public void updatePosition(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
