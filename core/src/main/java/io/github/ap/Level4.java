package io.github.ap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Level4 extends Levels {
    public Level4(Main main, SpriteBatch spriteBatch) {
        super(main, spriteBatch);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        spriteBatch.begin();
        spriteBatch.draw(levelTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spriteBatch.draw(backButtonTexture, 20, Gdx.graphics.getHeight() - 70, 150, 50);
        spriteBatch.end();
        input();
    }

    private void input() {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            float mouseX = Gdx.input.getX();
            float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

            if (mouseX >= 20 && mouseX <= 170 && mouseY >= Gdx.graphics.getHeight() - 70 && mouseY <= Gdx.graphics.getHeight()) {
                main.setScreen(new LevelScreen(spriteBatch, main));
            }
        }
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
        super.dispose();
    }
}
