package com.cs2340team7.project.models;

import androidx.lifecycle.ViewModel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.Timer;
import java.util.TimerTask;

public class Player extends ViewModel implements MapSubscriber {
    public static enum Direction {UP,DOWN,LEFT,RIGHT}
    private Timer timer;
    private GameDataModel gameData;
    private Boolean running;
    private int x;
    private int y;

    private TiledMap map;
    private movementStrategy movementStrategy;

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

    public void setMovementStrategy(){
        String character = gameData.getCharacter();
        switch (character) {
            case "Persian" :
                movementStrategy = new purplePersian(getPlayer());
                break;
            case "Gabe" :
                movementStrategy = new GeneralGabe(getPlayer());
                break;
            case "Sid" :
                movementStrategy = new SwordMasterSid(getPlayer());
                break;
        }
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
        Gdx.app.log("MOVEMENT", "x and y were updated");

    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void updateMap(TiledMap map){
        this.map = map;
        Gdx.app.log("MOVEMENT", "map updated in player");
    }

    public void move(Player.Direction direction) {
        movementStrategy.move(direction);
    }
    public boolean canMove(int new_x, int new_y){
        Gdx.app.log("MOVEMENT", "canMove was called");
        //expects one and only one of new_x, new_y to be different from current x,y
        //requires that wall tiles have an "isSolid" property and that they are listed on layer 1
        int min_num = 0;
        int max_num = 0;
        TiledMapTileLayer tileLayer = (TiledMapTileLayer) this.map.getLayers().get("Tile Layer 1");
        TiledMapTileLayer.Cell cell;
        if (this.x == new_x){
            if (this.y <= new_y) {
                min_num = this.y;
                max_num = new_y;
            } else {
                max_num = this.y;
                min_num = new_y;
            }
            while (min_num <= max_num) {
                cell = tileLayer.getCell(this.x, min_num);
                if (cell == null){
                    Gdx.app.log("MOVEMENT", "cell is null");
                    System.out.printf("X is %d, Y is %d", this.x, min_num);
                }
                if (cell != null && !(cell.getTile().getProperties().containsKey("isSolid"))) {
                    return false;
                }
                min_num++;

            }
        } else {
            if (this.x <= new_x) {
                min_num = this.x;
                max_num = new_x;
            } else {
                max_num = this.x;
                min_num = new_x;
            }
            while (min_num <= max_num) {
                cell = tileLayer.getCell(min_num,this.y);
                if (cell == null){
                    Gdx.app.log("MOVEMENT", "cell is null");
                    System.out.printf("X is %d, Y is %d", min_num, this.y);
                } else {
                    System.out.printf("CELL IS NOT NULL, X is %d, Y is %d", min_num, this.y);
                }

                if (cell != null && !(cell.getTile().getProperties().containsKey("isSolid"))) {

                    return false;
                }
                min_num++;

            }
        }
        return true;

    }
}
