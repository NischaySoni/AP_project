package io.github.ap;

import java.io.Serializable;

class GameState implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int health;

    public GameState(String name, int health) {
        this.name = name;
        this.health = health;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }

    @Override
    public String toString() {
        return name + " (Health: " + health + ")";
    }

    public void setMusicVolume(float volume) {
    }

    public float getMusicVolume() {
        return 0;
    }
}

// Bird class
class Bird extends GameState {
    private static final long serialVersionUID = 1L;

    public Bird(String name, int health) {
        super(name, health);
    }
}

// Structure class
class Structure extends GameState {
    private static final long serialVersionUID = 1L;

    public Structure(String name, int health) {
        super(name, health);
    }
}

// Pig class
class Pig extends GameState {
    private static final long serialVersionUID = 1L;

    public Pig(String name, int health) {
        super(name, health);
    }
}
