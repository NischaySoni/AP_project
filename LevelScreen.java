import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class LevelScreen {
    private JFrame pvrframe;

    public LevelScreen(JFrame pvrframe){
        this.pvrframe = pvrframe;
        ImageIcon imageIcon1 = new ImageIcon("space.png");
        Border border = BorderFactory.createLineBorder(Color.black, 3);

        JLabel label = new JLabel();
        label.setBorder(border);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);

        JFrame levelscreen = new JFrame("Levels");
        levelscreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        levelscreen.setUndecorated(true); // Remove the title bar
        device.setFullScreenWindow(levelscreen);
        levelscreen.setLayout(null);


        BackHomeButton button = new BackHomeButton("Back");
        button.setFont(new Font("Comic Sans", Font.BOLD, 25));
        button.setForeground(Color.black);
        button.setBackground(Color.green);
        button.setBounds(100, 100, 200, 100);
//        button.setPreferredSize(new Dimension(200, 100));
        button.setFocusable(false);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("back");
                pvrframe.setVisible(true);
                levelscreen.setVisible(false);
            }
        });


        levelscreen.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = levelscreen.getWidth();
                int height = levelscreen.getHeight();
                label.setBounds(0, 0, width, height);

                Image img = imageIcon1.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(img));
            }
        }
        );

        levelscreen.add(button);
        levelscreen.add(label);
        levelscreen.setVisible(true);
    }
}
