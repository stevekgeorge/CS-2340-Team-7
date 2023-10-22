package com.cs2340team7.project.viewmodels;

import androidx.lifecycle.ViewModel;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.cs2340team7.project.models.GameDataModel;

public class TechGreenViewModel extends ViewModel {
    private GameDataModel gameData;
    public TechGreenViewModel() {
        gameData = GameDataModel.getData();
    }
    public void advanceLevel() {
        gameData.setCurrentLevel(2);
    }
    public GameDataModel getGameData() {
        return gameData;
    }
    public void updateMap(TiledMap map) {
        gameData.updateMap(map);
    }
}
