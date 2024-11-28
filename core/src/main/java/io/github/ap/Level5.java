package io.github.ap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class Level5 extends Levels {
    private ShapeRenderer shapeRenderer;
    private Vector2 startPoint1, endPoint1;
    private Vector2 startPoint2, endPoint2;
    private boolean isDragging1, isDragging2;

    private Texture slingShotTexture;  // Texture for slingshot band
    private float slingshotX, slingshotY;

    // The MyRed bird
    private MyRed myRed;

    public Level5(Main main, SpriteBatch spriteBatch) {
        super(main, spriteBatch);
    }

    @Override
    public void show() {
        // Initialize ShapeRenderer for drawing lines
        shapeRenderer = new ShapeRenderer();

        // Load the slingshot texture
        slingShotTexture = new Texture("slingShot.png");

        // Set input processor to handle mouse input for slingshot
        Gdx.input.setInputProcessor(new SlingShotInputProcessor());

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

        // Initialize MyRed bird object
        myRed = new MyRed();
    }

    @Override
    public void render(float delta) {
        // Clear the screen and draw the level background
        ScreenUtils.clear(0, 0, 0, 1);

        // Draw the level background image
        spriteBatch.begin();
        spriteBatch.draw(levelTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spriteBatch.end();

        // Draw the static slingshot texture (this is your stationary slingshot band)
        drawStaticSlingshot();

        // Draw the slingshot lines using ShapeRenderer
        drawSlingshotLines();

        // Draw the dynamic slingshot textures (bands) on top of the static one
        drawSlingshotTextures();

        // Update and draw the MyRed bird
        myRed.update(delta);
        spriteBatch.begin();
        spriteBatch.draw(myRed.getBirdTexture(), myRed.getX(), myRed.getY()); // Draw the bird
        spriteBatch.end();
    }

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
    public void hide() {
        // Dispose of the ShapeRenderer and textures when the screen is hidden
        shapeRenderer.dispose();
        slingShotTexture.dispose();
        myRed.dispose(); // Dispose of bird's texture
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
                float velocityX = dragVector.x * 5;  // Adjust multiplier for speed
                float velocityY = dragVector.y * 5;  // Adjust multiplier for speed

                // Launch the bird
                myRed.launch(velocityX, velocityY);
            }
            return true;
        }

        @Override
        public boolean touchCancelled(int i, int i1, int i2, int i3) {
            return false;
        }

        // Unused InputProcessor methods (but they must be implemented)
        @Override
        public boolean keyDown(int keycode) { return false; }

        @Override
        public boolean keyUp(int keycode) { return false; }

        @Override
        public boolean keyTyped(char character) { return false; }

        @Override
        public boolean mouseMoved(int screenX, int screenY) { return false; }

        @Override
        public boolean scrolled(float amountX, float amountY) { return false; }
    }
}

//package io.github.ap;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
//import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.utils.ScreenUtils;
//
//public class Level5 extends Levels {
//    private ShapeRenderer shapeRenderer;
//    private Vector2 startPoint1, endPoint1;
//    private Vector2 startPoint2, endPoint2;
//    private boolean isDragging1, isDragging2;
//
//    private Texture slingShotTexture;  // Texture for slingshot band
//    private float slingshotX, slingshotY;
//
//    // The MyRed bird
//    private MyRed myRed;
//
//    // The Ground object
//    private Ground ground;
//
//    public Level5(Main main, SpriteBatch spriteBatch) {
//        super(main, spriteBatch);
//    }
//
//    @Override
//    public void show() {
//        // Initialize ShapeRenderer for drawing lines
//        shapeRenderer = new ShapeRenderer();
//
//        // Load the slingshot texture
//        slingShotTexture = new Texture("slingShot.png");
//
//        // Set input processor to handle mouse input for slingshot
//        Gdx.input.setInputProcessor(new SlingShotInputProcessor());
//
//        // Position for slingshot texture
//        slingshotX = Gdx.graphics.getWidth() / 5f;  // Adjust X to move it to the left (1/5th of the screen width)
//        slingshotY = Gdx.graphics.getHeight() / 4f;  // Adjust Y to move it lower (1/4th of the screen height)
//
//        // Calculate the top position of the slingshot texture
//        float slingshotTopY = slingshotY + slingShotTexture.getHeight() / 2f;  // Top of the texture
//
//        // Initialize the start and end points of the slingshot lines to the top of the slingshot
//        startPoint1 = new Vector2(slingshotX, slingshotTopY); // Slingshot anchor point 1 (top of texture)
//        endPoint1 = new Vector2(startPoint1); // Initially the same as start
//
//        startPoint2 = new Vector2(slingshotX + slingShotTexture.getWidth(), slingshotTopY); // Slingshot anchor point 2 (right side of texture, top)
//        endPoint2 = new Vector2(startPoint2); // Initially the same as start
//
//        // Initialize MyRed bird object
//        myRed = new MyRed();
//
//        // Initialize the Ground object
//        ground = new Ground(main.world);
//    }
//
//    @Override
//    public void render(float delta) {
//        // Clear the screen and draw the level background
//        ScreenUtils.clear(0, 0, 0, 1);
//
//        // Draw the level background image
//        spriteBatch.begin();
//        spriteBatch.draw(levelTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        spriteBatch.end();
//
//        // Draw the Ground object
//        ground.render(spriteBatch);  // Render the ground texture
//
//        // Draw the static slingshot texture (this is your stationary slingshot band)
//        drawStaticSlingshot();
//
//        // Draw the slingshot lines using ShapeRenderer
//        drawSlingshotLines();
//
//        // Draw the dynamic slingshot textures (bands) on top of the static one
//        drawSlingshotTextures();
//
//        // Update and draw the MyRed bird
//        myRed.update(delta);
//        spriteBatch.begin();
//        spriteBatch.draw(myRed.getBirdTexture(), myRed.getX(), myRed.getY()); // Draw the bird
//        spriteBatch.end();
//    }
//
//    private void drawStaticSlingshot() {
//        // Draw the static slingshot image at the given position
//        spriteBatch.begin();
//        spriteBatch.draw(slingShotTexture, slingshotX - slingShotTexture.getWidth() / 2, slingshotY - slingShotTexture.getHeight() / 2);
//        spriteBatch.end();
//    }
//
//    private void drawSlingshotLines() {
//        shapeRenderer.setProjectionMatrix(main.viewport.getCamera().combined);
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//
//        // Draw the first line if dragging
//        if (isDragging1) {
//            shapeRenderer.line(startPoint1.x, startPoint1.y, endPoint1.x, endPoint1.y);
//        }
//
//        // Draw the second line if dragging
//        if (isDragging2) {
//            shapeRenderer.line(startPoint2.x, startPoint2.y, endPoint2.x, endPoint2.y);
//        }
//
//        shapeRenderer.end();
//    }
//
//    private void drawSlingshotTextures() {
//        spriteBatch.begin();
//
//        // Draw first slingshot band texture (from startPoint1 to endPoint1)
//        if (isDragging1) {
//            float angle1 = (float) Math.atan2(endPoint1.y - startPoint1.y, endPoint1.x - startPoint1.x); // Angle for rotation
//            float distance1 = startPoint1.dst(endPoint1); // Distance between the points (length of the band)
//
//            // Draw the first texture stretched along the angle, applying rotation and scaling
//            spriteBatch.draw(slingShotTexture, startPoint1.x, startPoint1.y,
//                0, slingShotTexture.getHeight() / 2,
//                distance1, slingShotTexture.getHeight(),
//                1, 1
//            );
//        }
//
//        // Draw second slingshot band texture (from startPoint2 to endPoint2)
//        if (isDragging2) {
//            float angle2 = (float) Math.atan2(endPoint2.y - startPoint2.y, endPoint2.x - startPoint2.x); // Angle for rotation
//            float distance2 = startPoint2.dst(endPoint2); // Distance between the points (length of the band)
//
//            // Draw the second texture stretched along the angle, applying rotation and scaling
//            spriteBatch.draw(slingShotTexture, startPoint2.x, startPoint2.y,
//                0, slingShotTexture.getHeight() / 2,
//                distance2, slingShotTexture.getHeight(),
//                1, 1
//            );
//        }
//
//        spriteBatch.end();
//    }
//
//    @Override
//    public void hide() {
//        // Dispose of the ShapeRenderer and textures when the screen is hidden
//        shapeRenderer.dispose();
//        slingShotTexture.dispose();
//        myRed.dispose(); // Dispose of bird's texture
//        ground = null;  // Dispose of the ground object
//    }
//
//    // InputProcessor for handling the slingshot dragging behavior
//    private class SlingShotInputProcessor implements com.badlogic.gdx.InputProcessor {
//        @Override
//        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//            if (button == 0) {
//                // Convert screen coordinates to world coordinates
//                Vector2 mousePos = new Vector2(screenX, Gdx.graphics.getHeight() - screenY); // Invert Y axis
//
//                // Start dragging both lines
//                isDragging1 = true;
//                isDragging2 = true;
//
//                // Update the end points to where the mouse is clicked
//                endPoint1.set(mousePos);
//                endPoint2.set(mousePos);
//            }
//            return true;
//        }
//
//        @Override
//        public boolean touchDragged(int screenX, int screenY, int pointer) {
//            Vector2 mousePos = new Vector2(screenX, Gdx.graphics.getHeight() - screenY); // Invert Y axis
//
//            // If dragging the first line, update its end point
//            if (isDragging1) {
//                endPoint1.set(mousePos);
//            }
//
//            // If dragging the second line, update its end point
//            if (isDragging2) {
//                endPoint2.set(mousePos);
//            }
//
//            return true;
//        }
//
//        @Override
//        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
//            // Stop dragging the first or second line when the left mouse button is released
//            if (button == 0) {
//                isDragging1 = false;
//                isDragging2 = false;
//
//                // Launch the bird based on the drag distance
//                Vector2 dragVector = new Vector2(endPoint1.x - startPoint1.x, endPoint1.y - startPoint1.y);
//                float velocityX = dragVector.x * 5;  // Adjust multiplier for speed
//                float velocityY = dragVector.y * 5;  // Adjust multiplier for speed
//
//                // Launch the bird
//                myRed.launch(velocityX, velocityY);
//            }
//            return true;
//        }
//
//        @Override
//        public boolean touchCancelled(int i, int i1, int i2, int i3) {
//            return false;
//        }
//
//        // Unused InputProcessor methods (but they must be implemented)
//        @Override
//        public boolean keyDown(int keycode) { return false; }
//
//        @Override
//        public boolean keyUp(int keycode) { return false; }
//
//        @Override
//        public boolean keyTyped(char character) { return false; }
//
//        @Override
//        public boolean mouseMoved(int screenX, int screenY) { return false; }
//
//        @Override
//        public boolean scrolled(float amountX, float amountY) { return false; }
//    }
//}
//
