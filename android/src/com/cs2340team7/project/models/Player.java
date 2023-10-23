package com.cs2340team7.project.models;

import androidx.lifecycle.ViewModel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.Timer;
import java.util.TimerTask;

public class Player extends ViewModel implements MapSubscriber {
    public static enum Direction { UP, DOWN, LEFT, RIGHT }
    private Timer timer;
    private GameDataModel gameData;
    private Boolean running;
    private int x;
    private int y;

    private TiledMap map;
    private MovementStrategy movementStrategy;

    private static Player player;

    private PlayerSprite playerSprite;


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

    public void setMovementStrategy() {
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
        default:
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
        playerSprite.setX(newX);
        playerSprite.setY(newY);
        Gdx.app.log("MOVEMENT", "x and y were updated");

    }
    public int getX() {
        return (int) playerSprite.getX();
    }
    public int getY() {
        return (int) playerSprite.getY();
    }
    public void updateMap(TiledMap map) {
        this.map = map;
        Gdx.app.log("MOVEMENT", "map updated in player");
    }
    //temp exit criteria
//    public boolean exit(){
//        System.out.printf("exit called, x is %d, map width is %d ", getX(), (int) map.getProperties().get("width"));
//        if (convertCordToCell(getX(), true) >= (int) map.getProperties().get("width") -3 ) {
//            return true;
//        }
//        return false;
//    }
    public boolean exit(){
        System.out.printf("exit called, x is %d, map width is %d ", getX(), (int) map.getProperties().get("width"));
        if (getX() > 900 ) {
            return true;
        }
        return false;
    }


    private int convertCordToCell(int pos, boolean isX) {
        //assumes all maps have cells that are 32 pixels wide
        int heightConstant;
        int widthConstant =  32;
        if (Gdx.graphics.getHeight() != 0 && Gdx.graphics.getWidth() != 0){
            heightConstant = (Gdx.graphics.getHeight()/ Gdx.graphics.getWidth()) * 32;
        }
        else {
            heightConstant =  32;
        }
        if (isX) {
            return (int) pos / widthConstant;
        } else {
            return (int) (pos) / heightConstant;
        }
    }

    public void move(Player.Direction direction) {
        movementStrategy.move(direction);
    }
    public boolean canMove(int newX, int newY) {
        this.x = getX();
        this.y =getY();
        //dummy check to make sure cant go off screen, was having a problem on skiles only,think that
        //its because its tiles are not quite right yet
        if (newY > Gdx.graphics.getHeight() -100){
            return false;
        }

        //expects one and only one of new_x, new_y to be different from current x,y
        //requires that wall tiles have an "isSolid" property and that they are listed on layer 1
        int minNum = 0;
        int maxNum = 0;
        TiledMapTileLayer tileLayer = (TiledMapTileLayer) this.map.getLayers().get("Tile Layer 1");
        TiledMapTileLayer.Cell cell;
        if (this.x == newX) {
            if (this.y <= newY) {
                minNum = convertCordToCell(this.y, false);
                maxNum = convertCordToCell(newY, false);
            } else {
                maxNum = convertCordToCell(this.y, false);
                minNum = convertCordToCell(newY, false);
            }
            while (minNum <= maxNum) {
                cell = tileLayer.getCell(convertCordToCell(this.x, true), minNum);
                if (cell == null) {
                    return false;
                } else if (cell.getTile().getProperties().containsKey("isSolid")) {
                    if ((boolean) cell.getTile().getProperties().get("isSolid")) {
                        System.out.println("found WALL");
                        System.out.printf("X is %d, Y is %d",
                                convertCordToCell(this.x, true), minNum);
                        System.out.printf("X is %d, Y is %d", this.x, this.y);
                        return false;
                    }

                }
                minNum++;

            }
        } else {
            if (this.x <= newX) {
                minNum = convertCordToCell(this.x, true);
                maxNum = convertCordToCell(newX + 40, true); //const for width of char
            } else {
                maxNum = convertCordToCell(this.x, true);
                minNum = convertCordToCell(newX, true);
            }
            while (minNum <= maxNum) {
                cell = tileLayer.getCell(minNum, convertCordToCell(this.y, false));
                if (cell == null) {
                    System.out.println("cell is null");
                    System.out.printf("X is %d, Y is %d", minNum, convertCordToCell(this.y, false));
                    System.out.printf("X is %d, Y is %d", this.x, this.y);
                    return false;
                } else if (cell.getTile().getProperties().containsKey("isSolid")) {
                    if ((boolean) cell.getTile().getProperties().get("isSolid")) {
                        System.out.println("found WALL");
                        System.out.printf("X is %d, Y is %d",
                                minNum, convertCordToCell(this.y, false));
                        System.out.printf("X is %d, Y is %d", this.x, this.y);
                        return false;
                    }

                }


                minNum++;

            }
        }


        return true;
    }
    public void setMap(TiledMap map) {
        this.map = map;
    }

    public PlayerSprite getSprite() {
        return playerSprite;
    }
    public void  setPlayerSprite(Sprite sprite){
        this.playerSprite = new PlayerSprite(sprite, player);
    }
}



