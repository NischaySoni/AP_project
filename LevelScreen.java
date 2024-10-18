import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class LevelScreen {
    public LevelScreen(){
//        JLabel label = new JLabel();
//        label.setBorder(border);
//        label.setVerticalAlignment(JLabel.CENTER);
//        label.setHorizontalAlignment(JLabel.CENTER);

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
//                new LevelScreen();
            }
        });


//        JButton button = new JButton("Back!");
//        button.setBounds(50, 50, 120, 40); // Set position (x, y) and size (width, height)
//        button.setFont(new Font("Comic Sans", Font.BOLD, 25));
//        button.setForeground(Color.black);
//        button.setBackground(Color.green);
//        button.setFocusable(false);


        levelscreen.add(button);
        levelscreen.setVisible(true);
    }
}
