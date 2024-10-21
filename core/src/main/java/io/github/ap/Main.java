package io.github.ap;

/**import com.badlogic.gdx.ApplicationAdapter;
 import com.badlogic.gdx.Gdx;
 import com.badlogic.gdx.Input;
 import com.badlogic.gdx.graphics.Color;
 import com.badlogic.gdx.graphics.Texture;
 import com.badlogic.gdx.graphics.g2d.Sprite;
 import com.badlogic.gdx.graphics.g2d.SpriteBatch;
 import com.badlogic.gdx.math.MathUtils;
 import com.badlogic.gdx.math.Rectangle;
 import com.badlogic.gdx.math.Vector2;
 import com.badlogic.gdx.utils.Array;
 import com.badlogic.gdx.utils.ScreenUtils;
 import com.badlogic.gdx.audio.Music;
 import com.badlogic.gdx.audio.Sound;
 import com.badlogic.gdx.utils.viewport.FitViewport;
 **/
/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */

import com.badlogic.gdx.Application;

/**public class Main extends ApplicationAdapter {
 Texture backgroundTexture;
 Texture bucketTexture;
 Texture dropTexture;
 Sound dropSound;
 Music music;
 SpriteBatch spriteBatch;
 FitViewport viewport;
 Sprite bucketSprite;
 Vector2 touchPos;
 Array<Sprite> dropSprites;
 float dropTimer;
 Rectangle bucketRectangle;
 Rectangle dropRectangle;

 @Override
 public void create() {
 backgroundTexture = new Texture("background.png");
 bucketTexture = new Texture("bucket.png");
 dropTexture = new Texture("drop.png");
 dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.mp3"));
 music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
 spriteBatch = new SpriteBatch();
 viewport = new FitViewport(8, 5);
 bucketSprite = new Sprite(bucketTexture);
 bucketSprite.setSize(1, 1);
 touchPos = new Vector2();
 dropSprites = new Array<>();
 bucketRectangle = new Rectangle();
 dropRectangle = new Rectangle();
 music.setLooping(true);
 music.setVolume(.5f);
 music.play();
 }

 @Override
 public void render() {
 // organize code into three methods
 input();
 logic();
 draw();
 }

 private void input() {
 float speed = .25f;
 float delta = Gdx.graphics.getDeltaTime();
 if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
 bucketSprite.translateX(speed * delta);
 }
 else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
 bucketSprite.translateX(-speed * delta);
 }
 if (Gdx.input.isTouched()) {
 touchPos.set(Gdx.input.getX(), Gdx.input.getY());
 viewport.unproject(touchPos);
 bucketSprite.setCenterX(touchPos.x);
 }
 }

 private void logic() {
 float worldWidth = viewport.getWorldWidth();
 float worldHeight = viewport.getWorldHeight();

 float bucketWidth = bucketSprite.getWidth();
 float bucketHeight = bucketSprite.getHeight();

 bucketSprite.setX(MathUtils.clamp(bucketSprite.getX(), 0, worldWidth - bucketWidth));

 float delta = Gdx.graphics.getDeltaTime();
 // Apply the bucket position and size to the bucketRectangle
 bucketRectangle.set(bucketSprite.getX(), bucketSprite.getY(), bucketWidth, bucketHeight);
 // Loop through the sprites backwards to prevent out of bounds errors
 for (int i = dropSprites.size - 1; i >= 0; i--) {
 Sprite dropSprite = dropSprites.get(i); // Get the sprite from the list
 float dropWidth = dropSprite.getWidth();
 float dropHeight = dropSprite.getHeight();

 dropSprite.translateY(-2f * delta);
 // Apply the drop position and size to the dropRectangle
 dropRectangle.set(dropSprite.getX(), dropSprite.getY(), dropWidth, dropHeight);
 // if the top of the drop goes below the bottom of the view, remove it
 if (dropSprite.getY() < -dropHeight) dropSprites.removeIndex(i);
 else if (bucketRectangle.overlaps(dropRectangle)) { // Check if the bucket overlaps the drop
 dropSprites.removeIndex(i); // Remove the drop
 dropSound.play(); // Play the sound
 }
 }

 dropTimer += delta; // Adds the current delta to the timer
 if (dropTimer > 1f) { // Check if it has been more than a second
 dropTimer = 0; // Reset the timer
 createDroplet(); // Create the droplet
 }
 }

 private void draw() {
 ScreenUtils.clear(Color.BLACK);
 viewport.apply();
 spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
 spriteBatch.begin();

 float worldWidth = viewport.getWorldWidth();
 float worldHeight = viewport.getWorldHeight();

 spriteBatch.draw(backgroundTexture, 0, 0, worldWidth, worldHeight);
 bucketSprite.draw(spriteBatch);
 for (Sprite dropSprite : dropSprites) {
 dropSprite.draw(spriteBatch);
 }
 spriteBatch.end();
 }

 private void createDroplet() {
 // create local variables for convenience
 float dropWidth = 1;
 float dropHeight = 1;
 float worldWidth = viewport.getWorldWidth();
 float worldHeight = viewport.getWorldHeight();

 // create the drop sprite
 Sprite dropSprite = new Sprite(dropTexture);
 dropSprite.setSize(dropWidth, dropHeight);
 dropSprite.setX(MathUtils.random(0f, worldWidth - dropWidth));
 dropSprite.setY(worldHeight);
 dropSprites.add(dropSprite); // Add it to the list
 }

 @Override
 public void pause() {

 }

 @Override
 public void resize(int width, int height) {
 viewport.update(width, height, true); // true centers the camera
 }


 @Override
 public void dispose() {
 }
 }
 **/

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Main extends Game {    // change from ApplicationAdapter to Game
    Texture backgroundTexture;
    Music music;
    ScreenViewport viewport;
    SpriteBatch spriteBatch;
    Texture playButtonTexture;
    Texture exitButtonTexture;
    float playButtonX, playButtonY, playButtonWidth, playButtonHeight;
    float exitButtonX, exitButtonY, exitButtonWidth, exitButtonHeight;

    @Override
    public void create() {
        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
        backgroundTexture = new Texture("cover.png");
        music = Gdx.audio.newMusic(Gdx.files.internal("bgm.wav"));
        viewport = new ScreenViewport();
        spriteBatch = new SpriteBatch();
        music.setLooping(true);
        music.setVolume(.5f);
        music.play();

        playButtonTexture = new Texture("play.png");
        playButtonWidth = 200;
        playButtonHeight = 100;
        playButtonX = (Gdx.graphics.getWidth() - playButtonWidth) / 2; // Centered
//        playButtonX = (float) (Gdx.graphics.getWidth()) / 2;
        playButtonY = (float) (Gdx.graphics.getHeight()) / 2;

        exitButtonTexture = new Texture("Exit.png");
        exitButtonWidth = 50;
        exitButtonHeight = 50;
        exitButtonX = (Gdx.graphics.getWidth() - exitButtonWidth) / 2; // Centered
        exitButtonY = playButtonY - 120;

    }

    @Override
    public void render() {
        input();
        logic();
        draw();
        super.render();
    }

    private void input() {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            float mouseX = Gdx.input.getX();
            float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();  // Invert the y-coordinate

            // Check if the click is within the button's boundaries
            if (mouseX >= playButtonX && mouseX <= playButtonX + playButtonWidth &&
                mouseY >= playButtonY && mouseY <= playButtonY + playButtonHeight) {
                // The play button is clicked, handle the action
                System.out.println("Play button clicked!");
                startGame();  // A method to transition to the gameplay, if applicable
            }
            if (mouseX >= exitButtonX && mouseX <= exitButtonX + exitButtonWidth &&
                mouseY >= exitButtonY && mouseY <= exitButtonY + exitButtonHeight) {
                Gdx.app.exit();
                System.out.println("Game exit");
            }
        }
    }

    private void logic() {

    }

    private void draw() {
        ScreenUtils.clear(Color.BLACK); // Clear the screen to black
        viewport.apply(); // Apply the viewport
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined); // Set the camera matrix

        spriteBatch.begin();

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        spriteBatch.draw(backgroundTexture, 0, 0, screenWidth, screenHeight);
        spriteBatch.draw(playButtonTexture, playButtonX, playButtonY, playButtonWidth, playButtonHeight);
        spriteBatch.draw(exitButtonTexture, exitButtonX, exitButtonY, exitButtonWidth, exitButtonHeight);

        spriteBatch.end();
    }

    private void startGame() {
        System.out.println("Starting the game...");
        LevelScreen levelScreen = new LevelScreen(spriteBatch);
        setScreen(levelScreen);
//        setScreen(new LevelScreen(spriteBatch));
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
        backgroundTexture.dispose();
        music.dispose();
        spriteBatch.dispose();
        playButtonTexture.dispose();
    }
}

//  This the test done by me
