package io.github.team10.escapefromuni;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * OLD CLASS
 * Main menu screen displayed on game launch with menu options:
 *      Start Game, Tutorial, Settings, Achievements, Leaderboard, Exit.
 * NEW CHANGES:
 *      added new buttons for the achievements and leaderboard screens.
 */
public class MainMenu implements Screen {

    private final EscapeGame game;
    private Texture backgroundImage;
    private Texture buttonTexture;
    private BitmapFont font;
    private GlyphLayout layout;

    // buttons
    private Rectangle startButton;
    private Rectangle tutorialButton;
    private Rectangle settingsButton;
    private Rectangle exitButton;

    // NEW BUTTONS
    private Rectangle achievementButton;
    private Rectangle leaderboardButton;

    // hover states for buttons
    private boolean startHovered;
    private boolean tutorialHovered;
    private boolean settingsHovered;
    private boolean exitHovered;

    // NEW HOVER STATES
    private boolean achievementHovered;
    private boolean leaderboardHovered;

    /**
     * Initialises a new MainMenu object.
     * @param game the current EscapeGame object.
     */
    public MainMenu(EscapeGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        // backgrounds
        backgroundImage = new Texture(Gdx.files.internal("mainmenu_background.png"));
        buttonTexture = new Texture(Gdx.files.internal("ButtonBG.png"));

        font = game.font;
        layout = new GlyphLayout();

        // button sizes
        float buttonWidth = 400f;
        float buttonHeight = 80f;

        // alignment; To be Fixed
        float screenWidth = game.uiViewport.getWorldWidth();
        float screenHeight = game.uiViewport.getWorldHeight();
        float centerX = screenWidth / 2f;

        // main menu button positions
        startButton = new Rectangle(centerX - buttonWidth / 2f, screenHeight / 2f + 150f, buttonWidth, buttonHeight);
        tutorialButton = new Rectangle(centerX - buttonWidth / 2f, screenHeight / 2f + 50f, buttonWidth, buttonHeight);
        settingsButton = new Rectangle(centerX - buttonWidth / 2f, screenHeight / 2f - 50f, buttonWidth, buttonHeight);
        exitButton = new Rectangle(centerX - buttonWidth / 2f, screenHeight / 2f - 450f, buttonWidth, buttonHeight);

        // NEW BUTTON LOCATIONS
        achievementButton = new Rectangle(centerX - buttonWidth / 2f, screenHeight / 2f - 150f, buttonWidth, buttonHeight);
        leaderboardButton = new Rectangle(centerX - buttonWidth / 2f, screenHeight / 2f - 250f, buttonWidth, buttonHeight);

        //menu music
        AudioManager.getInstance().playMenuMusic();
    }

    // Draws the main menu UI
    public void display() {
        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);
        game.batch.begin();
        game.batch.draw(backgroundImage, 0, 0, game.viewport.getWorldWidth(), game.viewport.getWorldHeight());
        game.batch.end();

        game.uiViewport.apply();
        game.batch.setProjectionMatrix(game.uiCamera.combined);
        game.batch.begin();

        // draw all main menu achievement_texts
        drawButton(startButton, "Start Game", startHovered);
        drawButton(tutorialButton, "Tutorial", tutorialHovered);
        drawButton(settingsButton, "Settings", settingsHovered);
        drawButton(exitButton, "Exit", exitHovered);

        // NEW BUTTON RENDERING
        drawButton(achievementButton, "Achievements", achievementHovered);
        drawButton(leaderboardButton, "Leaderboard", leaderboardHovered);

        game.batch.end();
    }

    /**
     * Draws the given button to the screen.
     * @param button the button to draw to the screen.
     * @param text the text the button should have.
     * @param hovered whether the button is currently hovered or not.
     */
    private void drawButton(Rectangle button, String text, boolean hovered) {

        if (hovered) {
            game.batch.setColor(1f, 1f, 0.5f, 1f);
        } else {
            game.batch.setColor(Color.WHITE);
        }

        //button bg,size etc
        game.batch.draw(buttonTexture, button.x, button.y, button.width, button.height);
        game.batch.setColor(Color.WHITE);

        layout.setText(font, text);
        float textX = button.x + (button.width - layout.width) / 2f;
        float textY = button.y + (button.height + layout.height) / 2f;

        font.setColor(Color.WHITE);
        font.draw(game.batch, layout, textX, textY);
    }

    /**
     * Checks if the button being currently hovered over is clicked.
     * @param button the current button
     * @return true if clicked, false otherwise.
     */
    private boolean isButtonClicked(Rectangle button) {
        // click detector
        if (Gdx.input.justTouched()) {
            Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            game.uiViewport.unproject(touchPos);

            if (button.contains(touchPos.x, touchPos.y)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the current button is being hovered over.
     * @param button the current button.
     * @return true if hovered over, false otherwise.
     */
    private boolean isButtonHovered(Rectangle button) {
        // detect mouse hover in UI coordinates
        Vector2 mousePos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
        game.uiViewport.unproject(mousePos);
        return button.contains(mousePos.x, mousePos.y);
    }

    /**
     * Navigates the user to the main game screen.
     */
    public void onStartGame() {
        System.out.println("Starting game...");
        game.setScreen(new GameScreen(game));

        // NEW LOAD ACHIEVEMENTS
        game.achievementManager.loadAchievements();
        dispose();
    }

    /**
     * Navigates the user to the tutorial screen.
     */
    public void onTutorial() {
        System.out.println("Opening tutorial...");
        game.setScreen(new TutorialPage(game));
        dispose();
    }

    /**
     * Navigates the user to the settings screen.
     */
    public void onSettings() {
        System.out.println("Opening settings...");
        game.setScreen(new SettingsPage(game, this));
        dispose();
    }

    /**
     * Quits the game.
     */
    public void onExit() {
        System.out.println("Exiting game...");
        Gdx.app.exit();
    }

    // NEW
    /**
     * Navigates the user to the achievements screen.
     */
    public void onAchievements() {
        System.out.println("Going to achievement menu...");
        game.setScreen(new AchievementScreen(game));
        dispose();
    }

    // NEW
    /**
     * Navigates the user to the leaderboard screen.
     */
    public void onLeaderBoard() {
        System.out.println("Opening leaderboard...");
        game.setScreen(new LeaderboardPage(game,this));
        dispose();
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        startHovered = isButtonHovered(startButton);
        tutorialHovered = isButtonHovered(tutorialButton);
        settingsHovered = isButtonHovered(settingsButton);
        exitHovered = isButtonHovered(exitButton);

        // NEW BUTTONS
        achievementHovered = isButtonHovered(achievementButton);
        leaderboardHovered = isButtonHovered(leaderboardButton);


        if (isButtonClicked(startButton)) {
            onStartGame();
        } else if (isButtonClicked(tutorialButton)) {
            onTutorial();
        } else if (isButtonClicked(settingsButton)) {
            onSettings();
        } else if (isButtonClicked(exitButton)) {
            onExit();

            // NEW BUTTON CHECKS
        } else if (isButtonClicked(achievementButton)) {
            onAchievements();
        } else if (isButtonClicked(leaderboardButton)) {
            onLeaderBoard();
        }

        // draw everything
        display();
    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height, true);
        game.uiViewport.update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        backgroundImage.dispose();
        buttonTexture.dispose();
    }
}
