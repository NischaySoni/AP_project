package io.github.ap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;


public class Level3 extends Levels {

    public enum BirdType {
        RED, YELLOW, BLACK, BLUE
    }
    private BirdType currentBird = BirdType.RED;
    private boolean isPaused = false;
    private Music music;
    private BitmapFont font;
    private RedBird redBird;
    private BlueBird blueBird;
    private BlackBird blackBird;
    private YellowBird yellowBird;
    private KingPig kingPig;
    private Pig1 pig1;
    private Pig2 pig2;
    private Glass glass;
    private Wood wood;
    private Stone stone;
    private TNT tnt;
    private int score = 0;
    private boolean isWood = true;
    private boolean isStone = true;
    private boolean isGlass = true;
    private boolean isKingPig = true;
    private boolean isPig1 = true;
    private boolean isPig2 = true;
    private boolean isTNT = true;

    private ShapeRenderer shapeRenderer;
    private Vector2 startPoint1, endPoint1;
    private Vector2 startPoint2, endPoint2;
    private boolean isDragging1, isDragging2;
    private Texture slingShotTexture;  // Texture for slingshot band
    private float slingshotX, slingshotY;

    public Level3(Main main, SpriteBatch spriteBatch) {
        super(main, spriteBatch);
        shapeRenderer = new ShapeRenderer();
        slingShotTexture = new Texture("slingShot.png");

        // Set input processor to handle mouse input for slingshot
        Gdx.input.setInputProcessor(new Level3.SlingShotInputProcessor());

        // Position for slingshot texture
        slingshotX = Gdx.graphics.getWidth() / 5f;  // Adjust X to move it to the left (1/5th of the screen width)
        slingshotY = Gdx.graphics.getHeight() / 4f;  // Adjust Y to move it lower (1/4th of the screen height)

        // Calculate the top position of the slingshot texture
        float slingshotTopY = slingshotY + slingShotTexture.getHeight() / 2f;  // Top of the texture

        // Initialize the start and end points of the slingshot lines to the top of the slingshot
        startPoint1 = new Vector2(slingshotX, slingshotTopY); // Slingshot anchor point 1 (top of texture)
        endPoint1 = new Vector2(startPoint1); // Initially the same as start

        startPoint2 = new Vector2(slingshotX + slingShotTexture.getWidth(), slingshotTopY); // Slingshot anchor point 2 (right side of texture, top)
        endPoint2 = new Vector2(startPoint2); // Initially the same as start
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(3);
        redBird = new RedBird();
        blueBird = new BlueBird();
        blackBird = new BlackBird();
        yellowBird = new YellowBird();
        if (isKingPig) {
            kingPig = new KingPig(1300, 200, 160, 160, 4, "KingPig", kingPigTexture);
        }
        if (isPig1) {
            pig1 = new Pig1(1300, 500, 130, 130, 3, "Pig1", pig1Texture);
        }
        if (isPig2) {
            pig2 = new Pig2(1100, 200, 130, 130, 3, "Pig2", pig2Texture);
        }
        if (isGlass) {
            glass = new Glass(1150, 300, 50, 200, 6, "Glass", glassTexture);
        }
        if (isWood) {
            wood = new Wood(1020, 400, 700, 50, 8, "Wood", woodTexture);
        }
        if (isStone) {
            stone = new Stone(1300, 200, 50, 200, 10, "Stone", stoneTexture);
        }
        if (isTNT) {
            tnt = new TNT(1300, 450, 150, 70, 1, "TNT", tntTexture);
        }
    }

    @Override
    public void show() {}

    private void drawStaticSlingshot() {
        // Draw the static slingshot image at the given position
        spriteBatch.begin();
        spriteBatch.draw(slingShotTexture, slingshotX - slingShotTexture.getWidth() / 2, slingshotY - slingShotTexture.getHeight() / 2);
        spriteBatch.end();
    }

    private void drawSlingshotLines() {
        shapeRenderer.setProjectionMatrix(main.viewport.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        // Draw the first line if dragging
        if (isDragging1) {
            shapeRenderer.line(startPoint1.x, startPoint1.y, endPoint1.x, endPoint1.y);
        }

        // Draw the second line if dragging
        if (isDragging2) {
            shapeRenderer.line(startPoint2.x, startPoint2.y, endPoint2.x, endPoint2.y);
        }

        shapeRenderer.end();
    }

    private void drawSlingshotTextures() {
        spriteBatch.begin();

        // Draw first slingshot band texture (from startPoint1 to endPoint1)
        if (isDragging1) {
            float angle1 = (float) Math.atan2(endPoint1.y - startPoint1.y, endPoint1.x - startPoint1.x); // Angle for rotation
            float distance1 = startPoint1.dst(endPoint1); // Distance between the points (length of the band)

            // Draw the first texture stretched along the angle, applying rotation and scaling
            spriteBatch.draw(slingShotTexture, startPoint1.x, startPoint1.y,
                0, slingShotTexture.getHeight() / 2,
                distance1, slingShotTexture.getHeight(),
                1, 1
            );
        }

        // Draw second slingshot band texture (from startPoint2 to endPoint2)
        if (isDragging2) {
            float angle2 = (float) Math.atan2(endPoint2.y - startPoint2.y, endPoint2.x - startPoint2.x); // Angle for rotation
            float distance2 = startPoint2.dst(endPoint2); // Distance between the points (length of the band)

            // Draw the second texture stretched along the angle, applying rotation and scaling
            spriteBatch.draw(slingShotTexture, startPoint2.x, startPoint2.y,
                0, slingShotTexture.getHeight() / 2,
                distance2, slingShotTexture.getHeight(),
                1, 1
            );
        }

        spriteBatch.end();
    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        spriteBatch.begin();
        spriteBatch.draw(levelTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spriteBatch.end();

        if (isPaused) {
            return;
        }

        // Draw the static slingshot texture (this is your stationary slingshot band)
        drawStaticSlingshot();

        // Draw the slingshot lines using ShapeRenderer
        drawSlingshotLines();

        // Draw the dynamic slingshot textures (bands) on top of the static one
        drawSlingshotTextures();

        if (redBird.isLaunched()) {
            redBird.update(delta);
            if (isKingPig && redBird.checkCollision(kingPig.getX(), kingPig.getY(), kingPig.getWidth(), kingPig.getHeight())) {
                System.out.println("Red Bird hit the King Pig!");
                kingPig.takeDamage();
                handleCollision(redBird, kingPig);
                redBird.reset();
            }
            if (isPig1 && redBird.checkCollision(pig1.getX(), pig1.getY(), pig1.getWidth(), pig1.getHeight())) {
                System.out.println("Red Bird hit the King Pig!");
                pig1.takeDamage();
                handleCollision(redBird, pig1);
                redBird.reset();
            }
            if (isPig2 && redBird.checkCollision(pig2.getX(), pig2.getY(), pig2.getWidth(), pig2.getHeight())) {
                System.out.println("Red Bird hit the King Pig!");
                pig2.takeDamage();
                handleCollision(redBird, pig2);
                redBird.reset();
            }
            if (isWood && redBird.checkCollision(wood.getX(), wood.getY(), wood.getWidth(), wood.getHeight())) {
                System.out.println("Red Bird hit the Wood!");
                wood.takeDamage();
                handleCollision(redBird, wood);
                redBird.reset();
            }
            if (isGlass && redBird.checkCollision(glass.getX(), glass.getY(), glass.getWidth(), glass.getHeight())) {
                System.out.println("Red Bird hit the Glass!");
                glass.takeDamage();
                handleCollision(redBird, glass);
                redBird.reset();
            }
            if (isStone && redBird.checkCollision(stone.getX(), stone.getY(), stone.getWidth(), stone.getHeight())) {
                System.out.println("Red Bird hit the Stone!");
                stone.takeDamage();
                handleCollision(redBird, stone);
                redBird.reset();
            }
            if (isTNT && redBird.checkCollision(tnt.getX(), tnt.getY(), tnt.getWidth(), tnt.getHeight())) {
                System.out.println("Red Bird hit the tnt!");
                tnt.takeDamage();
                handleCollision(redBird, tnt);
                redBird.reset();
            }
        }
        if (blueBird.isLaunched()) {
            blueBird.update(delta);
            if (isKingPig && blueBird.checkCollision(kingPig.getX(), kingPig.getY(), kingPig.getWidth(), kingPig.getHeight())) {
                System.out.println("Blue Bird hit the King Pig!");
                kingPig.takeDamage();
                handleCollision(blueBird, kingPig);
            }
            if (isPig1 && blueBird.checkCollision(pig1.getX(), pig1.getY(), pig1.getWidth(), pig1.getHeight())) {
                System.out.println("Blue Bird hit the King Pig!");
                pig1.takeDamage();
                handleCollision(blueBird, pig1);
                blueBird.reset();
            }
            if (isPig2 && blueBird.checkCollision(pig2.getX(), pig2.getY(), pig2.getWidth(), pig2.getHeight())) {
                System.out.println("Blue Bird hit the King Pig!");
                pig2.takeDamage();
                handleCollision(blueBird, pig2);
                blueBird.reset();
            }
            if (isWood && blueBird.checkCollision(wood.getX(), wood.getY(), wood.getWidth(), wood.getHeight())) {
                System.out.println("Blue Bird hit the Wood!");
                wood.takeDamage();
                handleCollision(blueBird, wood);
            }
            if (isGlass && blueBird.checkCollision(glass.getX(), glass.getY(), glass.getWidth(), glass.getHeight())) {
                System.out.println("Blue Bird hit the Glass!");
                glass.takeDamage();
                glass.takeDamage();
                handleCollision(blueBird, glass);
            }
            if (isStone && blueBird.checkCollision(stone.getX(), stone.getY(), stone.getWidth(), stone.getHeight())) {
                System.out.println("Blue Bird hit the Stone!");
                stone.takeDamage();
                handleCollision(blueBird, stone);
            }
            if (isTNT && blueBird.checkCollision(tnt.getX(), tnt.getY(), tnt.getWidth(), tnt.getHeight())) {
                System.out.println("Blue Bird hit the TNT!");
                tnt.takeDamage();
                handleCollision(blueBird, tnt);
            }
        }


        if (blackBird.isLaunched()) {
            blackBird.update(delta);
            if (isKingPig && blackBird.checkCollision(kingPig.getX(), kingPig.getY(), kingPig.getWidth(), kingPig.getHeight())) {
                System.out.println("Black Bird hit the King Pig!");
                kingPig.takeDamage();
                handleCollision(blackBird, kingPig);
                blackBird.reset();
            }
            if (isPig1 && blackBird.checkCollision(pig1.getX(), pig1.getY(), pig1.getWidth(), pig1.getHeight())) {
                System.out.println("Black Bird hit the King Pig!");
                pig1.takeDamage();
                handleCollision(blackBird, pig1);
                blackBird.reset();
            }
            if (isPig2 && blackBird.checkCollision(pig2.getX(), pig2.getY(), pig2.getWidth(), pig2.getHeight())) {
                System.out.println("Black Bird hit the King Pig!");
                pig2.takeDamage();
                handleCollision(blackBird, pig2);
                blackBird.reset();
            }
            if (isWood && blackBird.checkCollision(wood.getX(), wood.getY(), wood.getWidth(), wood.getHeight())) {
                System.out.println("Black Bird hit the Wood!");
                wood.takeDamage();
                handleCollision(blackBird, wood);
                blackBird.reset();
            }
            if (isGlass && blackBird.checkCollision(glass.getX(), glass.getY(), glass.getWidth(), glass.getHeight())) {
                System.out.println("Black Bird hit the Glass!");
                glass.takeDamage();
                handleCollision(blackBird, glass);
                blackBird.reset();
            }
            if (isStone && blackBird.checkCollision(stone.getX(), stone.getY(), stone.getWidth(), stone.getHeight())) {
                System.out.println("Black Bird hit the Stone!");
                stone.takeDamage();
                handleCollision(blackBird, stone);
                blackBird.reset();
            }
            if (isTNT && blackBird.checkCollision(tnt.getX(), tnt.getY(), tnt.getWidth(), tnt.getHeight())) {
                System.out.println("Black Bird hit the tnt!");
                tnt.takeDamage();
                handleCollision(blackBird, tnt);
                blackBird.reset();
            }
        }
        if (yellowBird.isLaunched()) {
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                yellowBird.activateSpeedBoost(); // Activate speed boost
            } else {
                yellowBird.deactivateSpeedBoost(); // Deactivate speed boost when spacebar is released
            }
            yellowBird.update(delta);
            if (isKingPig && yellowBird.checkCollision(kingPig.getX(), kingPig.getY(), kingPig.getWidth(), kingPig.getHeight())) {
                System.out.println("Yellow Bird hit the King Pig!");
                kingPig.takeDamage();
                handleCollision(yellowBird, kingPig);
                yellowBird.reset();
            }
            if (isPig1 && yellowBird.checkCollision(pig1.getX(), pig1.getY(), pig1.getWidth(), pig1.getHeight())) {
                System.out.println("Yellow Bird hit the King Pig!");
                pig1.takeDamage();
                handleCollision(yellowBird, pig1);
                yellowBird.reset();
            }
            if (isPig2 && yellowBird.checkCollision(pig2.getX(), pig2.getY(), pig2.getWidth(), pig2.getHeight())) {
                System.out.println("Yellow Bird hit the King Pig!");
                pig2.takeDamage();
                handleCollision(yellowBird, pig2);
                yellowBird.reset();
            }
            if (isWood && yellowBird.checkCollision(wood.getX(), wood.getY(), wood.getWidth(), wood.getHeight())) {
                System.out.println("Yellow Bird hit the Wood!");
                wood.takeDamage();
                if (yellowBird.isSpeedBoosted()) {
                    wood.takeDamage(); // Deal double damage
                }
                handleCollision(yellowBird, wood);
                yellowBird.reset();
            }
            if (isGlass && yellowBird.checkCollision(glass.getX(), glass.getY(), glass.getWidth(), glass.getHeight())) {
                System.out.println("Yellow Bird hit the Glass!");
                glass.takeDamage();
                handleCollision(yellowBird, glass);
                yellowBird.reset();
            }
            if (isStone && yellowBird.checkCollision(stone.getX(), stone.getY(), stone.getWidth(), stone.getHeight())) {
                System.out.println("Yellow Bird hit the Stone!");
                stone.takeDamage();
                handleCollision(yellowBird, stone);
                yellowBird.reset();
            }
            if (isTNT && yellowBird.checkCollision(tnt.getX(), tnt.getY(), tnt.getWidth(), tnt.getHeight())) {
                System.out.println("Yellow Bird hit the tnt!");
                tnt.takeDamage();
                handleCollision(yellowBird, tnt);
                yellowBird.reset();
            }
        }

        spriteBatch.begin();
        spriteBatch.draw(redTexture, redBird.getX(), redBird.getY(), 100, 100);
        spriteBatch.draw(chuckTexture, yellowBird.getX(), yellowBird.getY(), 100, 100);
        spriteBatch.draw(bluesTexture, blueBird.getX(), blueBird.getY(), 70, 70);
        spriteBatch.draw(bombTexture, blackBird.getX(), blackBird.getY(), 130, 130);
        if (isKingPig) {
            spriteBatch.draw(kingPigTexture, kingPig.getX(), kingPig.getY(), kingPig.getWidth(), kingPig.getHeight());
        }
        if (isPig1) {
            spriteBatch.draw(pig1Texture, pig1.getX(), pig1.getY(), pig1.getWidth(), pig1.getHeight());
        }
        if (isPig2) {
            spriteBatch.draw(pig2Texture, pig2.getX(), pig2.getY(), pig2.getWidth(), pig2.getHeight());
        }
        if (isWood) {
            spriteBatch.draw(woodTexture, wood.getX(), wood.getY(), wood.getWidth(), wood.getHeight());
        }
        if (isGlass) {
            spriteBatch.draw(glassTexture, glass.getX(), glass.getY(), glass.getWidth(), glass.getHeight());
        }
        if (isStone) {
            spriteBatch.draw(stoneTexture, stone.getX(), stone.getY(), stone.getWidth(), stone.getHeight());
        }
        if (isTNT) {
            spriteBatch.draw(tntTexture, tnt.getX(), tnt.getY(), tnt.getWidth(), tnt.getHeight());
        }
        spriteBatch.draw(platformTexture, -2400, -100, 4500, 2350);

        float backButtonX = 20;
        float backButtonY = Gdx.graphics.getHeight() - 150;
        float backButtonWidth = 250;
        float backButtonHeight = 150;
        if (isMouseOverButton(Gdx.input.getX(), Gdx.input.getY(), backButtonX, backButtonY, backButtonWidth, backButtonHeight)) {
            spriteBatch.draw(backButtonTexture, backButtonX - 10, backButtonY - 10, backButtonWidth + 20, backButtonHeight + 20);
        } else {
            spriteBatch.draw(backButtonTexture, backButtonX, backButtonY, backButtonWidth, backButtonHeight);
        }

        float pauseX = (Gdx.graphics.getWidth() - 50) / 2f;
        float pauseY = Gdx.graphics.getHeight() - 70;
        if (isMouseOverButton(Gdx.input.getX(), Gdx.input.getY(), pauseX, pauseY, 50, 50)) {
            spriteBatch.draw(pause, pauseX - 5, pauseY - 5, 60, 60);
        } else {
            spriteBatch.draw(pause, pauseX, pauseY, 50, 50);
        }

        String scoreText = "Score: " + score;
        float textX = Gdx.graphics.getWidth() - font.getRegion().getRegionWidth() - 20;
        float textY = Gdx.graphics.getHeight() - 20;
        font.draw(spriteBatch, scoreText, textX, textY);

        spriteBatch.end();

        input();
    }

    private void handleCollision(GameObject bird, GameObject target) {
        // Increase score based on the object hit
        if (target instanceof KingPig) {
            score += 100;  // Increase score by 100 for hitting the KingPig
        }
        if (target instanceof Pig1) {
            score += 70;  // Increase score by 70 for hitting the Pig1
        }
        if (target instanceof KingPig) {
            score += 70;  // Increase score by 70 for hitting the Pig2
        }
        if (target instanceof Wood) {
            score += 10;  // Increase score by 10 for hitting Wood
            music = Gdx.audio.newMusic(Gdx.files.internal("wood.mp3"));
            music.setVolume(0.5f);
            music.play();
        }
        if (target instanceof Glass) {
            score += 5;  // Increase score by 5 for hitting Glass
            music = Gdx.audio.newMusic(Gdx.files.internal("glass.mp3"));
            music.setVolume(0.5f);
            music.play();
        }
        if (target instanceof Stone) {
            score += 15;  // Increase score by 15 for hitting Stone
            music = Gdx.audio.newMusic(Gdx.files.internal("stone.mp3"));
            music.setVolume(0.5f);
            music.play();
        }
        if (target instanceof TNT) {
            score += 20;  // Increase score by 20 for hitting TNT
            music = Gdx.audio.newMusic(Gdx.files.internal("tnt.mp3"));
            music.setVolume(0.5f);
            music.play();
        }
        // Reduce the target's health/durability
        target.takeDamage(bird.getDamage());


        // Check if the target is destroyed
        if (wood.isDestroyed()) {
            System.out.println(target.getName() + " has been destroyed!");
            isWood = false;
            target.triggerDestroyedEffect();
            target.removeGameObject();
        }
        if (stone.isDestroyed()) {
            System.out.println(target.getName() + " has been destroyed!");
            isStone = false;
            target.triggerDestroyedEffect();
            target.removeGameObject();
        }
        if (glass.isDestroyed()) {
            System.out.println(target.getName() + " has been destroyed!");
            isGlass = false;
            target.triggerDestroyedEffect();
            target.removeGameObject();
        }
        if (tnt.isDestroyed()) {
            System.out.println(target.getName() + " has been destroyed!");
            isTNT = false;
            target.triggerDestroyedEffect();
            target.removeGameObject();
        }
        if (kingPig.isDestroyed()) {
            System.out.println(target.getName() + " has been destroyed!");
            isKingPig = false;
            target.triggerDestroyedEffect();
            target.removeGameObject();
        }
        if (pig1.isDestroyed()) {
            System.out.println(target.getName() + " has been destroyed!");
            isPig1 = false;
            target.triggerDestroyedEffect();
            target.removeGameObject();
        }
        if (pig2.isDestroyed()) {
            System.out.println(target.getName() + " has been destroyed!");
            isPig2 = false;
            target.triggerDestroyedEffect();
            target.removeGameObject();
        }
        bird.reset();
    }

    // InputProcessor for handling the slingshot dragging behavior
    private class SlingShotInputProcessor implements com.badlogic.gdx.InputProcessor {
        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            if (button == 0) {
                // Convert screen coordinates to world coordinates
                Vector2 mousePos = new Vector2(screenX, Gdx.graphics.getHeight() - screenY); // Invert Y axis

                // Start dragging both lines
                isDragging1 = true;
                isDragging2 = true;

                // Update the end points to where the mouse is clicked
                endPoint1.set(mousePos);
                endPoint2.set(mousePos);
            }
            return true;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            Vector2 mousePos = new Vector2(screenX, Gdx.graphics.getHeight() - screenY); // Invert Y axis

            // If dragging the first line, update its end point
            if (isDragging1) {
                endPoint1.set(mousePos);
            }

            // If dragging the second line, update its end point
            if (isDragging2) {
                endPoint2.set(mousePos);
            }

            return true;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            // Stop dragging the first or second line when the left mouse button is released
            if (button == 0) {
                isDragging1 = false;
                isDragging2 = false;

                // Launch the bird based on the drag distance
                Vector2 dragVector = new Vector2(endPoint1.x - startPoint1.x, endPoint1.y - startPoint1.y);
                float velocityX = dragVector.x * 2;  // Adjust multiplier for speed
                float velocityY = dragVector.y * 2;  // Adjust multiplier for speed

                // Launch the selected bird
                switch (currentBird) {
                    case RED:
                        redBird.launch(velocityX, velocityY);
                        break;
                    case YELLOW:
                        yellowBird.launch(velocityX, velocityY);
                        break;
                    case BLACK:
                        blackBird.launch(velocityX, velocityY);
                        break;
                    case BLUE:
                        blueBird.launch(velocityX, velocityY);
                        break;
                }
            }
            return true;
        }

        @Override
        public boolean touchCancelled(int i, int i1, int i2, int i3) {
            return false;
        }

        // Unused InputProcessor methods (but they must be implemented)
        @Override
        public boolean keyDown(int keycode) {
            switch (keycode) {
                case Input.Keys.NUM_1:
                    currentBird = BirdType.RED;
                    break;
                case Input.Keys.NUM_2:
                    currentBird = BirdType.YELLOW;
                    break;
                case Input.Keys.NUM_3:
                    currentBird = BirdType.BLACK;
                    break;
                case Input.Keys.NUM_4:
                    currentBird = BirdType.BLUE;
                    break;
            }
            return true;
        }

        @Override
        public boolean keyUp(int keycode) { return false; }

        @Override
        public boolean keyTyped(char character) { return false; }

        @Override
        public boolean mouseMoved(int screenX, int screenY) { return false; }

        @Override
        public boolean scrolled(float amountX, float amountY) { return false; }
    }


    private void input() {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            float mouseX = Gdx.input.getX();
            float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

            if (mouseX >= 20 && mouseX <= 270 && mouseY >= Gdx.graphics.getHeight() - 150 && mouseY <= Gdx.graphics.getHeight()) {
                main.setScreen(new LevelScreen(spriteBatch, main));
            }

            float pauseX = (Gdx.graphics.getWidth() - 50) / 2f;
            float pauseY = Gdx.graphics.getHeight() - 70;
            if (mouseX >= pauseX && mouseX <= pauseX + 50 && mouseY >= pauseY && mouseY <= pauseY + 50) {
                isPaused = !isPaused;
                if (isPaused) {
                    System.out.println("Game is paused");
                    Pause pauseMenu = new Pause(main, spriteBatch, new Level1(main, spriteBatch));
                    main.setScreen(pauseMenu);
                }
            }
//            if (!redBird.isLaunched() && mouseX >= redBird.getX() && mouseX <= redBird.getX() + 100 && mouseY >= redBird.getY() && mouseY <= redBird.getY() + 100) {
//                redBird.launch();
//            }
//            if (!blueBird.isLaunched() && mouseX >= blueBird.getX() && mouseX <= blueBird.getX() + 100 && mouseY >= blueBird.getY() && mouseY <= blueBird.getY() + 100) {
//                blueBird.launch();
//            }
//            if (!blackBird.isLaunched() && mouseX >= blackBird.getX() && mouseX <= blackBird.getX() + 100 && mouseY >= blackBird.getY() && mouseY <= blackBird.getY() + 100) {
//                blackBird.launch();
//            }
//            if (!yellowBird.isLaunched() && mouseX >= yellowBird.getX() && mouseX <= yellowBird.getX() + 100 && mouseY >= yellowBird.getY() && mouseY <= yellowBird.getY() + 100) {
//                yellowBird.launch();
//            }
        }
    }

    private boolean isMouseOverButton(float mouseX, float mouseY, float buttonX, float buttonY, float buttonWidth, float buttonHeight) {
        float adjustedMouseY = Gdx.graphics.getHeight() - mouseY;
        return mouseX >= buttonX && mouseX <= buttonX + buttonWidth && adjustedMouseY >= buttonY && adjustedMouseY <= buttonY + buttonHeight;
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        super.dispose();
        font.dispose();
    }
}





