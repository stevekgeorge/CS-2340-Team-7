package com.cs2340team7;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.cs2340team7.project.models.GameDataModel;
import com.cs2340team7.project.models.Leaderboard;
import com.cs2340team7.project.models.Player;
import com.cs2340team7.project.models.PurplePersian;
import com.cs2340team7.project.viewmodels.GameOverViewModel;
import com.cs2340team7.project.viewmodels.IntroScreenViewModel;
import com.cs2340team7.project.viewmodels.TechGreenViewModel;
import com.cs2340team7.project.views.TechGreen;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;



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
        Player score = Player.getPlayer();

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
        board.clear();
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
        Player player = Player.getPlayer();
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
        dataModel.setCurrentHealth(500);
        int health = dataModel.getCurrentHealth();
        dataModel.setCurrentLevel(1);
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
        Player player = Player.getPlayer();
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

    @Test
    public void testCorrectTimeStamps() {
        Leaderboard board = Leaderboard.getLeaderboard();
        Calendar cal = Calendar.getInstance();
        board.addEntry("Steve", 5555, "8/15/1947 12:00 AM");
        board.addEntry("Steven", 8888, "8/16/1947 12:00 AM");
        board.addEntry("Stephen", 7777, "8/17/1947 12:00 AM");
        board.addEntry("Stevie", 9999, "8/18/1947 12:00 AM");
        board.addEntry("Stefan", 4444, "8/19/1947 12:00 AM");

        assertEquals(board.getEntries().get(0).getDate(), "8/18/1947 12:00 AM");
        assertEquals(board.getEntries().get(1).getDate(), "8/16/1947 12:00 AM");
        assertEquals(board.getEntries().get(2).getDate(), "8/17/1947 12:00 AM");
        assertEquals(board.getEntries().get(3).getDate(), "8/15/1947 12:00 AM");
        assertEquals(board.getEntries().get(4).getDate(), "8/19/1947 12:00 AM");
    }

    @Test
    public void testTopTenAttempts() {
        Leaderboard board = Leaderboard.getLeaderboard();
        board.clear();
        Calendar cal = Calendar.getInstance();
        board.addEntry("Steve", 9999, "8/15/1947 12:00 AM");
        board.addEntry("Steven", 8888, "8/16/1947 12:00 AM");
        board.addEntry("Stephen", 7777, "8/17/1947 12:00 AM");
        board.addEntry("Stevie", 6666, "8/18/1947 12:00 AM");
        board.addEntry("Stefan", 5555, "8/19/1947 12:00 AM");
        board.addEntry("Stephanus", 4444, "8/19/1947 12:00 AM");
        board.addEntry("Steffen", 3333, "8/19/1947 12:00 AM");
        board.addEntry("Stefanus", 2222, "8/19/1947 12:00 AM");
        board.addEntry("Stephan", 1111, "8/19/1947 12:00 AM");
        board.addEntry("Stepan", 999, "8/19/1947 12:00 AM");
        board.addEntry("Stefaan", 10000, "8/19/1947 12:00 AM");

        assertEquals(board.getEntries().get(0).getPlayerName(), "Stefaan");
        assertEquals(board.getEntries().get(1).getPlayerName(), "Steve");
        assertEquals(board.getEntries().get(2).getPlayerName(), "Steven");
        assertEquals(board.getEntries().get(3).getPlayerName(), "Stephen");
        assertEquals(board.getEntries().get(4).getPlayerName(), "Stevie");
        assertEquals(board.getEntries().get(5).getPlayerName(), "Stefan");
        assertEquals(board.getEntries().get(6).getPlayerName(), "Stephanus");
        assertEquals(board.getEntries().get(7).getPlayerName(), "Steffen");
        assertEquals(board.getEntries().get(8).getPlayerName(), "Stefanus");
        assertEquals(board.getEntries().get(9).getPlayerName(), "Stephan");
    }

//    @Test
//    public void testPlayerInitialPositionTechGreen() {
//        TechGreen techgreen = new TechGreen(null);
//        float expectedInitialX = Gdx.graphics.getWidth() / 2 - techgreen.getTexture().getWidth() / 2;
//        float expectedInitialY = Gdx.graphics.getHeight() / 2 + techgreen.getTexture().getHeight() / 2;
//
//        float actualX = techgreen.getSpriteX();
//        float actualY = techgreen.getSpriteY();
//        assertEquals(expectedInitialX, actualX);
//        assertEquals(expectedInitialY, actualY);
//    }

    @Test
    public void testUpdateMap() {
        Player player = Player.getPlayer();
        GameDataModel model = player.getGameData();
        TiledMap mockMap = new TiledMap();

        assertEquals(null, model.getCurrentMap());

        player.setMap(mockMap);

        assertEquals(mockMap, model.getCurrentMap());
    }

    @Test
    public void testAddSubscriber() {
        Player player = Player.getPlayer();
        GameDataModel model = player.getGameData();

        assertTrue(model.getMapSubscribers().contains(player));
    }

//    public void testPlayerMovement() {
//        TechGreen techGreen = new TechGreen(null);
//        techGreen.setSprite(techGreen.getSprite(), Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
//
//
//        // Check if player has moved left (X position decreased)
//        float expectedX = Gdx.graphics.getWidth() / 2 - techGreen.getTexture().getWidth() / 2 - (Gdx.graphics.getDeltaTime() * 1000);
//        float actualX = techGreen.getSpriteX();
//        assertEquals(expectedX, actualX, 0.1f);
//
//
//    }
    @Test
    public void testMoveUpOnPlayerMove(){
        Player player = Player.getPlayer();
        TechGreenViewModel model = new TechGreenViewModel();
        player.updatePosition(10,10);
        Sprite sprite = new Sprite();
        sprite.setSize(160, 160);
        model.setPlayerSprite(sprite);

        player.setMap(new TmxMapLoader().load("Klausmapp.tmx"));

        int yStart = player.getY();
        model.move(Player.Direction.DOWN);
        int yLater = player.getY();
        assertTrue (yStart > yLater);



    }
    @Test
    public void testDontMoveOffScreen(){
        TechGreenViewModel model = new TechGreenViewModel();
        Player player = Player.getPlayer();
        player.setMap(new TmxMapLoader().load("Klausmapp.tmx"));
        Sprite sprite = new Sprite();
        sprite.setSize(160, 160);
        model.setPlayerSprite(sprite);

        player.updatePosition(0,0);
        int yStart = player.getY();
        model.move(Player.Direction.DOWN);
        int yLater = player.getY();

        assertTrue( yStart == yLater);



    }

    @Test
    public void testAdvancesLevelUponExit() {
        TechGreen techGreen = new TechGreen(null);
        TechGreenViewModel model = new TechGreenViewModel();
        Player player = Player.getPlayer();
        player.setMap(new TmxMapLoader().load("Klausmapp.tmx"));
        Sprite sprite = new Sprite();
        sprite.setSize(160, 160);
        model.setPlayerSprite(sprite);

        int level = player.getGameData().getCurrentLevel();
        player.updatePosition(1000, 1000);
        if(player.exit()){
            model.advanceLevel();
        }
        int newLevel = player.getGameData().getCurrentLevel();

        assertTrue(level != newLevel);

    }
    @Test
    public void testMovementStrategyAssignedCorrect(){
        Player player = Player.getPlayer();
        GameDataModel model = player.getGameData();
        model.setCharacter("Persian");
        player.setMovementStrategy();
        assertTrue(((PurplePersian)player.getMovementStrategy()).getSpeed() == 1);

    }
    @Test public void testExitTrue(){
        Player player = Player.getPlayer();
        GameDataModel model = player.getGameData();
        Sprite sprite = new Sprite();
        player.setPlayerSprite(sprite);
        player.updatePosition(1000, 1000);
    }
}