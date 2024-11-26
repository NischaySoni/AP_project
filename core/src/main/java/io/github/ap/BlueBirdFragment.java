package io.github.ap;

public class BlueBirdFragment extends GameObject{
    private float x;
    private float y;
    private float initialVelocityX;
    private float initialVelocityY;
    private float gravity;
    private float timeSinceLaunch;

    private static final float GRAVITY = -9.8f;

    public BlueBirdFragment(float startX, float startY, float velocityX, float velocityY) {
        super(startX, startY, 40, 40, 2, "blue bird fragment");
        this.initialVelocityX = velocityX;
        this.initialVelocityY = velocityY;
        this.gravity = GRAVITY;
        this.timeSinceLaunch = 0;
    }

    public void updateFragment(float delta) {
        timeSinceLaunch += delta;
        x += initialVelocityX * delta;
        y += initialVelocityY * delta + 0.5f * gravity * (timeSinceLaunch * timeSinceLaunch);
        initialVelocityY += gravity * delta;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public boolean isColliding(float objectX, float objectY, float objectWidth, float objectHeight) {
        return x < objectX + objectWidth &&
            x + 10 > objectX &&
            y < objectY + objectHeight &&
            y + 10 > objectY;
    }
}
