package com.cs2340team7.project.viewmodels;

import androidx.lifecycle.ViewModel;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.cs2340team7.project.models.GameDataModel;
import com.cs2340team7.project.models.Player;
import com.cs2340team7.project.models.PlayerSprite;

public class KlausViewModel extends ViewModel {
    private GameDataModel gameData;
    private Player player;
    public KlausViewModel() {
        gameData = GameDataModel.getData();
        player = Player.getPlayer();
    }

    public void advanceLevel() {
        gameData.setCurrentLevel(3);
    }

    public GameDataModel getGameData() {
        return gameData;
    }

    public void updateMap(TiledMap map) {
        gameData.updateMap(map);
    }

    public void move(Player.Direction direction) {
        player.move(direction);
    }
    public float getX() {
        return player.getX();
    }
    public float getY() {
        return player.getY();
    }
    public void updatePosition(int newX, int newY) {
        player.updatePosition(newX, newY);
    }
    public void setPlayerSprite(Sprite sprite){
        player.setPlayerSprite(sprite);
    }
    public PlayerSprite getPlayerSprite(){
        return player.getSprite();
    }
    public Boolean exit(){
        return player.exit();
    }
}
