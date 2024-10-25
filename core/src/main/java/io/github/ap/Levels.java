package io.github.ap;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Levels extends ScreenAdapter {
    protected final Main main;
    protected final SpriteBatch spriteBatch;
    protected final Texture levelTexture;
    protected final Texture backButtonTexture;
    protected final Texture redTexture;
    protected final Texture chuckTexture;
    protected final Texture bluesTexture;
    protected final Texture bombTexture;
    protected final Texture slingShotTexture;
    protected final Texture kingPigTexture;
    protected final Texture wood;
    protected final Texture stone;
    protected final Texture glass;
    protected final Texture tnt;
    protected final Texture pause;





    public Levels(Main main, SpriteBatch spriteBatch) {
        this.main = main;
        this.spriteBatch = spriteBatch;
        this.levelTexture = new Texture("levelScreen1.jpg");
        this.backButtonTexture = new Texture("BackButton.png");
        this.redTexture = new Texture("red.png");
        this.chuckTexture = new Texture("chuck.png");
        this.bluesTexture = new Texture("blues.png");
        this.bombTexture = new Texture("bomb.png");
        this.slingShotTexture = new Texture("slingShot.png");
        this.kingPigTexture = new Texture("kingPig.png");
        this.wood = new Texture("wood.png");
        this.stone = new Texture("stone.png");
        this.glass = new Texture("glass.png");
        this.tnt = new Texture("TNT.png");
        this.pause = new Texture("pause.png");
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
        redTexture.dispose();
        chuckTexture.dispose();
        bluesTexture.dispose();
        bombTexture.dispose();
        slingShotTexture.dispose();
        kingPigTexture.dispose();
        wood.dispose();
        stone.dispose();
        glass.dispose();
        tnt.dispose();
        pause.dispose();
    }
}
