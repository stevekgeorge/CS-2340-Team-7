package com.cs2340team7.project.viewmodels;

import androidx.lifecycle.ViewModel;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.cs2340team7.project.models.GameDataModel;

public class SkilesViewModel extends ViewModel {
    private GameDataModel gameData;
    public SkilesViewModel() {
        gameData = GameDataModel.getData();
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
}
