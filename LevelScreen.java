import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class LevelScreen {
    public LevelScreen(){
        JFrame levelscreen = new JFrame("Levels");
        levelscreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        levelscreen.setUndecorated(true); // Remove the title bar
        device.setFullScreenWindow(levelscreen);
        levelscreen.setLayout(null);

        BackHomeButton button = new BackHomeButton("Back");

        levelscreen.setVisible(true);
    }
}
