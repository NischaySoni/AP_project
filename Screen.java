import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Screen {

    public Screen() {

        ImageIcon imageIcon1 = new ImageIcon("openingscreen.png");
        ImageIcon imageIcon2 = new ImageIcon("logo2.png");
        Border border = BorderFactory.createLineBorder(Color.black, 3);

        JLabel label = new JLabel();
        label.setBorder(border);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(null);

        frame.setTitle("ANGRY BIRDS");
        frame.setIconImage(imageIcon2.getImage());

        label.setLayout(new GridBagLayout());

        StartButton button = new StartButton("Play");
        button.setFont(new Font("Comic Sans", Font.BOLD, 25));
        button.setForeground(Color.black);
        button.setBackground(Color.white);
        button.setPreferredSize(new Dimension(200, 100));
        button.setFocusable(false);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Game Started");
            }
        });

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = GridBagConstraints.RELATIVE;
        c.insets = new Insets(400, 0, 0, 0);
        c.anchor = GridBagConstraints.PAGE_END;
        label.add(button, c);

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = frame.getWidth();
                int height = frame.getHeight();
                label.setBounds(0, 0, width, height);

                Image img = imageIcon1.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(img));
                }
            }
        );

        frame.add(label);
        frame.setVisible(true);
    }
}
