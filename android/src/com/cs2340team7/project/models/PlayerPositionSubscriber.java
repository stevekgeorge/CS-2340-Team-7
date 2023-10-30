package com.cs2340team7.project.models;

import java.security.PublicKey;

public interface PlayerPositionSubscriber {
    public void updatePlayerPosition(int location_x, int location_y, int size_x, int size_y);
}
