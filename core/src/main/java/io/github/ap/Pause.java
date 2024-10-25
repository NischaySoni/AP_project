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

    private final Texture backgroundTexture; // Background texture for the pause menu
    private final Texture backButtonTexture;
    private final Texture restartTexture;
    private final Texture playTexture;

    private final float menuWidth = 600;
    private final float menuHeight = 400;
    private boolean isVisible = true; // Initially set to true to show the menu

    public Pause(Main main, SpriteBatch spriteBatch, Levels currentLevel) {
        this.main = main;
        this.spriteBatch = spriteBatch;
        this.currentLevel = currentLevel;

        // Load textures (ensure these paths are correct)
        backgroundTexture = new Texture("cover.png");
        backButtonTexture = new Texture("back1.png");
        restartTexture = new Texture("again.png");
        playTexture = new Texture("ingameplay.png");
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

        // Draw the pause menu background
        float menuX = (Gdx.graphics.getWidth() - menuWidth) / 2;
        float menuY = (Gdx.graphics.getHeight() - menuHeight) / 2;

        spriteBatch.draw(backgroundTexture, menuX, menuY, menuWidth, menuHeight);

        // Draw buttons (position these based on your design)
        spriteBatch.draw(playTexture, menuX + 20, menuY + 50, 100, 40); // Resume button
        spriteBatch.draw(restartTexture, menuX + 20, menuY + 100, 100, 40); // Restart button
        spriteBatch.draw(backButtonTexture, menuX + 20, menuY + 150, 100, 40); // Back button

        spriteBatch.end();

        input(); // Handle input
    }

    public void input() {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            float mouseX = Gdx.input.getX();
            float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

            float menuX = (Gdx.graphics.getWidth() - menuWidth) / 2;
            float menuY = (Gdx.graphics.getHeight() - menuHeight) / 2;

            if (mouseX >= menuX + 20 && mouseX <= menuX + 120) {
                if (mouseY >= menuY + 150 && mouseY <= menuY + 190) {
                    // Back to Level Screen
                    main.setScreen(new LevelScreen(spriteBatch, main));
                } else if (mouseY >= menuY + 50 && mouseY <= menuY + 90) {
                    // Play (Resume game)
                    setVisible(false);
                    main.setScreen(currentLevel); // Return to current level
                } else if (mouseY >= menuY + 100 && mouseY <= menuY + 140) {
                    // Restart Level
                    main.setScreen(currentLevel); // Restart the current level
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






//package io.github.ap;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Input;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
//import com.badlogic.gdx.utils.ScreenUtils;
//
//public class Pause implements Screen {
//    private final SpriteBatch spriteBatch;
//    private  Texture gameBackgroundTexture;
//    private  Texture backButtonTexture;
//    private  Texture restartTexture;
//    private  Texture playTexture;
//    private final Main main;
//
//    private boolean isVisible = false;
//
//    private final float menuWidth = 600;
//    private final float menuHeight = 400;
//    private final Levels currentLevel;
//
//
//
//    public Pause(Main main, SpriteBatch spriteBatch, Levels currentLevel) {
//        this.main = main;
//        this.spriteBatch = spriteBatch;
//        this.currentLevel = currentLevel; // Set the current level
//    }
//
//    public void setVisible(boolean visible) {
//        this.isVisible = visible;
//    }
//
//    @Override
//    public void show() {}
//
//    @Override
//    public void render(float delta) {
//        if (!isVisible) return;
//
//        ScreenUtils.clear(Color.BLACK);
//        spriteBatch.begin();
//
//        // Draw the pause menu background
//        float menuX = (Gdx.graphics.getWidth() - menuWidth) / 2;
//        float menuY = (Gdx.graphics.getHeight() - menuHeight) / 2;
//
//        spriteBatch.draw(backgroundTexture, menuX, menuY, menuWidth, menuHeight);
//
//
//        spriteBatch.end();
//    }
//
//    public void input() {
//        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
//            float mouseX = Gdx.input.getX();
//            float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
//
//            float menuX = (Gdx.graphics.getWidth() - menuWidth) / 2;
//            float menuY = (Gdx.graphics.getHeight() - menuHeight) / 2;
//
//            if (mouseX >= menuX + 20 && mouseX <= menuX + 120) {
//                if (mouseY >= menuY + 150 && mouseY <= menuY + 190) {
//                    // Back to Level Screen
//                    main.setScreen(new LevelScreen(spriteBatch, main));
//                } else if (mouseY >= menuY + 100 && mouseY <= menuY + 140) {
//                    // Play (Resume game)
//                    setVisible(false);
//                } else if (mouseY >= menuY + 50 && mouseY <= menuY + 90) {
//                    // Restart Level
//                    main.setScreen(currentLevel); // Restart the current level
//                }
//            }
//        }
//    }
//
//    @Override
//    public void resize(int width, int height) {}
//
//    @Override
//    public void pause() {}
//
//    @Override
//    public void resume() {}
//
//    @Override
//    public void hide() {}
//
//    @Override
//    public void dispose() {
//        backgroundTexture.dispose();
//    }
//}
