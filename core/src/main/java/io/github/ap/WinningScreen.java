package io.github.ap;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.Game;

public class WinningScreen implements Screen {
    private SpriteBatch batch;
    private BitmapFont font;
    private final Levels currentLevel;
    private final Main main;

    public WinningScreen(Main main, Levels currentLevel) {
        this.main = main;
        batch = new SpriteBatch();
        font = new BitmapFont();
        this.currentLevel = currentLevel;
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        font.getData().setScale(2);
        font.draw(batch, "You Won!", Gdx.graphics.getWidth() / 2f - 50, Gdx.graphics.getHeight() / 2f);
        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            main.setScreen(currentLevel);
        }
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
