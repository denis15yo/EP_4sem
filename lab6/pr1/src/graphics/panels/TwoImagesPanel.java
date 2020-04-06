package graphics.panels;

import myUtil.FilesWork;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

@SuppressWarnings("FieldCanBeLocal")
public class TwoImagesPanel extends JPanel {
    private MixedImagePanel mixedImagePanel;
    private OriginalImagePanel originalImagePanel;

    private JButton mixButton;
    private JButton openButton;

    private static final int x0 = 0, y0 = 0;

    public TwoImagesPanel() {
        super(new BorderLayout());

        mixedImagePanel = new MixedImagePanel(x0, y0);
        originalImagePanel = new OriginalImagePanel(x0, y0);
        mixButton = new JButton("Перемешать");
        openButton = new JButton("Открыть");

        mixButton.addActionListener(e -> mixedImagePanel.mix());
        openButton.addActionListener(e -> {
            try {
                setImage(ImageIO.read(Objects.requireNonNull(FilesWork.showOpenFileDialog(null,
                        ((dir, name) -> name.matches(".+\\.(png|jpg)"))))));
            } catch (IOException | NullPointerException ignored) {}
        });

        JPanel temp = new JPanel(new GridLayout(1, 2, 10, 0));
        temp.add(mixedImagePanel);
        temp.add(originalImagePanel);
        add(temp, BorderLayout.CENTER);
        temp = new JPanel(new FlowLayout());
        temp.add(mixButton);
        temp.add(openButton);
        add(temp, BorderLayout.SOUTH);
    }

    public void setImage(BufferedImage image) {
        mixedImagePanel.setImage(image);
        originalImagePanel.setImage(image);
    }
}
