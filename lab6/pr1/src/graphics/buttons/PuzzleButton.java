package graphics.buttons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class PuzzleButton extends JButton {
    private int shouldX, shouldY;

    public PuzzleButton(int shouldX, int shouldY, ImageIcon imageIcon) {
        setContentAreaFilled(false);
        setBorderPainted(false);
        this.shouldX = shouldX;
        this.shouldY = shouldY;
        setIcon(imageIcon);
        setSize(new Dimension(imageIcon.getIconWidth(), imageIcon.getIconHeight()));
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                setLocation(e.getXOnScreen() - getWidth() / 2, e.getYOnScreen() - getHeight() / 2);
            }
        });
    }

    public int getShouldX() {
        return shouldX;
    }

    public int getShouldY() {
        return shouldY;
    }
}
