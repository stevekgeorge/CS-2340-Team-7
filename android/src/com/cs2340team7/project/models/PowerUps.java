package com.cs2340team7.project.models;

/**
 * interface for PowerUps with an abstract method that applies powerups to the GameModel.
 * This interface is part of the decorator pattern implementation.
 */
public interface PowerUps {
    void apply(GameDataModel model);
}
