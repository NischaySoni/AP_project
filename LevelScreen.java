import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class LevelScreen {
    private JFrame pvrframe;

    public LevelScreen(JFrame pvrframe) {
        this.pvrframe = pvrframe;

        ImageIcon bgi = new ImageIcon("levelScreenImg.png");

        JFrame levelscreen = new JFrame("Levels");
        levelscreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        levelscreen.setUndecorated(true);
        device.setFullScreenWindow(levelscreen);
        levelscreen.setLayout(new BorderLayout());

        ImageIcon logoIcon = new ImageIcon("logo2.png");
        levelscreen.setIconImage(logoIcon.getImage());

        Border border = BorderFactory.createLineBorder(Color.BLACK);

        JLabel label = new JLabel();
        label.setBorder(border);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setLayout(new GridBagLayout());

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

        BackHomeButton backButton = new BackHomeButton("Back");
        backButton.setFont(new Font("Comic Sans", Font.BOLD, 20));
        backButton.setForeground(Color.green);
        backButton.setBackground(Color.black);
        backButton.setPreferredSize(new Dimension(150, 50));
        backButton.setFocusable(false);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Back to main screen");
                pvrframe.setVisible(true);
                levelscreen.setVisible(false);
            }
        });

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 10, 10);

        label.add(level1Button, c);
        label.add(level2Button, c);
        label.add(level3Button, c);
        label.add(backButton, c);

        levelscreen.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = levelscreen.getWidth();
                int height = levelscreen.getHeight();
                label.setBounds(0, 0, width, height);

                Image img = bgi.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(img));
                }
            }
        );

        levelscreen.add(label, BorderLayout.CENTER);
        levelscreen.setVisible(true);
    }

    private void customizeLevelButton(JButton button) {
        button.setFont(new Font("Comic Sans", Font.BOLD, 15));
        button.setForeground(Color.white);
        button.setBackground(Color.blue);
        button.setFocusable(false);
        button.setPreferredSize(new Dimension(150, 50));
    }
}
