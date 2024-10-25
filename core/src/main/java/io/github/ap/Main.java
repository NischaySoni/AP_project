package io.github.ap;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Main extends Game {
    Music music;
    ScreenViewport viewport;
    SpriteBatch spriteBatch;

    @Override
    public void create() {
        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());

        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        viewport = new ScreenViewport();
        spriteBatch = new SpriteBatch();
        music.setLooping(true);
        music.setVolume(0.5f);
        music.play();

        setScreen(new MainMenu(spriteBatch));
    }

    @Override
    public void render() {
        input();
        logic();
        draw();
        super.render();
    }

    private void input() {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }

    private void logic() {

    }

    private void draw() {
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);

        spriteBatch.begin();
        spriteBatch.end();
    }

    public void startGame() {
        System.out.println("Starting the game...");
        LevelScreen levelScreen = new LevelScreen(spriteBatch, this);
        setScreen(levelScreen);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        music.dispose();
        spriteBatch.dispose();
    }
}
