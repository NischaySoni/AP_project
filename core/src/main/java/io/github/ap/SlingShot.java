package io.github.ap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;
import java.util.Queue;

public class SlingShot {
    private Vector2 position; // Position of the slingshot
    private Texture slingTexture; // Texture for the slingshot
    private Queue<Texture> birdQueue; // Queue for bird textures to be launched
    private Texture currentBirdTexture; // Texture of the currently loaded bird
    private Vector2 currentBirdVelocity; // Velocity of the current bird
    private boolean isPulledBack; // Whether the slingshot is pulled back
    private Vector2 launchPosition; // Position where bird launches from
    private Vector2 pullBackPosition; // Position when the slingshot is pulled back

    private static final float PULLBACK_DISTANCE = 50; // Maximum distance the slingshot can be pulled back

    public SlingShot(Texture slingTexture, float x, float y, Queue<Texture> birdTextures) {
        this.position = new Vector2(x, y);
        this.slingTexture = slingTexture;
        this.birdQueue = new LinkedList<>(birdTextures);
        birdTextures.add(new Texture("redBird"));
        birdTextures.add(new Texture("blueBird"));
        birdTextures.add(new Texture("blackBird"));
        birdTextures.add(new Texture("yellowBird"));
        this.currentBirdTexture = birdQueue.poll(); // Get the first bird texture
        this.isPulledBack = false;
        this.launchPosition = new Vector2(x + 30, y + 60); // Adjust based on sling design
        this.pullBackPosition = new Vector2();
        this.currentBirdVelocity = new Vector2(0, 0); // Initial velocity is zero
    }

    // Render the slingshot and the current bird
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.draw(slingTexture, position.x, position.y, 100, 150);

        if (currentBirdTexture != null) {
            float birdX = isPulledBack ? pullBackPosition.x : launchPosition.x;
            float birdY = isPulledBack ? pullBackPosition.y : launchPosition.y;
            spriteBatch.draw(currentBirdTexture, birdX, birdY, 50, 50); // Drawing the bird with its texture
        }
    }

    // Handle input for pulling and launching the bird
    public void handleInput(float mouseX, float mouseY, boolean isPressed, boolean isReleased) {
        if (currentBirdTexture == null) return;

        if (isPressed) {
            // Calculate pull-back position within the allowed range
            float dx = mouseX - launchPosition.x;
            float dy = mouseY - launchPosition.y;
            float distance = (float) Math.sqrt(dx * dx + dy * dy);
            if (distance > PULLBACK_DISTANCE) {
                float ratio = PULLBACK_DISTANCE / distance;
                dx *= ratio;
                dy *= ratio;
            }
            pullBackPosition.set(launchPosition.x + dx, launchPosition.y + dy);
            isPulledBack = true;
        }

        if (isReleased && isPulledBack) {
            // Launch the bird with velocity based on pull-back
            float velocityX = launchPosition.x - pullBackPosition.x;
            float velocityY = launchPosition.y - pullBackPosition.y;
            currentBirdVelocity.set(velocityX, velocityY); // Set bird's velocity based on the pull-back

            // After launching, switch to the next bird in the queue
            currentBirdTexture = birdQueue.poll(); // Load the next bird texture
            isPulledBack = false;
        }
    }

    // Get the current bird's texture
    public Texture getCurrentBirdTexture() {
        return currentBirdTexture;
    }

    // Get the velocity of the current bird
    public Vector2 getCurrentBirdVelocity() {
        return currentBirdVelocity;
    }

    public boolean hasBirdsLeft() {
        return currentBirdTexture != null || !birdQueue.isEmpty();
    }
}
