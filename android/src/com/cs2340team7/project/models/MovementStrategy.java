package com.cs2340team7.project.models;

/**
 * Interface that implements a move method. This is for applying
 * Strategy design pattern for player movement.
 */
public interface MovementStrategy {
    void move(Player.Direction direction);
}
