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
    private MovementStrategy movementStrategy;

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
                movementStrategy = new PurplePersian(getPlayer());
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
    public void updateMap(TiledMap map) {
        this.map = map;
        Gdx.app.log("MOVEMENT", "map updated in player");
    }

    private int convertCordToCell(int pos, boolean isX) {
        //assumes all maps have cells that are 32 pixels wide
        int width_constant =  Gdx.graphics.getWidth() / (int) map.getProperties().get("width");
        int height_constant =  Gdx.graphics.getHeight() / (int) map.getProperties().get("height");;
        if (isX){
            return (int) pos / width_constant ;
        } else {
            int height = Gdx.graphics.getHeight();
            return (int) (pos) / height_constant;
        }
    }

    public void move(Player.Direction direction) {
        movementStrategy.move(direction);
    }
    public boolean canMove(int new_x, int new_y) {
//        if (gameData.getCurrentLevel() == 3){
//            return true;
//        }
        Gdx.app.log("MOVEMENT", "canMove was called");
        //expects one and only one of new_x, new_y to be different from current x,y
        //requires that wall tiles have an "isSolid" property and that they are listed on layer 1
        int min_num = 0;
        int max_num = 0;
        TiledMapTileLayer tileLayer = (TiledMapTileLayer) this.map.getLayers().get("Tile Layer 1");
        TiledMapTileLayer.Cell cell;
        if (this.x == new_x) {
            if (this.y <= new_y) {
                min_num = convertCordToCell(this.y, false);
                max_num = convertCordToCell(new_y + 80, false); //constant for width of char
            } else {
                max_num = convertCordToCell(this.y, false);
                min_num = convertCordToCell(new_y, false);
            }
            while (min_num <= max_num) {
                cell = tileLayer.getCell(convertCordToCell(this.x, true), min_num);
                if (cell == null) {
                    System.out.println("cell is null");
                    return false;
                } else if (cell.getTile().getProperties().containsKey("isSolid")) {
                    if ((boolean) cell.getTile().getProperties().get("isSolid")) {
                        System.out.println("found WALL");
                        System.out.printf("X is %d, Y is %d", convertCordToCell(this.x, true), min_num);
                        System.out.printf("X is %d, Y is %d", this.x, this.y);
                        return false;
                    }

                }
                min_num++;

            }
        } else {
            if (this.x <= new_x) {
                min_num = convertCordToCell(this.x, true);
                max_num = convertCordToCell(new_x + 80, true); //conts for width of char
            } else {
                max_num = convertCordToCell(this.x, true);
                min_num = convertCordToCell(new_x, true);
            }
            while (min_num <= max_num) {
                cell = tileLayer.getCell(min_num, convertCordToCell(this.y, false));
                if (cell == null) {
                    System.out.println("cell is null");
                    System.out.printf("X is %d, Y is %d", min_num, convertCordToCell(this.y, false));
                    System.out.printf("X is %d, Y is %d", this.x, this.y);
                    return false;
                } else if (cell.getTile().getProperties().containsKey("isSolid")) {
                    if ((boolean) cell.getTile().getProperties().get("isSolid")) {
                        System.out.println("found WALL");
                        System.out.printf("X is %d, Y is %d", min_num, convertCordToCell(this.y, false));
                        System.out.printf("X is %d, Y is %d", this.x, this.y);
                        return false;
                    }

                }


                min_num++;

            }
        }


        return true;
    }
    public void setMap(TiledMap map) { this.map = map; }
}



