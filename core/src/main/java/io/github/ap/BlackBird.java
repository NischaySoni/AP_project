package io.github.ap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class BlackBird extends GameObject {
    private float x;
    private float y;
    private float initialVelocityX;
    private float initialVelocityY;
    private float gravity;
    private float timeSinceLaunch;
    private boolean isLaunched;

    // Constants
    private static final float START_X = 370;
    private static final float START_Y = 400;
    private static final float DEFAULT_VELOCITY_X = 450;
    private static final float DEFAULT_VELOCITY_Y = 500;
    private static final float GRAVITY = -9.8f;

    // Variable to control the collision range
    private float collisionRange = 1;  // Normal collision range

    public BlackBird() {
        super(START_X, START_Y, 130, 130, 5, "BlackBird");
        reset();
    }

    // Launch the bird with specified velocity
    public void launch() {
        if (!isLaunched) {
            this.initialVelocityX = DEFAULT_VELOCITY_X;
            this.initialVelocityY = DEFAULT_VELOCITY_Y;
            this.isLaunched = true;
            this.timeSinceLaunch = 0;
        }
    }

    // Reset the bird's position
    public void reset() {
        this.x = START_X;
        this.y = START_Y;
        this.isLaunched = false;
        this.timeSinceLaunch = 0;
        this.initialVelocityX = 0;
        this.initialVelocityY = 0;
    }

    // Update bird's position using projectile motion
    public void update(float delta) {
        if (isLaunched) {
            timeSinceLaunch += delta;

            // Update position based on velocity and gravity
            x += initialVelocityX * delta;
            y += initialVelocityY * delta + 0.5f * GRAVITY * (timeSinceLaunch * timeSinceLaunch);
            initialVelocityY += GRAVITY * delta;

            // If bird falls below the ground level, reset it
            if (y < 0) {
                reset();
            }

            // Increase collision range if space bar is pressed
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                collisionRange = 5;  // Increase the collision range
            }
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public boolean isLaunched() {
        return isLaunched;
    }

    private boolean isColliding(float x1, float y1, float width1, float height1, float x2, float y2, float width2, float height2) {
        return x1 < x2 + width2*collisionRange &&
            x1 + width1 > x2 &&
            y1 < y2 + height2*collisionRange &&
            y1 + height1 > y2;
    }

    public boolean checkCollision(float objectX, float objectY, float objectWidth, float objectHeight) {
        // Bird's width and height can also be affected by collisionRange
        float birdWidth = 10 * collisionRange;
        float birdHeight = 10 * collisionRange;

        return isColliding(x, y, birdWidth, birdHeight, objectX, objectY, objectWidth, objectHeight);
    }
}
