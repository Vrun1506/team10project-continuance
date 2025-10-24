package io.github.team10.escapefromuni;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends ApplicationAdapter {
    public Texture playerTexture;
    public Sprite playerSprite;

    private int speed;
    private SpriteBatch batch;

    public void SetSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void create() {
        playerTexture = new Texture("Player.png");
        playerSprite = new Sprite(playerTexture);
        playerSprite.setSize(1, 1);
        batch = new SpriteBatch();
    }

    @Override
    public void render() {
        move();
        draw();
    }

    private void move() {
        float delta = Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            playerSprite.translateX(speed * delta);
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            playerSprite.translateX(-speed * delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            playerSprite.translateY(speed * delta);
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            playerSprite.translateY(-speed * delta);
        }
    }

    private void draw() {
        batch.begin();
        playerSprite.draw(batch);
        batch.end();
    }
}
