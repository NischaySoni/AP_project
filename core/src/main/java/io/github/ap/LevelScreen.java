package io.github.ap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class LevelScreen implements Screen {
    private final SpriteBatch spriteBatch;
    private final Texture gameBackgroundTexture;
    private final Texture backButtonTexture;
    private final Texture level1Texture;
    private final Texture level2Texture;
    private final Texture level3Texture;
    private final Texture level4Texture;
    private final Texture level5Texture;
    private final Main main;
    private final float backButtonX;
    private final float backButtonY;
    private final float backButtonWidth = 400;
    private final float backButtonHeight = 200;

    public LevelScreen(SpriteBatch spriteBatch, Main main) {
        this.main = main;
        this.spriteBatch = spriteBatch;
        gameBackgroundTexture = new Texture("level.jpg");
        backButtonTexture = new Texture("BackButton.png");
        level1Texture = new Texture("LevelButton.png");
        level2Texture = new Texture("LevelButton.png");
        level3Texture = new Texture("LevelButton.png");
        level4Texture = new Texture("LevelButton.png");
        level5Texture = new Texture("LevelButton.png");

        backButtonX = 20;
        backButtonY = Gdx.graphics.getHeight() - backButtonHeight - 20; // 20 pixels from the top
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        spriteBatch.begin();
        spriteBatch.draw(gameBackgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spriteBatch.draw(backButtonTexture, backButtonX, backButtonY, backButtonWidth, backButtonHeight);
        spriteBatch.draw(level1Texture, 300, 300, 300, 300);
        spriteBatch.draw(level2Texture, 500, 500, 300, 300);
        spriteBatch.draw(level3Texture, 700, 300, 300, 300);
        spriteBatch.draw(level4Texture, 900, 500, 300, 300);
        spriteBatch.draw(level5Texture, 1100, 300, 300, 300);
        spriteBatch.end();

        input();
    }

    private void input() {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            float mouseX = Gdx.input.getX();
            float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

            if (mouseX >= backButtonX && mouseX <= backButtonX + backButtonWidth &&
                mouseY >= backButtonY && mouseY <= backButtonY + backButtonHeight) {
                System.out.println("Back button clicked!");
                main.setScreen(new MainMenu(spriteBatch));
            } else if (mouseX >= 300 && mouseX <= 500 && mouseY >= 300 && mouseY <= 500) {
                System.out.println("Level 1 button clicked!");
                main.setScreen(new Level1(main, spriteBatch));
            } else if (mouseX >= 500 && mouseX <= 700 && mouseY >= 500 && mouseY <= 700) {
                System.out.println("Level 2 button clicked!");
                main.setScreen(new Level2(main, spriteBatch));
            } else if (mouseX >= 700 && mouseX <= 900 && mouseY >= 300 && mouseY <= 500) {
                System.out.println("Level 3 button clicked!");
                main.setScreen(new Level3(main, spriteBatch));
            } else if (mouseX >= 900 && mouseX <= 1100 && mouseY >= 500 && mouseY <= 700) {
                System.out.println("Level 4 button clicked!");
                main.setScreen(new Level4(main, spriteBatch));
            } else if (mouseX >= 1100 && mouseX <= 1300 && mouseY >= 300 && mouseY <= 500) {
                System.out.println("Level 5 button clicked!");
                main.setScreen(new Level5(main, spriteBatch));
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
        gameBackgroundTexture.dispose();
        backButtonTexture.dispose();
    }
}
