package com.cs2340team7.project.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leaderboard {
    private List<LeaderboardEntry> entries;
    private static Leaderboard leaderboard;

    private Leaderboard() {
        entries = new ArrayList<>();
    }

    public static Leaderboard getLeaderboard() {
        if (leaderboard == null) {
            leaderboard = new Leaderboard();
        }
        return leaderboard;
    }

    public void addEntry(String playerName, int score, String date) {
        entries.add(new LeaderboardEntry(playerName, score, date));
        Collections.sort(entries);
    }

    public List<LeaderboardEntry> getEntries() {
        return entries;
    }

    public void clear() {
        entries = new ArrayList<>();
    }

    public class LeaderboardEntry implements Comparable {
        private String playerName;
        private int score;
        private String date;

        public LeaderboardEntry(String playerName, int score, String date) {
            this.playerName = playerName;
            this.score = score;
            this.date = date;
        }

        public String getPlayerName() {
            return playerName;
        }

        public int getScore() {
            return score;
        }

        public String getDate() {
            return date;
        }

        @Override
        public int compareTo(Object o) {
            LeaderboardEntry other = (LeaderboardEntry) o;
            if (this.score > other.score) {
                return -1;
            } else if (this.score < other.score) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
