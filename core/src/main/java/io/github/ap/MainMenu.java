package io.github.ap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainMenu implements Screen {
    private final SpriteBatch spriteBatch;
    private final Texture backgroundTexture;
    private final Texture playButtonTexture;
    private final Texture exitButtonTexture;
    private final Texture saveButtonTexture;

    private final int playButtonWidth = 500;
    private final int playButtonHeight = 250;
    private final int exitButtonWidth = 100;
    private final int exitButtonHeight = 100;
    private final int saveButtonWidth = 450;
    private final int saveButtonHeight = 200;

    private final int hoverPlayButtonWidth = 550;
    private final int hoverPlayButtonHeight = 300;
    private final int hoverExitButtonWidth = 120;
    private final int hoverExitButtonHeight = 120;
    private final int hoverSaveButtonWidth = 500;
    private final int hoverSaveButtonHeight = 250;

    public MainMenu(SpriteBatch spriteBatch) {
        this.spriteBatch = spriteBatch;
        backgroundTexture = new Texture("starting.jpg");
        playButtonTexture = new Texture("play.png");
        exitButtonTexture = new Texture("exit.png");
        saveButtonTexture = new Texture("save.png");
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        spriteBatch.begin();

        spriteBatch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        float mouseX = Gdx.input.getX();
        float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

        int playButtonDrawWidth = playButtonWidth;
        int playButtonDrawHeight = playButtonHeight;
        if (mouseX >= 720 && mouseX <= 720 + playButtonWidth && mouseY >= 520 && mouseY <= 520 + playButtonHeight) {
            playButtonDrawWidth = hoverPlayButtonWidth;
            playButtonDrawHeight = hoverPlayButtonHeight;
        }

        int exitButtonDrawWidth = exitButtonWidth;
        int exitButtonDrawHeight = exitButtonHeight;
        if (mouseX >= 925 && mouseX <= 925 + exitButtonWidth && mouseY >= 200 && mouseY <= 200 + exitButtonHeight) {
            exitButtonDrawWidth = hoverExitButtonWidth;
            exitButtonDrawHeight = hoverExitButtonHeight;
        }

        int saveButtonDrawWidth = saveButtonWidth;
        int saveButtonDrawHeight = saveButtonHeight;
        if (mouseX >= 740 && mouseX <= 740 + saveButtonWidth && mouseY >= 400 && mouseY <= 400 + saveButtonHeight) {
            saveButtonDrawWidth = hoverSaveButtonWidth;
            saveButtonDrawHeight = hoverSaveButtonHeight;
        }

        spriteBatch.draw(playButtonTexture, 720 - (playButtonDrawWidth - playButtonWidth) / 2,
            520 - (playButtonDrawHeight - playButtonHeight) / 2,
            playButtonDrawWidth, playButtonDrawHeight);

        spriteBatch.draw(exitButtonTexture, 925 - (exitButtonDrawWidth - exitButtonWidth) / 2,
            200 - (exitButtonDrawHeight - exitButtonHeight) / 2,
            exitButtonDrawWidth, exitButtonDrawHeight);

        spriteBatch.draw(saveButtonTexture, 740 - (saveButtonDrawWidth - saveButtonWidth) / 2,
            400 - (saveButtonDrawHeight - saveButtonHeight) / 2,
            saveButtonDrawWidth, saveButtonDrawHeight);

        spriteBatch.end();

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            if (mouseX >= 720 && mouseX <= 720 + playButtonWidth && mouseY >= 520 && mouseY <= 520 + playButtonHeight) {
                ((Main) Gdx.app.getApplicationListener()).startGame();
            }
            if (mouseX >= 925 && mouseX <= 925 + exitButtonWidth && mouseY >= 200 && mouseY <= 200 + exitButtonHeight) {
                Gdx.app.exit();
            }
            if (mouseX >= 740 && mouseX <= 740 + saveButtonWidth && mouseY >= 400 && mouseY <= 400 + saveButtonHeight) {
                ((Main) Gdx.app.getApplicationListener()).saveGame(new GameState(loadGame().getName(), loadGame().getHealth()));
            }
        }
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
        backgroundTexture.dispose();
        playButtonTexture.dispose();
        exitButtonTexture.dispose();
    }
}
