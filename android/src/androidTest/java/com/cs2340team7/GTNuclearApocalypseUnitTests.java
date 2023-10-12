package com.cs2340team7;

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

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class GTNuclearApocalypseUnitTests extends TestCase {
    @Test
    public void testPlayerScoreDecrease() {
        Player score = Player.getPlayerScore();

        int start = score.getGameData().getCurrentScore();

        score.startDecrease();
        try {
            Thread.sleep(6100);
        } catch (InterruptedException e) {
            System.out.println("PlayerScore countdown test could not be completed.");
        }

        int end = score.getGameData().getCurrentScore();

        if (start >= 3) {
            assertEquals(end, start - 2);
        } else {
            assertEquals(end, 0);
        }
    }

    @Test
    public void testLeaderboardDecreasingOrder() {
        Leaderboard board = Leaderboard.getLeaderboard();
        Calendar cal = Calendar.getInstance();
        board.addEntry("Steve", 50, "1/1/1970 12:00 AM");
        board.addEntry("Gabe", 80, "1/1/1970 12:00 AM");
        board.addEntry("Ava", 60, "1/1/1970 12:00 AM");
        board.addEntry("Ethan", 10, "1/1/1970 12:00 AM");
        board.addEntry("Eric", 70, "1/1/1970 12:00 AM");

        assertEquals(board.getEntries().get(0).getPlayerName(), "Gabe");
        assertEquals(board.getEntries().get(1).getPlayerName(), "Eric");
        assertEquals(board.getEntries().get(2).getPlayerName(), "Ava");
        assertEquals(board.getEntries().get(3).getPlayerName(), "Steve");
        assertEquals(board.getEntries().get(4).getPlayerName(), "Ethan");
    }

    @Test
    public void testNullWhitespaceName() {
        IntroScreenViewModel model = new IntroScreenViewModel();
        model.setPlayerName("Ethan");
        assertEquals(model.getGameData().getPlayerName(), "Ethan");
        model.setPlayerName("");
        assertEquals(model.getGameData().getPlayerName(), "Ethan");
    }

    @Test
    public void testHealth() {
        IntroScreenViewModel model = new IntroScreenViewModel();
        model.setDifficulty("Hard");
        assertEquals(model.getGameData().getMaxHealth(), 100);
        model.setDifficulty("Medium");
        assertEquals(model.getGameData().getMaxHealth(), 250);
        model.setDifficulty("Easy");
        assertEquals(model.getGameData().getMaxHealth(), 500);
    }

    @Test
    public void testLeaderboardUponRestart() {
        Player player = Player.getPlayerScore();
        player.startDecrease();

        Leaderboard.getLeaderboard().clear();
        Leaderboard.getLeaderboard().addEntry("Ethan", 2003, "1/1/1970 12:00 AM");
        Leaderboard.getLeaderboard().addEntry("Sneha", 2002, "1/1/1970 12:00 AM");
        Leaderboard.getLeaderboard().addEntry("Rishab", 2000, "1/1/1970 12:00 AM");
        Leaderboard.getLeaderboard().addEntry("Tish",  1998, "1/1/1970 12:00 AM");

        GameOverViewModel model = new GameOverViewModel();
        assertEquals(Leaderboard.getLeaderboard().getEntries().size(), 4);
        assertNotEquals(GameDataModel.getData().getCurrentScore(), 0);

        model.restart();

        assertEquals(Leaderboard.getLeaderboard().getEntries().size(), 4);
        assertEquals(GameDataModel.getData().getCurrentScore(), 20);
    }

    @Test
    public void testCharacterIsConsistentAcrossLevels() {
        GameDataModel dataModel = GameDataModel.getData();
        String Character = dataModel.getCharacter();
        dataModel.setCurrentLevel(1);
        assertEquals(dataModel.getCharacter(), Character);
        dataModel.setCurrentLevel(2);
        assertEquals(dataModel.getCharacter(), Character);
        dataModel.setCurrentLevel(3);
        assertEquals(dataModel.getCharacter(), Character);
        dataModel.setCurrentLevel(-1);
        assertEquals(dataModel.getCharacter(), Character);
    }

    @Test
    public void testHealthIsConsistentAcrossLevels() {
        GameDataModel dataModel = GameDataModel.getData();
        int health = dataModel.getCurrentHealth();
        dataModel.setCurrentHealth(1);
        assertEquals(dataModel.getCurrentHealth(), health);
        dataModel.setCurrentLevel(2);
        assertEquals(dataModel.getCurrentHealth(), health);
        dataModel.setCurrentLevel(3);
        assertEquals(dataModel.getCurrentHealth(), health);
        dataModel.setCurrentLevel(-1);
        assertEquals(dataModel.getCurrentHealth(), health);
    }

    @Test
    public void testPlayerScoreResets() {
        Player player = Player.getPlayerScore();
        player.stopDecrease();
        int start = player.getGameData().getCurrentScore();
        player.startDecrease();

        try {
            Thread.sleep(3100);
        } catch (InterruptedException e) {
            System.out.println("PlayerScore reset test could not be completed.");
        }

        assertEquals(player.getGameData().getCurrentScore(), start - 1);

        GameOverViewModel model = new GameOverViewModel();
        model.restart();

        assertEquals(player.getGameData().getCurrentScore(), start);
    }
}