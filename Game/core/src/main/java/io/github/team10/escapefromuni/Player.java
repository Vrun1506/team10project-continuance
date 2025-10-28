package io.github.team10.escapefromuni;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player {
    public Texture playerTexture;
    public Sprite playerSprite;
    public float speed;
    public EscapeGame game;

    private final float EDGE_LIMIT = 1f;

    public Player(float speed, float playerWidth, float playerHeight, EscapeGame game)
    {
        this.speed = speed;
        this.game = game;
        playerTexture = new Texture("Player.png");
        playerSprite = new Sprite(playerTexture);
        playerSprite.setSize(playerWidth, playerHeight);

        float centerX = game.viewport.getWorldWidth() / 2f;
        float centerY = game.viewport.getWorldHeight() / 2f;
        playerSprite.setCenter(centerX, centerY);
    }

    public void update(float delta)
    {
        move(delta);
    }

    private void move(float delta) {
        float worldWidth = game.viewport.getWorldWidth();
        float worldHeight = game.viewport.getWorldHeight();
        float halfWidth = playerSprite.getWidth() / 2f;
        float halfHeight = playerSprite.getHeight() / 2f;
        float playerCenterX = playerSprite.getX() + halfWidth;
        float playerCenterY = playerSprite.getY() + halfHeight;

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (playerCenterX < worldWidth - EDGE_LIMIT) {
                playerSprite.translateX(speed * delta);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (playerCenterX > EDGE_LIMIT) {
                playerSprite.translateX(-speed * delta);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if (playerCenterY < worldHeight - EDGE_LIMIT) {
                playerSprite.translateY(speed * delta);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if (playerCenterY > EDGE_LIMIT) {
                playerSprite.translateY(-speed * delta);
            }
        }
    }

    public void draw() {
        playerSprite.draw(game.batch);
    }

    public boolean checkCollision(Sprite objectSprite)
    {
        return playerSprite.getBoundingRectangle().overlaps(objectSprite.getBoundingRectangle());
    }

    public void dispose()
    {
        playerTexture.dispose();
    }

    public void setPosition(float x, float y)
    {
        playerSprite.setPosition(x, y);
    }

    public void setCenter(float x, float y)
    {
        playerSprite.setCenter(x, y);
    }
}
