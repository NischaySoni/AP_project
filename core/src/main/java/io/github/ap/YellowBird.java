package io.github.ap;

public class YellowBird extends GameObject {
    // Position
    private float x;
    private float y;

    // Physics properties
    private float initialVelocityX;
    private float initialVelocityY;
    private float gravity;

    // Tracking time since launch
    private float timeSinceLaunch;
    private boolean isLaunched;
    private boolean isSpeedBoosted; // New flag for speed boost

    // Constants for initial position and velocity
    private static final float START_X = 200;
    private static final float START_Y = 200;
    private static final float DEFAULT_VELOCITY_X = 500; // Adjust as needed
    private static final float DEFAULT_VELOCITY_Y = 500; // Adjust as needed
    private static final float GRAVITY = -9.8f;

    public YellowBird() {
        // Initialize with default values
        super(START_X, START_Y, 100, 100, 3, "YellowBird");
        reset();
    }

    // Launch the bird with a specified velocity
    public void launch(float velocityX, float velocityY) {
        if (!isLaunched) {
            this.initialVelocityX = velocityX;
            this.initialVelocityY = velocityY;
            this.isLaunched = true;
            this.timeSinceLaunch = 0;
        }
    }

    // Reset bird to its initial position
    public void reset() {
        this.x = START_X;
        this.y = START_Y;
        this.isLaunched = false;
        this.timeSinceLaunch = 0;
        this.initialVelocityX = 0;
        this.initialVelocityY = 0;
        this.isSpeedBoosted = false; // Reset speed boost
    }

    // Update the bird's position based on projectile motion
    public void update(float delta) {
        if (isLaunched) {
            timeSinceLaunch += delta;

            // If speed boost is active, increase velocity
            if (isSpeedBoosted) {
                initialVelocityX = DEFAULT_VELOCITY_X * 1.5f; // Increase speed by 1.5x
                initialVelocityY = DEFAULT_VELOCITY_Y * 1.5f; // Increase speed by 1.5x
            }

            // Calculate new position using kinematic equations
            x += initialVelocityX * delta; // X position update
            y += initialVelocityY * delta + 0.5f * GRAVITY * (timeSinceLaunch * timeSinceLaunch); // Y position update
            initialVelocityY += GRAVITY * delta; // Gravity effect on vertical velocity

            // If the bird falls below the starting Y position, reset it
            if (y < 0) {
                reset();
            }
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float START_X){
        this.x = START_X;
    }

    public void setY(float START_Y){
        this.y = START_Y;
    }

    public boolean isLaunched() {
        return isLaunched;
    }

    public boolean isSpeedBoosted() {
        return isSpeedBoosted;
    }

    private boolean isColliding(float x1, float y1, float width1, float height1, float x2, float y2, float width2, float height2) {
        return x1 < x2 + width2 &&
            x1 + width1 > x2 &&
            y1 < y2 + height2 &&
            y1 + height1 > y2;
    }

    public boolean checkCollision(float objectX, float objectY, float objectWidth, float objectHeight) {
        float birdWidth = 10;
        float birdHeight = 10;
        return isColliding(x, y, birdWidth, birdHeight, objectX, objectY, objectWidth, objectHeight);
    }

    // Method to activate speed boost (spacebar detection)
    public void activateSpeedBoost() {
        this.isSpeedBoosted = true;
    }

    // Method to deactivate speed boost (spacebar release detection)
    public void deactivateSpeedBoost() {
        this.isSpeedBoosted = false;
    }
}
