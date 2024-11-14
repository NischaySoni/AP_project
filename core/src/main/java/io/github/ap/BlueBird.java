package io.github.ap;

public class BlueBird {
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

    // Constants for initial position and velocity
    private static final float START_X = 100;
    private static final float START_Y = 200;
    private static final float DEFAULT_VELOCITY_X = 300; // Adjust as needed
    private static final float DEFAULT_VELOCITY_Y = 500; // Adjust as needed
    private static final float GRAVITY = -9.8f;

    public BlueBird() {
        // Initialize with default values
        reset();
    }

    // Launch the bird with a specified velocity
    public void launch() {
        if (!isLaunched) {
            this.initialVelocityX = DEFAULT_VELOCITY_X;
            this.initialVelocityY = DEFAULT_VELOCITY_Y;
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
    }

    // Update the bird's position based on projectile motion
    public void update(float delta) {
        if (isLaunched) {
            timeSinceLaunch += delta;

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

    public boolean isLaunched() {
        return isLaunched;
    }
}
