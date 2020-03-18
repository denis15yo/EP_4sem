package graphics.panels;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("FieldCanBeLocal")
public class DrawPanel extends JPanel {
    private static final int DELAY = 100;
    private static final int SECOND = 1000;

    private Timer timer;

    private double angle = 0;
    private int w = 0;
    private int direction = 1;

    private ImageIcon image;

    public DrawPanel() {
        setPreferredSize(new Dimension(500, 500));

        image = new ImageIcon();

        timer = new Timer(DELAY, e -> {
            angle += (double)w * DELAY / SECOND * Math.PI / 180 * direction;
            if(angle > 2 * Math.PI){
                angle -= 2 * Math.PI;
            }
            else if(angle < -2 * Math.PI){
                angle += 2 * Math.PI;
            }
            repaint();
        });
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        int r = Math.min(getWidth(), getHeight()) / 4;
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int x = (int) (centerX + r * Math.sin(angle));
        int y = (int) (centerY - r * Math.cos(angle));
        g.drawOval(centerX - r, centerY - r, 2 * r, 2 * r);
        g.drawImage(image.getImage(), x - image.getIconWidth() / 2, y - image.getIconHeight() / 2, null);
    }

    public void setW(int w) {
        this.w = w;
    }
    public void changeDirection(){
        direction *= -1;
    }
    public void setImage(ImageIcon image) {
        image.setImage(image.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        this.image = image;
    }
}
