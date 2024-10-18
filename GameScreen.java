package io.github.ap.project;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class GameScreen implements Screen {

    private MyGame game;

    public GameScreen(MyGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        Gdx.app.log("GameScreen", "Screen is now visible.");
        // Initialize resources here (textures, sounds, etc.)
    }

    @Override
    public void render(float delta) {
        // Clear the screen with a color (e.g., black)
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Here is where the game logic and rendering would go

        Gdx.app.log("GameScreen", "Rendering...");
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("GameScreen", "Resizing to: " + width + " x " + height);
    }

    @Override
    public void pause() {
        Gdx.app.log("GameScreen", "Game paused.");
    }

    @Override
    public void resume() {
        Gdx.app.log("GameScreen", "Game resumed.");
    }

    @Override
    public void hide() {
        Gdx.app.log("GameScreen", "Screen is now hidden.");
        // Release resources here if needed
    }

    @Override
    public void dispose() {
        Gdx.app.log("GameScreen", "Disposing screen resources.");
        // Clean up resources
    }
}
