package io.github.team10.escapefromuni;

/**
 * OLD CLASS
 * Timer class that handles the in-game timer.
 */
public class Timer {

    private float time;
    private float timeLeft;

    /**
     * Initialises a Timer object with a current time of 0 and a timeLeft of 300 seconds.
     */
    public Timer() {
        this.time = 0;
        this.timeLeft = 300; // start at 300 seconds
    }

    /**
     * Updates the timer.
     * @param delta the time that has elapsed since the last rendering.
     */
    public void update(float delta) {
        time += delta;
        timeLeft -= delta;
        if (timeLeft < 0) {
            timeLeft = 0;
        }
    }

    public float getTime() {
        return time;
    }

    public int getTimeSeconds() {
        return (int) time;
    }

    public int getTimeLeftSeconds() {
        return (int) timeLeft;
    }

    public boolean hasReached(float seconds) {
        return time >= seconds;
    }

    public boolean isFinished() {
        return timeLeft <= 0;
    }

    public void reset() {
        time = 0;
        timeLeft = 300;
    }
}
