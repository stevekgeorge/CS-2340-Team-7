
import com.cs2340team7.project.models.GameDataModel;

import java.util.Timer;
import java.util.TimerTask;

public class PlayerScore {
    public Timer timer;
    public GameDataModel GameData;

    public PlayerScore() {
        GameData.CurrentScore = 20;
        startDecrease();
    }
    public int getScore() {
        return GameData.CurrentScore;
    }
    public void startDecrease() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                decreaseScore();
            }
        }, 0, 3000);

    }
    public void decreaseScore() {
        if (GameData.CurrentScore > 0) {
            GameData.CurrentScore--;
        } else {
            timer.cancel();
        }
    }
}
