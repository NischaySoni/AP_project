package io.github.ap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Levels extends ScreenAdapter {
    protected final Main main;
    protected final SpriteBatch spriteBatch;
    protected final Texture levelTexture;
    protected final Texture backButtonTexture;


    public Levels(Main main, SpriteBatch spriteBatch) {
        this.main = main;
        this.spriteBatch = spriteBatch;
        this.levelTexture = new Texture("theme.png");
        this.backButtonTexture = new Texture("backButton.png");
    }

    @Override
    public void show(){

    }

    @Override
    public void render(float delta){

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
        levelTexture.dispose();
        backButtonTexture.dispose();
    }
}
