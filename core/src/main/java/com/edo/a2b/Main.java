package com.edo.a2b;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main implements ApplicationListener {
    Texture square;
    Texture background;
    Sprite player;
    SpriteBatch spriteBatch;
    FitViewport fitViewport;

    @Override
    public void create() {
        square = new Texture("square.png");
        background = new Texture("background.png");
        spriteBatch = new SpriteBatch();

        // Create the player sprite
        player = new Sprite(square);
        player.setSize(1, 1);

        // Split the screen into 8 x 5 "meters"/"pixels"
        fitViewport = new FitViewport(30, 15);
    }

    @Override
    public void resize(int width, int height) {
        // Center the camera
        fitViewport.update(width, height, true);
    }

    @Override
    public void render() {
        input();
        draw();
    }

    public void input() {
        float speed = 10f;
        float delta = Gdx.graphics.getDeltaTime();

        // Move the player to the right
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.translateX(speed * delta);
        }
        // Move the player to the right
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.translateX(-speed * delta);
        }
    }

    // Put rendering code here to make the actual render() cleaner
    public void draw() {
        // Clear the screen
        ScreenUtils.clear(Color.BLACK);
        // Apply the centered viewport to the render
        fitViewport.apply();
        // Apply the centered viewport to the spriteBatch
        spriteBatch.setProjectionMatrix(fitViewport.getCamera().combined);

        // Begin the drawing
        spriteBatch.begin();

        spriteBatch.draw(background, 0, 0, fitViewport.getScreenWidth(), fitViewport.getScreenHeight());
        player.draw(spriteBatch);

        // End the drawing
        spriteBatch.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
