package graphics.frames;

import graphics.panels.ClockPanel;
import graphics.panels.DiagramPanel;
import graphics.panels.RotatingImagePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

@SuppressWarnings("FieldCanBeLocal")
public class MainFrame extends JFrame {
    private JPanel clockPanel;
    private JPanel rotatingImagePanel;
    private JPanel diagramPanel;

    public MainFrame() {
        super("Timer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        clockPanel = new ClockPanel();
        rotatingImagePanel = new RotatingImagePanel(this, new ImageIcon("flag_afghanistan.png"));
        diagramPanel = new DiagramPanel(this);
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Clock", clockPanel);
        tabbedPane.addTab("Image", rotatingImagePanel);
        tabbedPane.addTab("Диаграмма", diagramPanel);

        add(tabbedPane);
        pack();
        setLocationRelativeTo(null);
    }
}
