package io.github.ap;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Main extends Game {
    Texture backgroundTexture;
    Music music;
    ScreenViewport viewport;
    SpriteBatch spriteBatch;
    Texture playButtonTexture;
    Texture exitButtonTexture;
    float playButtonX, playButtonY, playButtonWidth, playButtonHeight;
    float exitButtonX, exitButtonY, exitButtonWidth, exitButtonHeight;

    @Override
    public void create() {
        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
        backgroundTexture = new Texture("cover.png");
        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        viewport = new ScreenViewport();
        spriteBatch = new SpriteBatch();
        music.setLooping(true);
        music.setVolume(0.5f);
        music.play();

        playButtonTexture = new Texture("play.png");
//        playButtonTexture = new Texture("play Button.png");
        playButtonWidth = 400;
        playButtonHeight = 200;
        playButtonX = (Gdx.graphics.getWidth() - playButtonWidth) / 2;
        playButtonY = (float) Gdx.graphics.getHeight() / 2;

        exitButtonTexture = new Texture("Exit.png");
        exitButtonWidth = 100;
        exitButtonHeight = 100;
        exitButtonX = (Gdx.graphics.getWidth() - exitButtonWidth) / 2;
        exitButtonY = playButtonY - 300;

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
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            float mouseX = Gdx.input.getX();
            float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

            // Check if the click is within the button's boundaries
            if (mouseX >= playButtonX && mouseX <= playButtonX + playButtonWidth &&
                mouseY >= playButtonY && mouseY <= playButtonY + playButtonHeight) {
                System.out.println("Play button clicked!");
                startGame();
            }
            if (mouseX >= exitButtonX && mouseX <= exitButtonX + exitButtonWidth &&
                mouseY >= exitButtonY && mouseY <= exitButtonY + exitButtonHeight) {
                Gdx.app.exit();
                System.out.println("Game exit");
            }
        }
    }

    private void logic() {

    }

    private void draw() {
        ScreenUtils.clear(Color.BLACK); // Clear the screen to black
        viewport.apply(); // Apply the viewport
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined); // Set the camera matrix

        spriteBatch.begin();
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        spriteBatch.draw(backgroundTexture, 0, 0, screenWidth, screenHeight);
        spriteBatch.draw(playButtonTexture, playButtonX, playButtonY, playButtonWidth, playButtonHeight);
        spriteBatch.draw(exitButtonTexture, exitButtonX, exitButtonY, exitButtonWidth, exitButtonHeight);
        spriteBatch.end();
    }

    public void startGame() {
        System.out.println("Starting the game...");
        LevelScreen levelScreen = new LevelScreen(spriteBatch, this); // Pass 'this' to LevelScreen
        setScreen(levelScreen);
    }

    @Override
    public void pause() {
        // Handle pause logic if necessary
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        // Dispose all resources to free memory
        backgroundTexture.dispose();
        music.dispose();
        spriteBatch.dispose();
        playButtonTexture.dispose();
        exitButtonTexture.dispose();
    }
}
