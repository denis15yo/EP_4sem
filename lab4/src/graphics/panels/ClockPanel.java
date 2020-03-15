package graphics.panels;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings({"FieldCanBeLocal"})
public class ClockPanel extends JPanel {
    private static final int DELAY = 1000;
    private static final int ALL_TIME = 60_000;

    private Timer timer;

    private int centerX, centerY;
    private double angle;
    private int r;

    public ClockPanel() {
        setPreferredSize(new Dimension(500, 500));
        angle = 0;
        timer = new Timer(DELAY, e -> {
            angle += ((double)DELAY / ALL_TIME) * 2 * Math.PI;
            if(angle > 2 * Math.PI){
                angle -= 2 * Math.PI;
            }
            repaint();
        });
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        r = Math.min(getWidth(), getHeight()) / 4;
        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
        int x = (int) (centerX + r * Math.sin(angle));
        int y = (int) (centerY - r * Math.cos(angle));
        g.drawOval(centerX - r, centerY - r, 2 * r, 2 * r);
        g.drawLine(centerX, centerY, x, y);
    }
}
