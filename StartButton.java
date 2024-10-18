import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;

class StartButton extends JButton {
    public StartButton(String label) {
        super(label);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.black);
        g2.fillRoundRect(5, 5, getWidth() - 10, getHeight() - 10, 20, 20);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 10, getHeight() - 10, 20, 20);

        FontMetrics fontMetrics = g2.getFontMetrics();
        g2.setColor(getForeground());
        int x = (getWidth() - fontMetrics.stringWidth(getText())) / 2;
        int y = (getHeight() + fontMetrics.getAscent()) / 2 - fontMetrics.getDescent();
        g2.drawString(getText(), x, y);

        g2.dispose();
        super.paintComponent(g);
    }
}


