package io.github.ap;

public class Glass extends GameObject {
    private float x, y, width, height;
    private int health;
    private Object texture;

    public Glass(float x, float y, float width, float height, int health, String name, Object glassTexture) {
        super(x, y, width, height, health, name);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.health = health;
        this.texture = glassTexture;
    }

    private boolean isColliding(float x1, float y1, float width1, float height1, float x2, float y2, float width2, float height2) {
        return x1 < x2 + width2 &&
            x1 + width1 > x2 &&
            y1 < y2 + height2 &&
            y1 + height1 > y2;
    }

    public boolean checkCollision(float birdX, float birdY, float birdWidth, float birdHeight) {
        return isColliding(x, y, width, height, birdX, birdY, birdWidth, birdHeight);
    }

    @Override
    public void triggerDestroyedEffect() {
        System.out.println(getName() + " breaks into splinters!");
    }

    public void takeDamage() {
        health--;
        if (health <= 0) {
            System.out.println("Glass is destroyed!");
            triggerDestroyedEffect();
            removeWoodTexture();
        }
    }

    private void removeWoodTexture() {
        if (texture != null) {
            System.out.println("Removing glass texture.");
            texture = null;
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
