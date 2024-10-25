package io.github.ap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Pause implements Screen {
    private final SpriteBatch spriteBatch;
    private final Main main;
    private final Levels currentLevel;

    private final Texture backgroundTexture;
    private final Texture backButtonTexture;
    private final Texture restartTexture;
    private final Texture playTexture;


    private boolean isVisible = true;

    public Pause(Main main, SpriteBatch spriteBatch, Levels currentLevel) {
        this.main = main;
        this.spriteBatch = spriteBatch;
        this.currentLevel = currentLevel;

        backgroundTexture = new Texture("stop background.jpg");
        backButtonTexture = new Texture("ingameplay.png");
        restartTexture = new Texture("again.png");
        playTexture = new Texture("menu1.png");
    }

    public void setVisible(boolean visible) {
        this.isVisible = visible;
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        if (!isVisible) return;

        ScreenUtils.clear(Color.BLACK);
        spriteBatch.begin();
        spriteBatch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        float buttonWidth = 200;
        float buttonHeight = 100;

        float centerX = (Gdx.graphics.getWidth() - buttonWidth) / 2;
        float centerY = (Gdx.graphics.getHeight() - (3 * buttonHeight + 40)) / 2;

        spriteBatch.draw(backButtonTexture, centerX, centerY + 2 * (buttonHeight + 20), buttonWidth, buttonHeight);
        spriteBatch.draw(restartTexture, centerX, centerY + buttonHeight + 20, buttonWidth, buttonHeight);
        spriteBatch.draw(playTexture, centerX, centerY, buttonWidth, buttonHeight);

        spriteBatch.end();

        input();
    }

    public void input() {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                float mouseX = Gdx.input.getX();
                float mouseY = Gdx.input.getY();


                float buttonWidth = 200;
                float buttonHeight = 100;

                float centerX = (Gdx.graphics.getWidth() - buttonWidth) / 2;
                float centerY = (Gdx.graphics.getHeight() - (3 * buttonHeight + 40)) / 2;

                if (mouseX >= centerX && mouseX <= centerX + buttonWidth && mouseY >= centerY + 2 * (buttonHeight + 20) && mouseY <= centerY + 2 * (buttonHeight + 20) + buttonHeight) {
                    System.out.println("menu button clicked");
                    setVisible(false);
                    Screen Levelscreen = new LevelScreen(spriteBatch, main);
                    main.setScreen(Levelscreen);
                }


                if (mouseX >= centerX && mouseX <= centerX + buttonWidth && mouseY >= centerY + buttonHeight + 20 && mouseY <= centerY + buttonHeight + 20 + buttonHeight) {
                    System.out.println("Restart button clicked");
                    main.setScreen(currentLevel);
                }

                if (mouseX >= centerX && mouseX <= centerX + buttonWidth && mouseY >= centerY && mouseY <= centerY + buttonHeight) {
                    System.out.println("Play button clicked");
                    setVisible(false);
                    main.setScreen(currentLevel);
                }
            }
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
        backgroundTexture.dispose();
        backButtonTexture.dispose();
        restartTexture.dispose();
        playTexture.dispose();
    }
}







