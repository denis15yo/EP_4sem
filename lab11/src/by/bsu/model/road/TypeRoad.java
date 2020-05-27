package by.bsu.model.road;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public enum TypeRoad {
    FIRST("img/bg.png", new Rectangle(170, 0, 660, 800));

    private Image bg;
    private final Rectangle bounds;

    public Image getBg() {
        return bg;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    TypeRoad(String src, Rectangle bounds)  {
        try {
            bg = ImageIO.read(new File(src));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.bounds = bounds;
    }
}
