package graphics.panels;

import myUtil.FilesWork;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FilenameFilter;

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
        speedSlider = new JSlider(1, 10, 1);
        speedSlider.setPaintLabels(true);
        speedSlider.setMajorTickSpacing(1);
        speedSlider.setToolTipText("Скорость");
        speedSlider.setPaintTicks(true);
        imageButton = new JButton("Картинка");
        directionButton = new JButton("Направление");


        speedSlider.addChangeListener(e -> drawPanel.setW(speedSlider.getValue()));
        directionButton.addActionListener(e -> drawPanel.changeDirection());
        imageButton.addActionListener(e -> {
            File file = FilesWork.openFile(this.owner, (dir, name) -> name.matches(".+\\.(png|jpg|jpeg)"));
            if(file != null){
                drawPanel.setImage(new ImageIcon(file.getAbsolutePath()));
            }
        });

        Box box = Box.createHorizontalBox();
        box.add(imageButton);
        box.add(speedSlider);
        box.add(directionButton);

        JPanel temp = new JPanel(new FlowLayout());
        temp.add(box);

        add(drawPanel, BorderLayout.CENTER);
        add(temp, BorderLayout.SOUTH);
    }
}
