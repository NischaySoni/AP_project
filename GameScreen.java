package io.github.ap.project;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class GameScreen implements Screen {
    private AngryBird game;
    private Texture img;

    public GameScreen(AngryBird game) {
        this.game = game;
    }
    @Override
    public void show() {
        img = new Texture("badlogic.jpg");
        Gdx.app.log("GameScreen", "Screen is now visible.");
        // Initialize resources here (textures, sounds, etc.)
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.getBatch().begin();
        game.getBatch().draw(img, 0, 0);
        game.getBatch().end();
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
        img.dispose();
        Gdx.app.log("GameScreen", "Disposing screen resources.");
        // Clean up resources
    }
}
