package com.example.team7project.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.team7project.BR;

import java.util.Map;

public class GameOverDataModel extends BaseObservable {
    private int EndScore;
    private Map<String, Integer> HighScores;
    @Bindable
    public String PlayerName;

    public GameOverDataModel(int EndScore, Map<String, Integer> HighScores, String PlayerName) {
        this.EndScore = EndScore;
        this.HighScores = HighScores;
        this.PlayerName = PlayerName;
    }

    public int GetEndScore() {
        return EndScore;
    }

    public Map<String, Integer> GetHighScores() {
        return HighScores;
    }

    public String GetPlayerName() {
        return PlayerName;
    }

    public void SetEndScore(int NewScore) {
        EndScore = NewScore;
    }

    public void AddHighScore(int NewScore, String Player) {
        HighScores.put(Player, NewScore);
    }

    public void SetPlayerName(String NewName) {
        this.PlayerName = NewName;
        notifyPropertyChanged(BR.PlayerName);
    }
}
