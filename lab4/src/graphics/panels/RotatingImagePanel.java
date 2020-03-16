package graphics.panels;

import myUtil.FilesWork;

import javax.swing.*;
import java.awt.*;
import java.io.File;

@SuppressWarnings({"FieldCanBeLocal"})
public class RotatingImagePanel extends JPanel {
    private JFrame owner;

    private DrawPanel drawPanel;

    private JButton imageButton;
    private JSlider speedSlider;
    private JButton directionButton;

    public RotatingImagePanel(JFrame owner, ImageIcon image) {
        super(new BorderLayout());

        this.owner = owner;

        drawPanel = new DrawPanel(image);

        imageButton = new JButton("Картинка");
        speedSlider = new JSlider(0, 360, 0);
        speedSlider.setMajorTickSpacing(45);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.setToolTipText("Градусов в секунду");
        directionButton = new JButton("Направление");


        speedSlider.addChangeListener(e -> drawPanel.setW(speedSlider.getValue()));
        directionButton.addActionListener(e -> drawPanel.changeDirection());
        imageButton.addActionListener(e -> {
            File file = FilesWork.showOpenFileDialog(this.owner, (dir, name) -> name.matches(".+\\.(png|jpg|jpeg)"));
            if(file != null){
                drawPanel.setImage(new ImageIcon(file.getAbsolutePath()));
            }
        });

        Box box = Box.createHorizontalBox();
        box.add(imageButton);
        box.add(speedSlider);
        box.add(directionButton);

        add(drawPanel, BorderLayout.CENTER);
        add(box, BorderLayout.SOUTH);
    }
}
