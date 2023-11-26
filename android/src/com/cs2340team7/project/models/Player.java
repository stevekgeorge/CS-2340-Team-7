package com.cs2340team7.project.models;

import androidx.lifecycle.ViewModel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Player extends ViewModel implements MapSubscriber {
    public static enum Direction { UP, DOWN, LEFT, RIGHT }
    private Timer timer;
    private GameDataModel gameData;
    private Boolean running;
    private int x;
    private int y;

    private int sizeX;
    private int sizeY;

    private TiledMap map;
    private MovementStrategy movementStrategy;

    private static Player player;

    private Sprite playerSprite;
    private ArrayList<PlayerPositionSubscriber>
            playerPositionSubscribers = new ArrayList<PlayerPositionSubscriber>();


    protected Player() {
        gameData = GameDataModel.getData();
        gameData.setCurrentScore(20);
        running = false;
        //when the player is created add it as a map subscriber
        gameData.addMapSubscribers(this);
        sizeX = 30;
        sizeY = 30;
    }

    public GameDataModel getGameData() {
        return gameData;
    }

    /**
     * sets the movementStrategy variable based on what player has been chosen.
     * This is for strategy design implementation
     */
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

    /**
     * method that creates a timer such that it counts down and decreases the score every three seconds
     */
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

    /**
     * decreases the current score of the game by one every time the method is called.
     * if the score has reached 0, the timer is canceled and the method is called no more
     */
    public void decreaseScore() {
        if (gameData.getCurrentScore() > 0) {
            gameData.setCurrentScore(gameData.getCurrentScore() - 1);
        } else {
            timer.cancel();
        }
    }

    /**
     * the timer for score decrease is canceled and the currentScore is reset back to 20.
     */
    public void stopDecrease() {
        running = false;
        timer.cancel();
        gameData.setCurrentScore(20);
    }

    /**
     * updates position of the player every time the sprite moves.
     * The method notifies every subscriber of the player's movement as well as
     * the new bounding rectangle for the player
     * This is vital for implementing the observer design pattern.
     * @param newX the new X position of the player after it has moved
     * @param newY the new Y position of the player after it has moved
     */
    public void updatePosition(int newX, int newY) {
        Gdx.app.log("MOVEMENT", "update pos called");
        playerSprite.setX(newX);
        playerSprite.setY(newY);
        for (PlayerPositionSubscriber subscriber: playerPositionSubscribers) {
            subscriber.updatePlayerPosition(playerSprite.getBoundingRectangle());
        }

    }
    public int getX() {
        return (int) playerSprite.getX();
    }
    public int getY() {
        return (int) playerSprite.getY();
    }
    public void updateMap(TiledMap map) {
        this.map = map;

    }

    /**
     * adds subscribers of interface type PlayerPositionSubsriber to an arrayList.
     * This is for implementing observer pattern for player movement
     * @param subscriber the new subsriber instance being added to the arrayList.
     */
    public void addPlayerPositionSubscribers(PlayerPositionSubscriber subscriber) {
        this.playerPositionSubscribers.add(subscriber);
    }


    public MovementStrategy getMovementStrategy() {
        return movementStrategy;
    }

    /**
     * determines whether a sprite has exited the boundaries of the game screen.
     * @return a boolean determining whether the boundaries have been exceeded by a sprite.
     */
    public boolean exit() {
        System.out.printf("exit called, x is %d, map width is %d ",
                getX(), (int) map.getProperties().get("width"));
        if (getX() > 850) {
            return true;
        }
        return false;
    }

    /**
     * method that determines where the player is on the screen based on coordinates, and converts that
     * to a particular tile (32 pixels)
     * @param pos an int representing the change in position of the sprite compared with its last position.
     *            (either in the X or Y direction depending on the isX variable).
     * @param isX boolean that indicates whether the sprite moved in the X direction or not
     * @return
     */
    private int convertCordToCell(int pos, boolean isX) {
        //assumes all maps have cells that are 32 pixels wide
        int heightConstant = 32;
        int widthConstant =  32;
        if (isX) {
            return (int) pos / widthConstant;
        } else {
            return (int) (pos) / heightConstant;
        }
    }

    /**
     * moves the player based on the particular movementStrategy instance chosen.
     * This is for implementing the strategy design pattern.
     * @param direction direction of movement
     */
    public void move(Player.Direction direction) {
        movementStrategy.move(direction);
    }

    public void attack(Rectangle playerRect) {
        if (playerSprite.getBoundingRectangle().overlaps((playerRect))) {

        }
    }

    /**
     * method that determines whether the next tile is solid or not and whether the player can
     * move through it or not.
     * This method is for determining wall collisions.
     * @param newX the X variable of the new tile the player is moving into
     * @param newY the Y variable of the new tile the player is moving into
     * @return boolean determining whether it is possible for a sprite to move into a tile
     */
    public boolean canMove(int newX, int newY) {
        this.x = getX();
        this.y = getY();
        if (newY > Gdx.graphics.getHeight() - 100) {
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
                maxNum = convertCordToCell(newY + 80, false); //const for width of char
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
                maxNum = convertCordToCell(newX + 80, true); //const for width of char
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

    public Sprite getSprite() {

        String character = gameData.getCharacter();
        String filePath = null;
        switch (character) {
        case "Persian" :
            filePath = "thepurplepersian.png";
            break;
        case "Gabe" :
            filePath = "generalgabe.png";
            break;
        case "Sid" :
            filePath = "swordmastersid.png";
            break;
        default:
            filePath = "swordmastersid.png";
            break;
        }
        FileHandle fileHandle = Gdx.files.internal(filePath);
        Texture texture = new Texture(fileHandle);
        Sprite sprite = new Sprite(texture);
        sprite.setSize(160, 160);
        playerSprite = sprite;
        return playerSprite;
    }
    public void  setPlayerSprite(Sprite sprite) {
        this.playerSprite = sprite;
    }
    public void setXAndY(int x, int y) {
        this.x = x;
        this.y = y;
    }
}



