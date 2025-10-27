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

    public Player(float speed, EscapeGame game)
    {
        this.speed = speed;
        this.game = game;
        playerTexture = new Texture("Player.png");
        playerSprite = new Sprite(playerTexture);
        playerSprite.setSize(1, 1);
    }

    public void update(float delta)
    {
        move(delta);
    }

    private void move(float delta) {
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

    public void draw() {
        playerSprite.draw(game.batch);
    }
}
