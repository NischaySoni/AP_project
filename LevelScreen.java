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

        BackHomeButton button = new BackHomeButton("Back");
        button.setFont(new Font("Comic Sans", Font.BOLD, 25));
        button.setForeground(Color.green);
        button.setBackground(Color.black);
        button.setPreferredSize(new Dimension(200, 100));
        button.setFocusable(false);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Back to main screen");
                pvrframe.setVisible(true);
                levelscreen.setVisible(false);
            }
        });

        levelscreen.add(button, BorderLayout.SOUTH);
        levelscreen.setVisible(true);
    }
}
