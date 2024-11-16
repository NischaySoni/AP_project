package io.github.ap;

public class KingPig {
    private float x, y, width, height;
    private int durability;

    public KingPig(float x, float y, float width, float height, int durability) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.durability = durability;
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

    public void takeDamage() {
        durability--;
        if (durability <= 0) {
            System.out.println("Wood is destroyed!");
            // Remove wood from the level
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

