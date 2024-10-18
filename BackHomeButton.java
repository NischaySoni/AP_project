import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;

public class BackHomeButton extends JButton{
    public BackHomeButton(String label){
        super(label);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(false);
    }


}
