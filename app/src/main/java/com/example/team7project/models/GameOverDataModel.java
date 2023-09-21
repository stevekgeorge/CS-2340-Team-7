package com.example.team7project.models;

import java.util.Map;

public class GameOverDataModel {
    private int EndScore;
    private Map<String, Integer> HighScores;

    public GameOverDataModel(int EndScore, Map<String, Integer> HighScores) {
        this.EndScore = EndScore;
        this.HighScores = HighScores;
    }

    public int GetEndScore() {
        return EndScore;
    }

    public Map<String, Integer> GetHighScores() {
        return HighScores;
    }

    public void SetEndScore(int NewScore) {
        EndScore = NewScore;
    }

    public void AddHighScore(int NewScore, String Player) {
        HighScores.put(Player, NewScore);
    }
}
