package by.bsu.view.frames;

import by.bsu.view.panels.GamePanel;

import javax.swing.*;

@SuppressWarnings("FieldCanBeLocal")
public class GameFrame extends JFrame {
    private final GamePanel gamePanel;

    public GameFrame()  {
        super("Game");

        gamePanel = new GamePanel();

        add(gamePanel);

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
