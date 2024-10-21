package io.github.ap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class LevelScreen implements Screen {
    private final SpriteBatch spriteBatch;
    private final Texture gameBackgroundTexture;

    float backButtonX, backButtonY, backButtonWidth, backButtonHeight;

    public LevelScreen(SpriteBatch spriteBatch) {
//        System.out.println("Hello World");
        this.spriteBatch = spriteBatch;
        gameBackgroundTexture = new Texture("levelScreenImg.png"); // Replace with your game background image
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK); // Clear the screen to black
        spriteBatch.begin();
        spriteBatch.draw(gameBackgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        gameBackgroundTexture.dispose();
    }
}


//TextButton backButton = new TextButton("Back", skin);
//        backButton.setPosition(100, 400); // Adjust position as needed
//        backButton.addListener(new ClickListener() {
//    @Override
//    public void clicked(InputEvent event, float x, float y) {
//        game.setScreen(new PreviousScreen(game)); // Replace with your actual previous screen
//    }
//});


