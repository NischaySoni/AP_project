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
        spriteBatch.draw(backButtonTexture, 20, Gdx.graphics.getHeight() - 150, 250, 150);
        spriteBatch.draw(slingShotTexture, 400, 200, 150, 300);
        spriteBatch.draw(redTexture, 100, 200, 100, 100);
        spriteBatch.draw(chuckTexture, 200, 200, 100, 100);
        spriteBatch.draw(bluesTexture, 300, 200, 70, 70);
        spriteBatch.draw(bombTexture, 370, 400, 130, 130);
        spriteBatch.draw(kingPigTexture, 1300, 200, 160, 160);
        spriteBatch.draw(wood, 1020, 400, 700, 50);
        spriteBatch.draw(glass, 1200, 200,50,200);
        spriteBatch.draw(stone, 1500, 200, 50, 200);
        spriteBatch.draw(tnt, 1300, 450, 150, 70);

        float pauseX = (Gdx.graphics.getWidth() - 50) / 2f;
        float pauseY = Gdx.graphics.getHeight() - 70;
        spriteBatch.draw(pause, pauseX, pauseY, 50, 50);
        spriteBatch.end();
        input();
    }

    private void input() {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            float mouseX = Gdx.input.getX();
            float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

            if (mouseX >= 20 && mouseX <= 270 && mouseY >= Gdx.graphics.getHeight() - 150 && mouseY <= Gdx.graphics.getHeight()) {
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
