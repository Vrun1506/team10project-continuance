package io.github.team10.escapefromuni;

public class ScoreManager {

    private int score;

    public ScoreManager() {
        this.score = 0;
    }

    public void increaseScore(int scoreIncrease)
    {
        score += scoreIncrease;
    }

    public int getScore() {
        return score;
    }

    public void reset() {
        score = 0;
    }

    public int CalculateFinalScore(int timeLeftSeconds)
    {
        int timeScore = 50 * timeLeftSeconds;
        return timeScore + score;
    }
}
