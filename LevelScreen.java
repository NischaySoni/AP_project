import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LevelScreen {
    private JFrame pvrframe;

    public LevelScreen(JFrame pvrframe) {
        this.pvrframe = pvrframe;

        JFrame levelscreen = new JFrame("Levels");
        levelscreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        levelscreen.setUndecorated(true); 
        device.setFullScreenWindow(levelscreen);
        levelscreen.setLayout(null); 

        ImageIcon windowIcon = new ImageIcon("windowIcon.png"); 
        levelscreen.setIconImage(windowIcon.getImage()); 

    
        BackHomeButton button = new BackHomeButton("Back");
        button.setFont(new Font("Comic Sans", Font.BOLD, 25));
        button.setForeground(Color.black);
        button.setBackground(Color.green);
        button.setBounds(100, 100, 200, 100); 
        button.setFocusable(false);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Back to main screen");
                pvrframe.setVisible(true); 
                levelscreen.setVisible(false);
            }
        });

        levelscreen.add(button);
        levelscreen.setVisible(true);
    }
}
