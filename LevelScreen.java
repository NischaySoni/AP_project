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
        levelscreen.setLayout(new BorderLayout());

        ImageIcon logoIcon = new ImageIcon("logo2.png");
        levelscreen.setIconImage(logoIcon.getImage());

        JPanel levelPanel = new JPanel();
        levelPanel.setLayout(new GridLayout(9, 9, 50, 50)); 

        JButton level1Button = new JButton("Level 1");
        JButton level2Button = new JButton("Level 2");
        JButton level3Button = new JButton("Level 3");

        customizeLevelButton(level1Button);
        customizeLevelButton(level2Button);
        customizeLevelButton(level3Button);

        level1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Level 1 selected");
            }
        });

        level2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Level 2 selected");
            }
        });

        level3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Level 3 selected");
            }
        });

        levelPanel.add(level1Button);
        levelPanel.add(level2Button);
        levelPanel.add(level3Button);

        levelscreen.add(levelPanel, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Comic Sans", Font.BOLD, 20));
        backButton.setForeground(Color.green);
        backButton.setBackground(Color.black);
        backButton.setPreferredSize(new Dimension(100, 50)); 
        backButton.setFocusable(false);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Back to main screen");
                pvrframe.setVisible(true);
                levelscreen.setVisible(false);
            }
        });

        levelscreen.add(backButton, BorderLayout.SOUTH);
        levelscreen.setVisible(true);
    }

    private void customizeLevelButton(JButton button) {
        button.setFont(new Font("Comic Sans", Font.BOLD, 15)); 
        button.setForeground(Color.white);
        button.setBackground(Color.blue);
        button.setFocusable(false);
        button.setPreferredSize(new Dimension(5, 5)); 
    }
}
