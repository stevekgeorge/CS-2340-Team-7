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
import com.badlogic.gdx.math.Rectangle;
import com.cs2340team7.project.models.BasePowerUpDecorator;
import com.cs2340team7.project.models.BuzzEnemy;
import com.cs2340team7.project.models.Enemy;
import com.cs2340team7.project.models.EnemyFactory;
import com.cs2340team7.project.models.GameDataModel;
import com.cs2340team7.project.models.HealthPowerUpDecorator;
import com.cs2340team7.project.models.Leaderboard;
import com.cs2340team7.project.models.Player;
import com.cs2340team7.project.models.RandomPowerUpDecorator;
import com.cs2340team7.project.models.ScorePowerUpDecorator;
import com.cs2340team7.project.models.TAEnemy;
import com.cs2340team7.project.viewmodels.GameOverViewModel;
import com.cs2340team7.project.viewmodels.IntroScreenViewModel;
import com.cs2340team7.project.viewmodels.TechGreenViewModel;
import com.cs2340team7.project.views.TechGreen;
import com.cs2340team7.project.viewmodels.PlayerSelectViewModel;
import com.cs2340team7.project.models.PurplePersian;

import junit.framework.TestCase;

import java.util.Calendar;
import java.util.Timer;

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
        assertEquals(model.getGameData().getMaxHealth(), 50);
        model.setDifficulty("Medium");
        assertEquals(model.getGameData().getMaxHealth(), 75);
        model.setDifficulty("Easy");
        assertEquals(model.getGameData().getMaxHealth(), 100);
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

    @Test
    public void testMoveUpOnPlayerMove(){
        Player player = Player.getPlayer();
        TechGreenViewModel model = new TechGreenViewModel();
        player.updatePosition(10,10);
        FileHandle fileHandle = Gdx.files.internal("generalgabe.png");
        Texture texture = new Texture(fileHandle);
        Sprite sprite = new Sprite(texture);
        sprite.setSize(160, 160);
        model.setPlayerSprite(sprite);

        int yStart = player.getY();
        model.move(Player.Direction.DOWN);
        int yLater = player.getY();
        assertTrue (yStart > yLater);



    }
    @Test
    public void testDontMoveOffScreen(){
        TechGreenViewModel model = new TechGreenViewModel();
        Player player = Player.getPlayer();
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
    public void advancesLevelUponExit() {
        TechGreen techGreen = new TechGreen(null);
        TechGreenViewModel model = new TechGreenViewModel();
        Player player = Player.getPlayer();
        FileHandle fileHandle = Gdx.files.internal("generalgabe.png");
        Texture texture = new Texture(fileHandle);
        Sprite sprite = new Sprite(texture);
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
    public void testCheckSpriteCoordinates() {
        TechGreen techGreen = new TechGreen(null);
        TechGreenViewModel model = new TechGreenViewModel();
        Player player = Player.getPlayer();
        FileHandle fileHandle = Gdx.files.internal("swordmastersid.png");
        Texture texture = new Texture(fileHandle);
        Sprite sprite = new Sprite(texture);
        sprite.setSize(300, 300);
        model.setPlayerSprite(sprite);
        player.updatePosition(1000, 1000);
        assertTrue(sprite.getX() == 1000 && sprite.getY() == 1000);
    }

    @Test
    public void testCharacterChosen() {
        Player player = Player.getPlayer();
        GameDataModel model = player.getGameData();
        PlayerSelectViewModel viewModel = new PlayerSelectViewModel();
        viewModel.setSelectedPlayer("Persian");
        assertEquals("Persian", model.getCharacter());
    }

    @Test
    public void testChosenMovementStrategy() {
        Player player = Player.getPlayer();
        GameDataModel model = player.getGameData();
        model.setCharacter("Persian");
        player.setMovementStrategy();
        assertTrue(player.getMovementStrategy() instanceof PurplePersian);
    }
    
//    @Test
//    public void testPlayerHealthDecreaseUponCollision() {
//        Player player = Player.getPlayer();
//        Enemy enemy = EnemyFactory.generateEnemy(100, 100,Enemy.EnemyType.SENIOR);
//        GameDataModel gameModel = player.getGameData();
//        TechGreenViewModel model = new TechGreenViewModel();
//        FileHandle fileHandle = Gdx.files.internal("generalgabe.png");
//        Texture texture = new Texture(fileHandle);
//        Sprite sprite = new Sprite(texture);
//        sprite.setPosition(50, 50);
//        sprite.setSize(50, 50);
//        model.setPlayerSprite(sprite);
//        Rectangle collisionRect = new Rectangle(100, 100, 50, 50);
//        enemy.updatePlayerPosition(collisionRect);
//        assertEquals(90, gameModel.getCurrentHealth() - 10);
//    }
//    @Test
//    public void healthDecreasesCorrectAmount() {
//        IntroScreenViewModel model = new IntroScreenViewModel();
//        Player player = Player.getPlayer();
//        GameDataModel gameData = player.getGameData();
//        Enemy enemy = EnemyFactory.generateEnemy(100, 100,Enemy.EnemyType.SENIOR);
//        model.setDifficulty("Easy");
//        TechGreenViewModel techModel = new TechGreenViewModel();
//        FileHandle fileHandle = Gdx.files.internal("generalgabe.png");
//        Texture texture = new Texture(fileHandle);
//        Sprite sprite = new Sprite(texture);
//        sprite.setPosition(50, 50);
//        sprite.setSize(50, 50);
//        techModel.setPlayerSprite(sprite);
//        Rectangle collisionRect = new Rectangle(100, 100, 50, 50);
//        enemy.updatePlayerPosition(collisionRect);
//        assertEquals(75,gameData.getCurrentHealth());
//        gameData.clear();
//        model.setDifficulty("Medium");
//        assertEquals(50, gameData.getCurrentHealth());
//        gameData.clear();
//        model.setDifficulty("Hard");
//        assertEquals(25, gameData.getCurrentHealth());
//    }

    @Test
    public void testFactoryMakeTA(){

        Enemy ta = EnemyFactory.generateEnemy(0,0, Enemy.EnemyType.TA);
        assertTrue(ta.getClass() == TAEnemy.class);
    }
    @Test
    public void testFactoryMakeBuzz(){

        Enemy buzz = EnemyFactory.generateEnemy(0,0, Enemy.EnemyType.BUZZ);
        assertTrue(buzz.getClass() == BuzzEnemy.class);
    }

    @Test
    public void testDamageTaken() {
        Player player = Player.getPlayer();

        player.updatePosition(0, 0);
        assertEquals(player.getGameData().getCurrentHealth(), player.getGameData().getMaxHealth());

        Enemy testEnemy = EnemyFactory.generateEnemy(0, 0, Enemy.EnemyType.TA);
        assertNotEquals(player.getGameData().getCurrentHealth(), player.getGameData().getMaxHealth());
    }

    @Test
    public void testDamageIsNotRepeated() throws InterruptedException {
        Player player = Player.getPlayer();

        player.updatePosition(0, 0);
        assertEquals(player.getGameData().getCurrentHealth(), player.getGameData().getMaxHealth());

        Enemy testEnemy = EnemyFactory.generateEnemy(0, 0, Enemy.EnemyType.TA);
        int currentHealth = player.getGameData().getCurrentHealth();
        assertNotEquals(currentHealth, player.getGameData().getMaxHealth());

        Thread.sleep(5000);

        assertEquals(currentHealth, player.getGameData().getCurrentHealth());
    }

    @Test
    public void testCheckEnemySpeed() {

        BuzzEnemy ta = (BuzzEnemy) EnemyFactory.generateEnemy(0,0, Enemy.EnemyType.TA);
        assertTrue(ta.getSpeed() == 5);

    }

    public void testCheckEnemyHealth() {

        Enemy ta = EnemyFactory.generateEnemy(0,0, Enemy.EnemyType.TA);
        Player player = Player.getPlayer();
        GameDataModel model = player.getGameData();
        int health;

        if (model.getDifficulty() == "Easy") {
            health = 25;
        } else if (model.getDifficulty() == "Medium") {
            health = 50;
        } else {
            health = 100;
        }
        assertTrue(ta.getHealth() == health);

    }

    @Test
    public void testCheckEnemyDamage() {

        Enemy enemy = EnemyFactory.generateEnemy(0,0, Enemy.EnemyType.TA);
        Player player = Player.getPlayer();
        GameDataModel model = player.getGameData();
        int damage;
        if (model.getDifficulty() == "Easy") {
            damage = 10;
        } else if (model.getDifficulty() == "Medium") {
            damage = 15;
        } else {
            damage = 25;
        }
        assertTrue(enemy.getHealth() == damage);
    }

    @Test
    public void checkEnemyMovement() {
        Enemy enemy = EnemyFactory.generateEnemy(0,0, Enemy.EnemyType.TA);
        Player player = Player.getPlayer();
        GameDataModel model = player.getGameData();
        player.setXAndY(0, 0);
        player.updatePosition(10, 10);
        assertEquals(enemy.getEnemySprite().getX(), 0);
        assertEquals(enemy.getEnemySprite().getY(), 0);
    }
    @Test
    public void scoreDownTime() throws InterruptedException {
        Player player = Player.getPlayer();
        player.startDecrease();
        int score1 = player.getScore();
        Timer timer = new Timer();
        timer.wait((long) 1000);
        int score2 = player.getScore();

        assertTrue(score1 != score2);

    }
    @Test
    public void testScoreUpdateDamageTaken() {
        Player player = Player.getPlayer();
        int score1 = player.getScore();

        player.updatePosition(0, 0);
        assertEquals(player.getGameData().getCurrentHealth(), player.getGameData().getMaxHealth());

        Enemy testEnemy = EnemyFactory.generateEnemy(0, 0, Enemy.EnemyType.TA);
        int score2 = player.getScore();
        assertNotEquals(score1, score2);
    }
    @Test
    public void testPowerUpValues() {
        HealthPowerUpDecorator health = new HealthPowerUpDecorator(0, 0);
        assertEquals(health.getHealthBonus(), 25);
        ScorePowerUpDecorator score = new ScorePowerUpDecorator(0, 0);
        assertEquals(score.getScoreBonus(), 15);
        RandomPowerUpDecorator random = new RandomPowerUpDecorator(0, 0);
        assertEquals(random.getHealthBonus(), 25);
        assertEquals(random.getScoreBonus(), 15);
    }

    @Test
    public void testDecoratorPattern() {
        GameDataModel model = GameDataModel.getData();
        model.setCurrentScore(50);
        model.setCurrentHealth(50);
        HealthPowerUpDecorator healthPowerUp = new HealthPowerUpDecorator(500, 500);
        healthPowerUp.updatePlayerPosition(new Rectangle(500, 500, 64, 64));
        assertEquals(75, model.getCurrentHealth());
        assertEquals(false, healthPowerUp.getPowerUpActive());
    }

    



}
