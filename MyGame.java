package io.github.ap.project;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class MyGame extends Game {

    public GameScreen gameScreen;

    @Override
    public void create() {
        gameScreen = new GameScreen(this);
        setScreen(gameScreen);
        Gdx.app.log("MyGame", "Game created and GameScreen set.");
    }
}
