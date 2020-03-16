package graphics.frames;

import graphics.panels.ClockPanel;
import graphics.panels.DiagramPanel;
import graphics.panels.RotatingImagePanel;

import javax.swing.*;

@SuppressWarnings("FieldCanBeLocal")
public class MainFrame extends JFrame {
    private JPanel clockPanel;
    private JPanel rotatingImagePanel;
    private JPanel diagramPanel;

    public MainFrame() {
        super("lab4");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        clockPanel = new ClockPanel();
        rotatingImagePanel = new RotatingImagePanel(this, new ImageIcon("flag_afghanistan.png"));
        diagramPanel = new DiagramPanel(this);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Циферблат", clockPanel);
        tabbedPane.addTab("Картинка", rotatingImagePanel);
        tabbedPane.addTab("Диаграмма", diagramPanel);

        add(tabbedPane);
        pack();
        setLocationRelativeTo(null);
    }
}
