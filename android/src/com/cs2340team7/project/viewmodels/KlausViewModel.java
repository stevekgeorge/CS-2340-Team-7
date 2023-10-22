package com.cs2340team7.project.viewmodels;

import androidx.lifecycle.ViewModel;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.cs2340team7.project.models.GameDataModel;

public class KlausViewModel extends ViewModel {
    private GameDataModel gameData;
    public KlausViewModel() {
        gameData = GameDataModel.getData();
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
}
