package com.cs2340team7.project.viewmodels;

import androidx.lifecycle.ViewModel;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.cs2340team7.project.models.GameDataModel;
import com.cs2340team7.project.models.Player;

public class SkilesViewModel extends ViewModel {
    private GameDataModel gameData;
    private Player player;
    public SkilesViewModel() {
        gameData = GameDataModel.getData();
        player = Player.getPlayer();

    }

    public void advanceLevel() {
        gameData.setCurrentLevel(-1);
    }

    public void setScore(int score) {
        gameData.setCurrentScore(score);
    }

    public GameDataModel getGameData() {
        return gameData;
    }

    public String getPlayerName() {
        return gameData.getPlayerName();
    }

    public int getScore() {
        return gameData.getCurrentScore();
    }

    public String getTime() {
        return gameData.getStartTime();
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
