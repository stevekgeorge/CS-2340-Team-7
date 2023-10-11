package com.cs2340team7;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import androidx.lifecycle.Observer;
import androidx.test.annotation.UiThreadTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.cs2340team7.project.models.GameDataModel;
import com.cs2340team7.project.models.Leaderboard;
import com.cs2340team7.project.models.PlayerScore;
<<<<<<< HEAD:android/src/tests/java/com/cs2340team7/GTNuclearApocalypseUnitTests.java
import com.cs2340team7.project.viewmodels.GameOverViewModel;
import com.cs2340team7.project.views.GameOverScreen;
=======
import com.cs2340team7.project.viewmodels.IntroScreenViewModel;
import com.cs2340team7.project.views.IntroScreen;

import junit.framework.TestCase;
>>>>>>> 82d16f90d9f527e2f58efa5ea43e236d1ef156ba:android/src/androidTest/java/com/cs2340team7/GTNuclearApocalypseUnitTests.java

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
        PlayerScore score = PlayerScore.GetPlayerScore();

        int start = score.GameData.CurrentScore - 1;

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
        board.AddEntry("Steve", 50, new Date());
        board.AddEntry("Gabe", 80, new Date());
        board.AddEntry("Ava", 60, new Date());
        board.AddEntry("Ethan", 10, new Date());
        board.AddEntry("Eric", 70, new Date());

        assertEquals(board.GetEntries().get(0).PlayerName, "Gabe");
        assertEquals(board.GetEntries().get(1).PlayerName, "Eric");
        assertEquals(board.GetEntries().get(2).PlayerName, "Ava");
        assertEquals(board.GetEntries().get(3).PlayerName, "Steve");
        assertEquals(board.GetEntries().get(4).PlayerName, "Ethan");
    }

    @Test
<<<<<<< HEAD:android/src/tests/java/com/cs2340team7/GTNuclearApocalypseUnitTests.java
    public void testPlayerScoreDecreaseNotNegative(){
        PlayerScore score = PlayerScore.GetPlayerScore();
        int i = score.getScore() +1;
        while (i > 0) {
            score.decreaseScore();
            i--;
        }
        assert score.getScore() > 0;
    }

    @Test
    public void leaderBoardPersistsAfterReset(){
       Leaderboard leaderBoard = Leaderboard.GetLeaderboard();
       leaderBoard.AddEntry("Steve", 50, new Date());

        GameOverViewModel gameOver = new GameOverViewModel();
        gameOver.Restart(new GameOverScreen());
        assertEquals(leaderBoard.GetEntries().get(0).PlayerName, "Steve");

=======
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
>>>>>>> 82d16f90d9f527e2f58efa5ea43e236d1ef156ba:android/src/androidTest/java/com/cs2340team7/GTNuclearApocalypseUnitTests.java
    }
}