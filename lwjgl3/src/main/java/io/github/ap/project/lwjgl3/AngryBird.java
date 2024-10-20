package io.github.ap.project.lwjgl3;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AngryBird extends Game {
    public GameScreen gameScreen;
    private SpriteBatch batch;
    @Override
    public void create() {
        batch = new SpriteBatch();
        gameScreen = new GameScreen(this);
        setScreen(gameScreen);
        Gdx.app.log("MyGame", "Game created and GameScreen set.");
    }

    @Override
    public void dispose() {
        batch.dispose();
        super.dispose();
    }

    public Batch getBatch() {
        return batch;
    }
}
