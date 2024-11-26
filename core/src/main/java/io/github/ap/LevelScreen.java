package io.github.ap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
    private final BitmapFont font;
    private GameState gameState;

    private final int buttonSize = 300;
    private final int hoverButtonSize = 350;
    private final int backButtonWidth = 250;
    private final int backButtonHeight = 150;
    private final int hoverBackButtonWidth = 275;
    private final int hoverBackButtonHeight = 175;

    public LevelScreen(SpriteBatch spriteBatch, Main main) {
        this.main = main;
        this.spriteBatch = spriteBatch;
        this.gameState = new GameState(loadGame().getName(), loadGame().getHealth());
        gameBackgroundTexture = new Texture("Space.jpg");
        backButtonTexture = new Texture("BackButton.png");
        level1Texture = new Texture("LevelButton.png");
        level2Texture = new Texture("LevelButton.png");
        level3Texture = new Texture("LevelButton.png");
        level4Texture = new Texture("LevelButton.png");
        level5Texture = new Texture("LevelButton.png");
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(3);
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

    public LevelScreen(SpriteBatch spriteBatch, Main main, GameState savedState) {
        this.spriteBatch = spriteBatch;
        this.main = main;
        this.gameState = savedState;
        gameBackgroundTexture = new Texture("Space.jpg");
        backButtonTexture = new Texture("BackButton.png");
        level1Texture = new Texture("LevelButton.png");
        level2Texture = new Texture("LevelButton.png");
        level3Texture = new Texture("LevelButton.png");
        level4Texture = new Texture("LevelButton.png");
        level5Texture = new Texture("LevelButton.png");
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(3);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        spriteBatch.begin();
        spriteBatch.draw(gameBackgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        float mouseX = Gdx.input.getX();
        float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

        drawButtonWithHover(mouseX, mouseY, 20, Gdx.graphics.getHeight() - 150, backButtonTexture, backButtonWidth, backButtonHeight, hoverBackButtonWidth, hoverBackButtonHeight);
        drawButtonWithHover(mouseX, mouseY, 400, 300, level1Texture, buttonSize, buttonSize, hoverButtonSize, hoverButtonSize);
        drawButtonWithHover(mouseX, mouseY, 600, 500, level2Texture, buttonSize, buttonSize, hoverButtonSize, hoverButtonSize);
        drawButtonWithHover(mouseX, mouseY, 800, 300, level3Texture, buttonSize, buttonSize, hoverButtonSize, hoverButtonSize);
        drawButtonWithHover(mouseX, mouseY, 1000, 500, level4Texture, buttonSize, buttonSize, hoverButtonSize, hoverButtonSize);
        drawButtonWithHover(mouseX, mouseY, 1200, 300, level5Texture, buttonSize, buttonSize, hoverButtonSize, hoverButtonSize);

        drawLevelNumber("1", 400, 300);
        drawLevelNumber("2", 600, 500);
        drawLevelNumber("3", 800, 300);
        drawLevelNumber("4", 1000, 500);
        drawLevelNumber("5", 1200, 300);

        spriteBatch.end();

        input();
    }

    private void drawLevelNumber(String text, float x, float y) {
        float textX = x + buttonSize / 2f - font.getSpaceXadvance();
        float textY = y + buttonSize / 2f + font.getCapHeight() / 2f;
        font.draw(spriteBatch, text, textX, textY);
    }

    private void drawButtonWithHover(float mouseX, float mouseY, float x, float y, Texture texture, int defaultWidth, int defaultHeight, int hoverWidth, int hoverHeight) {
        int drawWidth = defaultWidth;
        int drawHeight = defaultHeight;

        if (mouseX >= x && mouseX <= x + defaultWidth && mouseY >= y && mouseY <= y + defaultHeight) {
            drawWidth = hoverWidth;
            drawHeight = hoverHeight;
        }

        spriteBatch.draw(texture, x - (drawWidth - defaultWidth) / 2f, y - (drawHeight - defaultHeight) / 2f, drawWidth, drawHeight);
    }

    private void input() {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            float mouseX = Gdx.input.getX();
            float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

            if (mouseX >= 20 && mouseX <= 20 + backButtonWidth && mouseY >= Gdx.graphics.getHeight() - 150 && mouseY <= Gdx.graphics.getHeight()) {
                System.out.println("Back button clicked!");
                main.setScreen(new MainMenu(spriteBatch));
            } else if (mouseX >= 400 && mouseX <= 600 && mouseY >= 300 && mouseY <= 600) {
                System.out.println("Level 1 button clicked!");
                main.setScreen(new Level1(main, spriteBatch));
            } else if (mouseX >= 600 && mouseX <= 800 && mouseY >= 500 && mouseY <= 800) {
                System.out.println("Level 2 button clicked!");
                main.setScreen(new Level2(main, spriteBatch));
            } else if (mouseX >= 800 && mouseX <= 1000 && mouseY >= 300 && mouseY <= 600) {
                System.out.println("Level 3 button clicked!");
                main.setScreen(new Level3(main, spriteBatch));
            } else if (mouseX >= 1000 && mouseX <= 1200 && mouseY >= 500 && mouseY <= 800) {
                System.out.println("Level 4 button clicked!");
                main.setScreen(new Level4(main, spriteBatch));
            } else if (mouseX >= 1200 && mouseX <= 1400 && mouseY >= 300 && mouseY <= 600) {
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
        font.dispose();
    }
}
