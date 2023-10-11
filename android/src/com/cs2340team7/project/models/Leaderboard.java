package com.cs2340team7.project.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Leaderboard {
    private List<LeaderboardEntry> Entries;
    private static Leaderboard Leaderboard;

    private Leaderboard() {
        Entries = new ArrayList<>();
    }

    public static Leaderboard GetLeaderboard() {
        if (Leaderboard == null) {
            Leaderboard = new Leaderboard();
        }
        return Leaderboard;
    }

    public void AddEntry(String PlayerName, int Score, Date Date) {
        Entries.add(new LeaderboardEntry(PlayerName, Score, Date));
    }

    public List<LeaderboardEntry> GetEntries() {
        return Entries;
    }

    public class LeaderboardEntry implements Comparable {
        public String PlayerName;
        public int Score;
        public Date Date;

        public LeaderboardEntry(String PlayerName, int Score, Date Date) {
            this.PlayerName = PlayerName;
            this.Score = Score;
            this.Date = Date;
        }

        @Override
        public int compareTo(Object o) {
            LeaderboardEntry other = (LeaderboardEntry) o;
            if (this.Score > other.Score) {
                return -1;
            } else if (this.Score < other.Score) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
