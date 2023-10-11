package tests.java.com.cs2340team7;

import org.junit.Test;

import static org.junit.Assert.*;

import com.cs2340team7.project.models.GameDataModel;
import com.cs2340team7.project.models.Leaderboard;
import com.cs2340team7.project.models.PlayerScore;
import com.cs2340team7.project.viewmodels.GameOverViewModel;
import com.cs2340team7.project.views.GameOverScreen;

import java.util.Date;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class GTNuclearApocalypseUnitTests {
    @Test
    public void testPlayerScoreDecrease() {
        PlayerScore score = PlayerScore.GetPlayerScore();
        score.startDecrease();

        int start = score.GameData.CurrentScore;

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e){
            System.out.println("PlayerScore countdown test could not be completed.");
        }

        score.stopDecrease();

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

    }
}