package com.cs2340team7.project.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * class responsible for implementing the leaderboard that is displayed at the end of the game.
 */

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

    /**
     * adds an entry to the leaderboard.
     * @param playerName the name of the player that is to be added.
     * @param score the score that the player received in the game.
     * @param date the date of the day the game was played.
     */

    public void addEntry(String playerName, int score, String date) {
        entries.add(new LeaderboardEntry(playerName, score, date));
        Collections.sort(entries);
    }

    /**
     * displays dummy leaderboard if no entries have been added.
     * @return an instance of leaderboardEntry that is not connected to any player and contains a score of 0.
     */
    public LeaderboardEntry dummy() {
        return new LeaderboardEntry("Empty", 0, "");
    }

    public List<LeaderboardEntry> getEntries() {
        return entries;
    }

    /**
     * clears the leaderboard and prepares for a new round of the game.
     */
    public void clear() {
        entries = new ArrayList<>();
    }

    /**
     * represents an entry on the leaderboard. Each entry contains a player name, a score, and the date
     * the game was played.
     */
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

        /**
         * method to compare two entries on the board to each other.
         * @param o the object to be compared.
         * @return an int representing whether the object o was greater than, less than, or equal to
         * this instance of leaderboardEntry
         */
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
