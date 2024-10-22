package io.github.ap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenu implements Screen {
    private final SpriteBatch spriteBatch;
    private final Texture backgroundTexture;
    private final Texture playButtonTexture;
    private final Texture exitButtonTexture;

    public MainMenu(SpriteBatch spriteBatch) {
        this.spriteBatch = spriteBatch;
        backgroundTexture = new Texture("cover.png");
        playButtonTexture = new Texture("play.png");
//        playButtonTexture = new Texture("play Button.png");
        exitButtonTexture = new Texture("exit.png");
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK); // Clear the screen to black
        spriteBatch.begin();
        spriteBatch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spriteBatch.draw(playButtonTexture, 770, 420, 400, 200);
        spriteBatch.draw(exitButtonTexture, 925, 200, 100, 100);

//        spriteBatch.draw(playButtonTexture, 860, 510, 200, 100);
//        spriteBatch.draw(exitButtonTexture, 925, 200, 100, 100);

        spriteBatch.end();


        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            float mouseX = Gdx.input.getX();
            float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

            if (mouseX >= 770 && mouseX <= 1170 && mouseY >= 420 && mouseY <= 620) {
                ((Main) Gdx.app.getApplicationListener()).startGame();
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
        backgroundTexture.dispose();
        playButtonTexture.dispose();
        exitButtonTexture.dispose();
    }
}
