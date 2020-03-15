package graphics.panels;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("FieldCanBeLocal")
public class DrawPanel extends JPanel {
    private static final int DELAY = 100;
    private static final int ALL_TIME = 60_000;

    private Timer timer;

    private int centerX, centerY;
    private double angle;
    private int r;
    private int w = 1;
    private int direction = 1;

    public void setW(int w) {
        this.w = w;
    }

    private ImageIcon image;

    public void setImage(ImageIcon image) {
        image.setImage(image.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        this.image = image;
    }

    public DrawPanel(ImageIcon image) {
        super(new BorderLayout());
        this.image = image;

        setPreferredSize(new Dimension(500, 500));
        angle = 0;
        timer = new Timer(DELAY, e -> {
            angle += ((double)DELAY / ALL_TIME) * 2 * Math.PI * w * direction;
            if(angle > 2 * Math.PI){
                angle -= 2 * Math.PI;
            }
            repaint();
        });
        timer.start();
    }

    public void changeDirection(){
        direction *= -1;
    }

    @Override
    public void paint(Graphics g) {
        r = Math.min(getWidth(), getHeight()) / 4;
        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
        int x = (int) (centerX + r * Math.sin(angle));
        int y = (int) (centerY - r * Math.cos(angle));
        g.drawOval(centerX - r, centerY - r, 2 * r, 2 * r);
        g.drawImage(image.getImage(), x - image.getIconWidth() / 2, y - image.getIconHeight() / 2, null);
    }
}
