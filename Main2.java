package io.github.ap.project;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Main2 {
    public static void main(String[] args) {
        // Create a configuration for the application
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("AngryBird"); // Set window title
        config.setWindowedMode(480, 320); // Set window size (width, height)
        config.setResizable(true); // Allow resizing
        config.useVsync(true); // Enable VSync

        // Start the application with the AngryBird instance and configuration
        new Lwjgl3Application(new AngryBird(), config);
    }
}
