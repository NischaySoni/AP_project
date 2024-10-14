import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Screen {

    public Screen() {

        ImageIcon imageIcon1 = new ImageIcon("openingscreen.png"); 
        ImageIcon imageIcon2 = new ImageIcon("logo.png");
        Border border = BorderFactory.createLineBorder(Color.green, 3);
        
        JLabel label = new JLabel(); 
        label.setBorder(border); 
        label.setVerticalAlignment(JLabel.CENTER); 
        label.setHorizontalAlignment(JLabel.CENTER); 

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setLayout(new BorderLayout()); 

        frame.setTitle("ANGRY BIRDS");
        frame.setIconImage(imageIcon2.getImage());

        frame.add(label, BorderLayout.CENTER);

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = frame.getWidth();
                int height = frame.getHeight();

                Image img = imageIcon1.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(img)); 
                }
            }
        );
        frame.setVisible(true);
    }
}
