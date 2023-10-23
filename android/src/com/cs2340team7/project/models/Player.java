package com.cs2340team7.project.models;

import androidx.lifecycle.ViewModel;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.Timer;
import java.util.TimerTask;

public class Player extends ViewModel implements MapSubscriber {
    private Timer timer;
    private GameDataModel gameData;
    private Boolean running;
    private int x;
    private int y;

    private TiledMap map;

    private static Player player;
    protected Player() {
        gameData = GameDataModel.getData();
        gameData.setCurrentScore(20);
        running = false;
        //when the player is created add it as a map subscriber
        gameData.addMapSubscribers(this);
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
    public void updateMap(TiledMap map) {
        this.map = map;
    }
    public boolean canMove(int newX, int newY) {
        //expects one and only one of new_x, new_y to be different from current x,y
        //requires that wall tiles have an "isSolid" property and that they are listed on layer 1
        int minNum = 0;
        int maxNum = 0;
        TiledMapTileLayer tileLayer = (TiledMapTileLayer) this.map.getLayers().get(0);
        TiledMapTileLayer.Cell cell;
        if (this.x == newX) {
            if (this.y <= newY) {
                minNum = this.y;
                maxNum = newY;
            } else {
                maxNum = this.y;
                minNum = newY;
            }
            while (minNum <= maxNum) {
                cell = tileLayer.getCell(this.x, minNum);
                if (cell.getTile().getProperties().containsKey("isSolid")) {
                    return false;
                }
                minNum++;

            }
        } else {
            if (this.x <= newX) {
                minNum = this.x;
                maxNum = newX;
            } else {
                maxNum = this.x;
                minNum = newX;
            }
            while (minNum <= maxNum) {
                cell = tileLayer.getCell(minNum, this.y);
                if (cell.getTile().getProperties().containsKey("isSolid")) {
                    return false;
                }
                minNum++;

            }
        }
        return true;

    }
}
