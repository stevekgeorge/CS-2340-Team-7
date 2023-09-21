package com.example.team7project.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import java.util.Map;

public class GameOverViewModel extends BaseObservable {
    @Bindable
    private String PlayerName;

    @Bindable
    private Integer PlayerScore;

    @Bindable
    private Map<String, Integer> HighScores;

    @Bindable
    public String getPlayerName() {
        return PlayerName;
    }

    @Bindable
    public Integer getPlayerScore() {
        return PlayerScore;
    }

    @Bindable
    public Map<String, Integer> getHighScores() {
        return HighScores;
    }
}
