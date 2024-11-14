package io.github.ap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Level1 extends Levels {

    private boolean isPaused = false;
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

    public Level1(Main main, SpriteBatch spriteBatch) {
        super(main, spriteBatch);
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(3);
        redBird = new RedBird();
        blueBird = new BlueBird();
        blackBird = new BlackBird();
        yellowBird = new YellowBird();
        slingShot = new SlingShot();
        kingPig = new KingPig();
        glass = new Glass();
        wood = new Wood();
        stone = new Stone();
        tnt = new TNT();
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        if (isPaused) {
            return;
        }

        if (redBird.isLaunched()) {
            redBird.update(delta);
        }
        if (blueBird.isLaunched()) {
            blueBird.update(delta);
        }
        if (blackBird.isLaunched()) {
            blackBird.update(delta);
        }
        if (yellowBird.isLaunched()) {
            yellowBird.update(delta);
        }
        spriteBatch.begin();

        spriteBatch.draw(levelTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spriteBatch.draw(slingShotTexture, 400, 200, 150, 300);
        spriteBatch.draw(redTexture, redBird.getX(), redBird.getY(), 100, 100);
        spriteBatch.draw(chuckTexture, yellowBird.getX(), yellowBird.getY(), 100, 100);
        spriteBatch.draw(bluesTexture, blueBird.getX(), blueBird.getY(), 70, 70);
        spriteBatch.draw(bombTexture, blackBird.getX(), blackBird.getY(), 130, 130);
        spriteBatch.draw(kingPigTexture, 1300, 200, 160, 160);
        spriteBatch.draw(woodTexture, 1020, 400, 700, 50);
        spriteBatch.draw(glassTexture, 1200, 200, 50, 200);
        spriteBatch.draw(stoneTexture, 1500, 200, 50, 200);
        spriteBatch.draw(tntTexture, 1300, 450, 150, 70);

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

        String text = "Score: 0";
        float textX = Gdx.graphics.getWidth() - font.getRegion().getRegionWidth() - 20;
        float textY = Gdx.graphics.getHeight() - 20;
        font.draw(spriteBatch, text, textX, textY);

        spriteBatch.end();

        input();
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
                    Pause pauseMenu = new Pause(main, spriteBatch, this);
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
