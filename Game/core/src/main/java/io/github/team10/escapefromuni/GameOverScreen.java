package io.github.team10.escapefromuni;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.ScreenUtils;
/**
 * OLD CLASS
 * Represents the Game Over screen, shown when the player either wins or loses.
 * Displays the appropriate background, score, and time information.
 * The player can return to the main menu by pressing the ESC key.
 */
public class GameOverScreen implements Screen {

    private final EscapeGame game;
    private final boolean isWon;
    private final Timer timer;
    private final ScoreManager scoreManager;

    private final BitmapFont font;
    private final Texture winScreen;
    private final Texture loseScreen;

    // NEW
    private final String name;

    private int finalScore;

    /**
     * Constructs a new GameOverScreen.
     * @param game  The main game instance.
     * @param isWon Whether the player has won or lost.
     * @param timer The timer used to track playtime.
     * @param scoreManager  The score manager which calculates the final score.
     */
    public GameOverScreen(final EscapeGame game, boolean isWon, Timer timer, ScoreManager scoreManager, String nameString) {
        this.game = game;
        this.isWon = isWon;
        this.timer = timer;
        this.scoreManager = scoreManager;
        this.name = nameString;

        this.font = game.font;
        this.winScreen = new Texture("WinScreen.png");
        this.loseScreen = new Texture("LoseScreen.png");

        // NEW SAVES ACHIEVEMENTS
        game.achievementManager.saveAchievements();
    }

    @Override
    public void render(float delta) {
        // Return to main menu if ESC pressed.
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new MainMenu(game));
            dispose();
            return;
        }

        ScreenUtils.clear(Color.BLACK);
        game.uiViewport.apply();
        game.batch.setProjectionMatrix(game.uiCamera.combined);
        game.batch.begin();

        if (isWon){ renderWinScreen(); }
        else { renderLoseScreen(); }

        game.batch.end();
    }

    /**
     * Renders the winning screen, showing the win background, final score and time elapsed.
     */
    private void renderWinScreen(){
        game.batch.draw(winScreen, 0, 0, game.uiViewport.getWorldWidth(), game.uiViewport.getWorldHeight());
        String timeText = "Time Elapsed: " + timer.getTimeSeconds();
        int finalScore = scoreManager.CalculateFinalScore(timer.getTimeLeftSeconds());
        String scoreText = "Score: " + finalScore;

        game.font.setColor(Color.BLACK);
        GlyphLayout layout = new GlyphLayout();

        float uiWidth = game.uiViewport.getWorldWidth();
        float uiHeight = game.uiViewport.getWorldHeight();

        // Draw time elapsed text
        layout.setText(game.font, timeText);
        float timeX = (uiWidth - layout.width) / 2f;
        float timeY = uiHeight * 0.35f;
        game.font.draw(game.batch, layout, timeX, timeY);

        // Draw score text
        layout.setText(game.font, scoreText);
        float scoreX = (uiWidth - layout.width) / 2f;
        float scoreY = uiHeight * 0.3f;
        font.draw(game.batch, scoreText, scoreX, scoreY);
    }

    /**
     * Renders the losing screen, showing the loss background.
     * Doesn't display the score or time.
     */
    private void renderLoseScreen(){
        game.batch.draw(loseScreen, 0, 0, game.uiViewport.getWorldWidth(), game.uiViewport.getWorldHeight());
    }

    // NEW FOR SCORE SAVING
    @Override
    public void show() {
        finalScore = scoreManager.CalculateFinalScore(timer.getTimeLeftSeconds());
        writeScores(finalScore);
        game.achievementManager.saveAchievements();
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    /**
     * Dispose of textures used by the screen.
     */
    @Override public void dispose() {
        winScreen.dispose();
        loseScreen.dispose();
    }

    // NEW function for leaderboard implementation
    /**
     * Writes the player score to file - 'leaderboard.txt'.
     * Creates 'leaderboard.txt' if one isn't found. This ends up in the same directory as the jar file.
     * @param newScore the new score to write.
     */
    public void writeScores(int newScore) {

        try {

            File scoreFile = new File("leaderboard.txt");
            scoreFile.createNewFile(); //only creates the file if it had not existed beforehand
            BufferedReader reader = new BufferedReader(new FileReader(scoreFile));

            // contains data from old scoreboard to be used in comparison
            ArrayList<String> oldRecords = new ArrayList<String>();
            ArrayList<Integer> oldScores = new ArrayList<Integer>();

            for (int i=0; i<5; i++) {
                // entire record keeping CSV format
                String temp = reader.readLine();

                if (temp == null){
                    break;
                }
                oldRecords.add(temp);

                // grabs just the score
                try {
                    Integer tempInt = Integer.parseInt(temp.split(",")[1]);
                    oldScores.add(tempInt);

                } catch (Exception e) {
                    System.err.println("Couldn't interpret scores file for writing!");
                    e.printStackTrace();
                }
            }

            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(scoreFile));

            // check if there are any records in the leaderboard file, and perform the necessary countermeasure
            if (oldScores.isEmpty()) {
                writer.write((name+","+newScore+"\n"));
            } else {

            boolean placed = false;
            for (int i=0; i<oldScores.size(); i++) {
                if (oldScores.get(i) <= newScore && !placed) {
                    writer.write((name+","+newScore+"\n"));
                    writer.write((oldRecords.get(i)+"\n"));
                    placed = true;
                } else {
                    writer.write((oldRecords.get(i)+"\n"));
                }
            }

            // if the score never beats existing ones, check if there is space for it anyway.
            if (!placed && oldScores.size() < 5) {
                writer.write((name+","+newScore+"\n"));
            }

            }

            System.out.println(oldScores);
            System.out.println(oldRecords);

            writer.close();

        } catch (IOException e) {
            System.err.println("Failed to overwrite scores!");
            e.printStackTrace();
        }
    }
}
