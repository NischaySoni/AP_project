package io.github.ap;

public abstract class GameObject {
    private float x, y, width, height;
    private int health; // Durability or health points
    private String name;
    public GameObject(float x, float y, float width, float height, int health, String name) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.health = health;
        this.name = name;
    }

    // Getters and setters for position and dimensions
    public float getX() { return x; }
    public float getY() { return y; }
    public float getWidth() { return width; }
    public float getHeight() { return height; }
    public String getName() { return name; }

    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }

    // Handle damage
    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) health = 0;
        System.out.println(name + " took " + damage + " damage. Remaining health: " + health);
    }

    // Return current health or damage remaining
    public int getDamage() {
        return health;
    }

    // Check if destroyed
    public boolean isDestroyed() {
        return health <= 0;
    }

    // Trigger destruction effects (to be overridden by subclasses if needed)
    public void triggerDestroyedEffect() {
        System.out.println(name + " is destroyed! Playing destruction effect.");
    }

    // Remove the object (to be implemented in the game engine)
    public void removeGameObject() {
        System.out.println(name + " removed from the game.");
    }


    // Reset the object to its initial state
    public void reset() {
        this.health = 100; // Example: reset health to full
        System.out.println(name + " has been reset to its initial state.");
    }


}

