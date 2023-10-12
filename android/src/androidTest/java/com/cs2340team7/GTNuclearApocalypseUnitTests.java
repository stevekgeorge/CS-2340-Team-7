package com.cs2340team7;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.cs2340team7.project.models.GameDataModel;
import com.cs2340team7.project.models.Leaderboard;
import com.cs2340team7.project.models.Player;
import com.cs2340team7.project.viewmodels.GameOverViewModel;
import com.cs2340team7.project.viewmodels.IntroScreenViewModel;

import junit.framework.TestCase;

import java.util.Calendar;
import java.util.Date;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class GTNuclearApocalypseUnitTests extends TestCase {
    @Test
    public void testPlayerScoreDecrease() {
        Player score = Player.GetPlayerScore();

        int start = score.GameData.CurrentScore;

        score.startDecrease();
        try {
            Thread.sleep(6100);
        } catch (InterruptedException e) {
            System.out.println("PlayerScore countdown test could not be completed.");
        }

        int end = score.GameData.CurrentScore;

        if (start >= 3) {
            assertEquals(end, start - 2);
        } else {
            assertEquals(end, 0);
        }
    }

    @Test
    public void testLeaderboardDecreasingOrder() {
        Leaderboard board = Leaderboard.GetLeaderboard();
        Calendar cal = Calendar.getInstance();
        board.AddEntry("Steve", 50, "1/1/1970 12:00 AM");
        board.AddEntry("Gabe", 80, "1/1/1970 12:00 AM");
        board.AddEntry("Ava", 60, "1/1/1970 12:00 AM");
        board.AddEntry("Ethan", 10, "1/1/1970 12:00 AM");
        board.AddEntry("Eric", 70, "1/1/1970 12:00 AM");

        assertEquals(board.GetEntries().get(0).PlayerName, "Gabe");
        assertEquals(board.GetEntries().get(1).PlayerName, "Eric");
        assertEquals(board.GetEntries().get(2).PlayerName, "Ava");
        assertEquals(board.GetEntries().get(3).PlayerName, "Steve");
        assertEquals(board.GetEntries().get(4).PlayerName, "Ethan");
    }

    @Test
    public void testNullWhitespaceName() {
        IntroScreenViewModel model = new IntroScreenViewModel();
        model.SetPlayerName("Ethan");
        assertEquals(model.GameData.PlayerName, "Ethan");
        model.SetPlayerName("");
        assertEquals(model.GameData.PlayerName, "Ethan");
    }

    @Test
    public void testHealth() {
        IntroScreenViewModel model = new IntroScreenViewModel();
        model.SetDifficulty("Hard");
        assertEquals(model.GameData.MaxHealth, 100);
        model.SetDifficulty("Medium");
        assertEquals(model.GameData.MaxHealth, 250);
        model.SetDifficulty("Easy");
        assertEquals(model.GameData.MaxHealth, 500);
    }

    @Test
    public void testLeaderboardUponRestart() {
        Player player = Player.GetPlayerScore();
        player.startDecrease();

        Leaderboard.GetLeaderboard().Clear();
        Leaderboard.GetLeaderboard().AddEntry("Ethan", 2003, "1/1/1970 12:00 AM");
        Leaderboard.GetLeaderboard().AddEntry("Sneha", 2002, "1/1/1970 12:00 AM");
        Leaderboard.GetLeaderboard().AddEntry("Rishab", 2000, "1/1/1970 12:00 AM");
        Leaderboard.GetLeaderboard().AddEntry("Tish",  1998, "1/1/1970 12:00 AM");

        GameOverViewModel model = new GameOverViewModel();
        assertEquals(Leaderboard.GetLeaderboard().GetEntries().size(), 4);
        assertNotEquals(GameDataModel.getData().CurrentScore, 0);

        model.Restart();

        assertEquals(Leaderboard.GetLeaderboard().GetEntries().size(), 4);
        assertEquals(GameDataModel.getData().CurrentScore, 20);
    }

    @Test
    public void testCharacterIsConsistentAcrossLevels() {
        GameDataModel dataModel = GameDataModel.getData();
        String Character = dataModel.Character;
        dataModel.CurrentLevel = 1;
        assertEquals(dataModel.Character, Character);
        dataModel.CurrentLevel = 2;
        assertEquals(dataModel.Character, Character);
        dataModel.CurrentLevel = 3;
        assertEquals(dataModel.Character, Character);
        dataModel.CurrentLevel = -1;
        assertEquals(dataModel.Character, Character);
    }

    @Test
    public void testHealthIsConsistentAcrossLevels() {
        GameDataModel dataModel = GameDataModel.getData();
        String Character = dataModel.Character;
        dataModel.CurrentLevel = 1;
        assertEquals(dataModel.Character, Character);
        dataModel.CurrentLevel = 2;
        assertEquals(dataModel.Character, Character);
        dataModel.CurrentLevel = 3;
        assertEquals(dataModel.Character, Character);
        dataModel.CurrentLevel = -1;
        assertEquals(dataModel.Character, Character);
    }

    @Test
    public void testPlayerScoreResets() {
        Player player = Player.GetPlayerScore();
        player.stopDecrease();
        int start = player.GameData.CurrentScore;
        player.startDecrease();

        try {
            Thread.sleep(3100);
        } catch (InterruptedException e) {
            System.out.println("PlayerScore reset test could not be completed.");
        }

        assertEquals(player.GameData.CurrentScore, start - 1);

        GameOverViewModel model = new GameOverViewModel();
        model.Restart();

        assertEquals(player.GameData.CurrentScore, start);
    }

    @Test
    public void testCorrectTimeStamps() {
        Leaderboard board = Leaderboard.GetLeaderboard();
        Calendar cal = Calendar.getInstance();
        board.AddEntry("Steve", 5555, "8/15/1947 12:00 AM");
        board.AddEntry("Steven", 8888, "8/16/1947 12:00 AM");
        board.AddEntry("Stephen", 7777, "8/17/1947 12:00 AM");
        board.AddEntry("Stevie", 9999, "8/18/1947 12:00 AM");
        board.AddEntry("Stefan", 4444, "8/19/1947 12:00 AM");

        assertEquals(board.GetEntries().get(0).Date, "8/18/1947 12:00 AM");
        assertEquals(board.GetEntries().get(1).Date, "8/16/1947 12:00 AM");
        assertEquals(board.GetEntries().get(2).Date, "8/17/1947 12:00 AM");
        assertEquals(board.GetEntries().get(3).Date, "8/15/1947 12:00 AM");
        assertEquals(board.GetEntries().get(4).Date, "8/19/1947 12:00 AM");
    }

    @Test
    public void testTopTenAttempts() {
        Leaderboard board = Leaderboard.GetLeaderboard();
        Calendar cal = Calendar.getInstance();
        board.AddEntry("Steve", 9999, "8/15/1947 12:00 AM");
        board.AddEntry("Steven", 8888, "8/16/1947 12:00 AM");
        board.AddEntry("Stephen", 7777, "8/17/1947 12:00 AM");
        board.AddEntry("Stevie", 6666, "8/18/1947 12:00 AM");
        board.AddEntry("Stefan", 5555, "8/19/1947 12:00 AM");
        board.AddEntry("Stephanus", 4444, "8/19/1947 12:00 AM");
        board.AddEntry("Steffen", 3333, "8/19/1947 12:00 AM");
        board.AddEntry("Stefanus", 2222, "8/19/1947 12:00 AM");
        board.AddEntry("Stephan", 1111, "8/19/1947 12:00 AM");
        board.AddEntry("Stepan", 999, "8/19/1947 12:00 AM");
        board.AddEntry("Stefaan", 10000, "8/19/1947 12:00 AM");

        assertEquals(board.GetEntries().get(0).PlayerName, "Stefaan");


        assertEquals(board.GetEntries().get(1).PlayerName, "Steve");
        assertEquals(board.GetEntries().get(2).PlayerName, "Steven");
        assertEquals(board.GetEntries().get(3).PlayerName, "Stephen");
        assertEquals(board.GetEntries().get(4).PlayerName, "Stevie");
        assertEquals(board.GetEntries().get(5).PlayerName, "Stefan");
        assertEquals(board.GetEntries().get(6).PlayerName, "Stephanus");
        assertEquals(board.GetEntries().get(7).PlayerName, "Steffen");
        assertEquals(board.GetEntries().get(8).PlayerName, "Stefanus");
        assertEquals(board.GetEntries().get(9).PlayerName, "Stephan");
    }
}