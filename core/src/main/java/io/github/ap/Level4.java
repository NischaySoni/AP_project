package io.github.ap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Level4 extends Levels {

    private boolean isPaused = false;
    private Music music;
    private BitmapFont font;
    private RedBird redBird;
    private BlueBird blueBird;
    private BlackBird blackBird;
    private YellowBird yellowBird;
    private SlingShot slingShot;
    private KingPig kingPig;
    private Glass glass;
    private Wood wood;
    private Stone stone;
    private TNT tnt;
    private int score = 0;
    private boolean isWood = true;
    private boolean isStone = true;
    private boolean isGlass = true;
    private boolean isKingPig = true;
    private boolean isTNT = true;


    public Level4(Main main, SpriteBatch spriteBatch) {
        super(main, spriteBatch);
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

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        if (isPaused) {
            return;
        }

        if (redBird.isLaunched()) {
            redBird.update(delta);
            if (isKingPig && redBird.checkCollision(kingPig.getX(), kingPig.getY(), kingPig.getWidth(), kingPig.getHeight())) {
                System.out.println("Red Bird hit the King Pig!");
                kingPig.takeDamage();
                handleCollision(redBird, kingPig);
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
            if (isKingPig && blueBird.checkCollision(kingPig.getX(), kingPig.getY(), kingPig.getWidth(), kingPig.getHeight())){
                System.out.println("Blue Bird hit the King Pig!");
                kingPig.takeDamage();
                handleCollision(blueBird, kingPig);
                blueBird.reset();
            }
            if (isWood && blueBird.checkCollision(wood.getX(), wood.getY(), wood.getWidth(), wood.getHeight())) {
                System.out.println("Blue Bird hit the Wood!");
                wood.takeDamage();
                handleCollision(blueBird, wood);
                blueBird.reset();
            }
            if (isGlass && blueBird.checkCollision(glass.getX(), glass.getY(), glass.getWidth(), glass.getHeight())) {
                System.out.println("Blue Bird hit the Glass!");
                glass.takeDamage();
                handleCollision(blueBird, glass);
                blueBird.reset();
            }
            if (isStone && blueBird.checkCollision(stone.getX(), stone.getY(), stone.getWidth(), stone.getHeight())) {
                System.out.println("Blue Bird hit the Stone!");
                stone.takeDamage();
                handleCollision(blueBird, stone);
                blueBird.reset();
            }
            if (isTNT && blueBird.checkCollision(tnt.getX(), tnt.getY(), tnt.getWidth(), tnt.getHeight())) {
                System.out.println("Blue Bird hit the tnt!");
                tnt.takeDamage();
                handleCollision(blueBird, tnt);
                blueBird.reset();
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
            yellowBird.update(delta);
            if (isKingPig && yellowBird.checkCollision(kingPig.getX(), kingPig.getY(), kingPig.getWidth(), kingPig.getHeight())) {
                System.out.println("Yellow Bird hit the King Pig!");
                kingPig.takeDamage();
                handleCollision(yellowBird, kingPig);
                yellowBird.reset();
            }
            if (isWood && yellowBird.checkCollision(wood.getX(), wood.getY(), wood.getWidth(), wood.getHeight())) {
                System.out.println("Yellow Bird hit the Wood!");
                wood.takeDamage();
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

        spriteBatch.draw(levelTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spriteBatch.draw(slingShotTexture, 400, 200, 150, 300);
        spriteBatch.draw(redTexture, redBird.getX(), redBird.getY(), 100, 100);
        spriteBatch.draw(chuckTexture, yellowBird.getX(), yellowBird.getY(), 100, 100);
        spriteBatch.draw(bluesTexture, blueBird.getX(), blueBird.getY(), 70, 70);
        spriteBatch.draw(bombTexture, blackBird.getX(), blackBird.getY(), 130, 130);
        if (isKingPig) {
            spriteBatch.draw(kingPigTexture, kingPig.getX(), kingPig.getY(), kingPig.getWidth(), kingPig.getHeight());
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
        bird.reset();
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
                    Pause pauseMenu = new Pause(main, spriteBatch, new Level4(main, spriteBatch));
                    main.setScreen(pauseMenu);
                }
            }
            if (!redBird.isLaunched() && mouseX >= redBird.getX() && mouseX <= redBird.getX() + 100 && mouseY >= redBird.getY() && mouseY <= redBird.getY() + 100) {
                redBird.launch();
            }
            if (!blueBird.isLaunched() && mouseX >= blueBird.getX() && mouseX <= blueBird.getX() + 100 && mouseY >= blueBird.getY() && mouseY <= blueBird.getY() + 100) {
                blueBird.launch();
            }
            if (!blackBird.isLaunched() && mouseX >= blackBird.getX() && mouseX <= blackBird.getX() + 100 && mouseY >= blackBird.getY() && mouseY <= blackBird.getY() + 100) {
                blackBird.launch();
            }
            if (!yellowBird.isLaunched() && mouseX >= yellowBird.getX() && mouseX <= yellowBird.getX() + 100 && mouseY >= yellowBird.getY() && mouseY <= yellowBird.getY() + 100) {
                yellowBird.launch();
            }
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
