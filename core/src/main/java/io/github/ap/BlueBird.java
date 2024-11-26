package io.github.ap;

import java.util.ArrayList;

public class BlueBird extends GameObject {
    private float x;
    private float y;
    private float initialVelocityX;
    private float initialVelocityY;
    private float gravity;
    private float timeSinceLaunch;
    private boolean isLaunched;

    private static final float START_X = 100;
    private static final float START_Y = 200;
    private static final float DEFAULT_VELOCITY_X = 450;
    private static final float DEFAULT_VELOCITY_Y = 500;
    private static final float GRAVITY = -9.8f;

    private ArrayList<BlueBirdFragment> fragments;

    public BlueBird() {
        super(START_X, START_Y, 70, 70, 2, "BlueBird");
        reset();
    }

    public void launch() {
        if (!isLaunched) {
            this.initialVelocityX = DEFAULT_VELOCITY_X;
            this.initialVelocityY = DEFAULT_VELOCITY_Y;
            this.isLaunched = true;
            this.timeSinceLaunch = 0;

            // Initialize fragments if they haven't been initialized yet
            if (fragments == null) {
                fragments = new ArrayList<>();
            }

            // Create three fragments
            fragments.clear(); // Clear any existing fragments before adding new ones
            fragments.add(new BlueBirdFragment(x, y, initialVelocityX - 100, initialVelocityY + 50));
            fragments.add(new BlueBirdFragment(x, y, initialVelocityX, initialVelocityY));
            fragments.add(new BlueBirdFragment(x, y, initialVelocityX + 100, initialVelocityY - 50));
        }
    }

    public void multiply() {
        // Create 3 new BlueBird fragments with varying velocities
        fragments.clear(); // Clear any existing fragments before multiplying
        fragments.add(new BlueBirdFragment(x, y, initialVelocityX - 100, initialVelocityY + 50));
        fragments.add(new BlueBirdFragment(x, y, initialVelocityX, initialVelocityY));
        fragments.add(new BlueBirdFragment(x, y, initialVelocityX + 100, initialVelocityY - 50));
    }

    public boolean isLaunched() {
        return isLaunched;
    }

    public ArrayList<BlueBirdFragment> getFragments() {
        return fragments;
    }

    public void update(float delta) {
        if (isLaunched) {
            ArrayList<BlueBirdFragment> toRemove = new ArrayList<>();
            for (BlueBirdFragment fragment : fragments) {
                fragment.updateFragment(delta);
                if (fragment.getY() < 0) {
                    toRemove.add(fragment);
                }
            }
            fragments.removeAll(toRemove);

            // Reset if all fragments are gone
            if (fragments.isEmpty()) {
                reset();
            }
        }
    }

    public boolean checkCollision(float objectX, float objectY, float objectWidth, float objectHeight) {
        for (BlueBirdFragment fragment : fragments) {
            if (fragment.isColliding(objectX, objectY, objectWidth, objectHeight)) {
                return true;
            }
        }
        return false;
    }

    public void reset() {
        this.x = START_X;
        this.y = START_Y;
        this.isLaunched = false;
        this.timeSinceLaunch = 0;
        this.initialVelocityX = 0;
        this.initialVelocityY = 0;
        this.fragments = new ArrayList<>();
    }
}
