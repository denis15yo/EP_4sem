package by.bsu.view.frames;

import by.bsu.events.GameOverEvent;
import by.bsu.events.SettingsConfiguredEvent;
import by.bsu.interfaces.Observer;
import by.bsu.view.panels.GamePanel;

import javax.swing.*;
import java.util.EventObject;

@SuppressWarnings("FieldCanBeLocal")
public class GameFrame extends JFrame implements Observer {
    private SettingsFrame settingsFrame;
    private GamePanel gamePanel;


    public GameFrame() {
        super("Game");

        settingsFrame = new SettingsFrame();
        settingsFrame.setVisible(true);
        settingsFrame.addObserver(this);
    }

    @Override
    public void update(EventObject eventObject) {
        if(eventObject instanceof SettingsConfiguredEvent){
            settingsFrame.setVisible(false);
            gamePanel = new GamePanel(settingsFrame.getPlayer(), settingsFrame.getTypeRoad(), settingsFrame.getTypeCar(), 2);
            gamePanel.addObserver(this);
            add(gamePanel);
            pack();
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setResizable(false);
            setVisible(true);
        } else if(eventObject instanceof GameOverEvent){
            setVisible(false);
        }

    }
}
