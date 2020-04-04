package graphics.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class OriginalImagePanel extends JPanel {
    private BufferedImage image;
    int x0, y0;

    public OriginalImagePanel(int x0, int y0) {
        this.x0 = x0;
        this.y0 = y0;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(image != null){
            g.drawImage(image, x0, y0, null);
        }
    }
}
