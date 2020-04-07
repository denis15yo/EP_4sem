package graphics.frames;

import graphics.dialogs.PosDialog;
import graphics.panels.Text3DPanel;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("FieldCanBeLocal")
public class MainFrame extends JFrame {
    private Text3DPanel text3DPanel;

    private JMenuBar menuBar;
    private JMenu lightMenu;
    private JMenu firstLightMenu, secondLightMenu;
    private JMenuItem firstLightColorMenuItem, firstLightPosMenuItem;
    private JMenuItem secondLightColorMenuItem, secondLightPosMenuItem;

    public MainFrame() {
        super("Java3DText");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        text3DPanel = new Text3DPanel();

        menuBar = new JMenuBar();
        lightMenu = new JMenu("Свет");
        firstLightMenu = new JMenu("Первый");
        secondLightMenu = new JMenu("Второй");
        firstLightPosMenuItem = new JMenuItem("Позиция");
        firstLightColorMenuItem = new JMenuItem("Цвет");
        secondLightPosMenuItem = new JMenuItem("Позиция");
        secondLightColorMenuItem = new JMenuItem("Цвет");
        menuBar.add(lightMenu);
        lightMenu.add(firstLightMenu);
        firstLightMenu.add(firstLightPosMenuItem);
        firstLightMenu.add(firstLightColorMenuItem);
        lightMenu.add(secondLightMenu);
        secondLightMenu.add(secondLightPosMenuItem);
        secondLightMenu.add(secondLightColorMenuItem);

        firstLightPosMenuItem.addActionListener(e -> {
            PosDialog posDialog = new PosDialog();
            posDialog.setVisible(true);
            if(posDialog.isDone()){
                text3DPanel.setFirstLightPos((float)posDialog.getPosX(), (float)posDialog.getPosY(), (float)posDialog.getPosZ());
            }
        });
        secondLightPosMenuItem.addActionListener(e -> {
            PosDialog posDialog = new PosDialog();
            posDialog.setVisible(true);
            if(posDialog.isDone()){
                text3DPanel.setSecondLightPos((float)posDialog.getPosX(), (float)posDialog.getPosY(), (float)posDialog.getPosZ());
            }
        });

        firstLightColorMenuItem.addActionListener(e -> {
            Color color = JColorChooser.showDialog(null, "Цвет первого", Color.BLACK);
            if (color != null) {
                text3DPanel.setFirstLightColor(color);
            }
        });
        secondLightColorMenuItem.addActionListener(e -> {
            Color color = JColorChooser.showDialog(null, "Цвет второго", Color.BLACK);
            if (color != null) {
                text3DPanel.setSecondLightColor(color);
            }
        });

        add(text3DPanel);
        setJMenuBar(menuBar);

        pack();
    }
}
