package io.github.team10.escapefromuni;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.OnscreenKeyboardType;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.ScreenUtils;

public class UsernameInputScreen implements Screen {

    private final EscapeGame game;
    private final BitmapFont font;
    private final Texture screen;
    private final Timer timer;
    private final ScoreManager scoreManager;

    public String nameText = "";
    public String displayText = "";

    /**
     * Constructs a new UsernameInputScreen.
     * @param game  The main game instance.
     */
    public UsernameInputScreen(final EscapeGame game, Timer timer, ScoreManager scoreManager) {
        this.game = game;
        this.timer = timer;
        this.scoreManager = scoreManager;

        this.font = game.font;
        this.screen = new Texture("WinScreen.png");
    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(Color.BLACK);
        game.uiViewport.apply();
        game.batch.setProjectionMatrix(game.uiCamera.combined);
        game.batch.begin();

        game.batch.draw(screen, 0, 0, game.uiViewport.getWorldWidth(), game.uiViewport.getWorldHeight());
        String text = "Enter your username below.";
        String displayText = nameText;

        game.font.setColor(Color.BLACK);
        GlyphLayout layout = new GlyphLayout();

        float uiWidth = game.uiViewport.getWorldWidth();
        float uiHeight = game.uiViewport.getWorldHeight();

        // Draw text
        layout.setText(game.font, text);
        float timeX = (uiWidth - layout.width) / 2f;
        float timeY = uiHeight * 0.35f;
        game.font.draw(game.batch, layout, timeX, timeY);

        // Draw username display text
        layout.setText(game.font, displayText);
        timeX = (uiWidth - layout.width) / 2f;
        timeY = uiHeight * 0.30f;
        game.font.draw(game.batch, layout, timeX, timeY);

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        screen.dispose();
    }

    @Override
    public void show() {

        //Capture key inputs and append to the name text accordingly
        Gdx.input.setInputProcessor(new InputAdapter () {
        @Override
        public boolean keyTyped(char character) {
            try {

                if (character == '\b') {
                    nameText = "";
                    return true;
                }

                if (character == '\n') {
                    game.setScreen(new GameOverScreen(game, true, timer, scoreManager, nameText));
                    return true;
                }

                if (nameText.length() < 3) {
                nameText = (nameText+character);
                }

            } catch (Exception e) {
                System.err.println("Couldn't interpet username input!");
                return false;
            }

            return true;
        }
        });
    }
    
}
