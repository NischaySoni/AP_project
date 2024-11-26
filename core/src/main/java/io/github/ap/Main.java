package io.github.ap;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main extends Game {
    Music music;
    ScreenViewport viewport;
    SpriteBatch spriteBatch;

    @Override
    public void create() {
        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());

        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        viewport = new ScreenViewport();
        spriteBatch = new SpriteBatch();
        music.setLooping(true);
        music.setVolume(0.5f);
        music.play();

        setScreen(new MainMenu(spriteBatch));
    }

    @Override
    public void render() {
        input();
        logic();
        draw();
        super.render();
    }

    public void saveGame(GameState gameState) {
        try (FileOutputStream fileOut = new FileOutputStream("game_state.sav");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(gameState);
            System.out.println("Game state saved successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to save the game state.");
        }
    }

    public GameState loadGame() {
        try (FileInputStream fileIn = new FileInputStream("game_state.sav");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            GameState gameState = (GameState) in.readObject();
            System.out.println("Game state loaded successfully!");
            return gameState;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to load the game state.");
            return null;
        }
    }

    private void input() {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) { // Save the game
            GameState gameState = new GameState(loadGame().getName(), loadGame().getHealth());
            //gameState.setScore(currentScore);
            gameState.setMusicVolume(music.getVolume());
            saveGame(gameState);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.L)) { // Load the game
            GameState loadedGameState = loadGame();
            if (loadedGameState != null) {
                //currentScore = loadedGameState.getScore();
                music.setVolume(loadedGameState.getMusicVolume());
            }
        }
    }

    private void logic() {

    }

    private void draw() {
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);

        spriteBatch.begin();
        spriteBatch.end();
    }

    public void startGame() {
        System.out.println("Starting the game...");
        LevelScreen levelScreen = new LevelScreen(spriteBatch, this);
        setScreen(levelScreen);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        music.dispose();
        spriteBatch.dispose();
    }
}
