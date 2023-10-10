
import java.util.Timer;
import java.util.TimerTask;

public class PlayerScore {
    public int score;
    public Timer timer;

    public PlayerScore(int score) {
        this.score = score;
        startDecrease();
    }
    public int getScore() {
        return score;
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
        if (score > 0) {
            score--;
        } else {
            timer.cancel();
        }
    }
}
