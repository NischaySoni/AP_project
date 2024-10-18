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
        levelscreen.setLayout(new GridLayout()); 

        ImageIcon logoIcon = new ImageIcon("logo2.png");
        levelscreen.setIconImage(logoIcon.getImage());
    
        BackHomeButton button = new BackHomeButton("Back");
        button.setFont(new Font("Comic Sans", Font.BOLD, 25));
        button.setForeground(Color.green);
        button.setBackground(Color.black);
        button.setPreferredSize(new Dimension(200, 100));
        button.setFocusable(false);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = GridBagConstraints.RELATIVE;
        c.insets = new Insets(0, 0, 0, 0);
        c.anchor = GridBagConstraints.PAGE_END;
        levelscreen.add(button, c);

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
