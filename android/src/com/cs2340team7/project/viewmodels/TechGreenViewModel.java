package com.cs2340team7.project.viewmodels;

import androidx.lifecycle.ViewModel;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.cs2340team7.project.models.GameDataModel;
import com.cs2340team7.project.models.Player;
/**
 * The TechGreenViewModel class serves as a ViewModel in the MVVM architecture for controlling the game's logic and data.
 * It manages interactions between the game data, player, and graphical representations of the player's character.
 */

public class TechGreenViewModel extends ViewModel {
    private GameDataModel gameData;
    private Player player;
    /**
     * Constructs a new instance of TechGreenViewModel.
     * Initializes the game data and player, and sets the player's movement strategy.
     */
    public TechGreenViewModel() {
        gameData = GameDataModel.getData();
        player = Player.getPlayer();
        //I think there is a better way to couple movement strategy and player but not sure
        player.setMovementStrategy();
    }
    /**
     * Advances the current game level to the next level.
     */
    public void advanceLevel() {
        gameData.setCurrentLevel(2);
    }
    public GameDataModel getGameData() {
        return gameData;
    }
    public void updateMap(TiledMap map) {
        gameData.updateMap(map);
    }
    /**
     * Initiates a movement for the player in the specified direction.
     *
     * @param direction The direction in which the player should move.
     */
    public void move(Player.Direction direction) {
        player.move(direction);
    }
    public float getX() {
        return player.getX();
    }
    public float getY() {
        return player.getY();
    }
    /**
     * Updates the player's position to the specified coordinates.
     *
     * @param newX The new X-coordinate for the player.
     * @param newY The new Y-coordinate for the player.
     */
    public void updatePosition(int newX, int newY) {
        player.updatePosition(newX, newY);
    }

    public void setPlayerSprite(Sprite sprite) {
        player.setPlayerSprite(sprite);
    }
    public Sprite getPlayerSprite() {
        return player.getSprite(false);
    }
    public Sprite getAttackSprite() {
        return player.getSprite(true);
    }
    public Boolean exit() {
        return player.exit();
    }
}
