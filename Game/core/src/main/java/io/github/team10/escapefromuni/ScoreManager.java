package io.github.team10.escapefromuni;

/**
 * OLD CLASS
 * ScoreManager class that handles the users score.
 */
public class ScoreManager {

    private int score;

    public ScoreManager() {
        this.score = 0;
    }

    /**
     * Increases the score by a given amount
     * @param scoreIncrease the amount to increase the score by.
     */
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

    /**
     * Calculates the final score.
     * @param timeLeftSeconds the time left in seconds.
     * @return the final score.
     */
    public int CalculateFinalScore(int timeLeftSeconds) {
        int timeScore = 50 * timeLeftSeconds;
        return timeScore + score;
    }
}
