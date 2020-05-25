package by.bsu.view.frames;

import by.bsu.interfaces.Observable;
import by.bsu.interfaces.Observer;
import by.bsu.models.Player;
import by.bsu.models.car.TypeCar;
import by.bsu.models.road.TypeRoad;
import by.bsu.view.panels.SettingsPanel;

import javax.swing.*;
import java.awt.*;
import java.util.EventObject;
import java.util.HashSet;
import java.util.Set;

public class SettingsFrame extends JFrame implements Observer, Observable {
    private Set<Observer> observerSet;

    private Player player;
    private SettingsPanel settingsPanel;

    public SettingsFrame() {
        super("Настройки");

        observerSet = new HashSet<>();

        settingsPanel = new SettingsPanel();

        player = new Player(JOptionPane.showInputDialog(null, "Как вас зовут?"));

        settingsPanel.addObserver(this);
        add(settingsPanel);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public Player getPlayer() {
        return player;
    }
    public TypeRoad getTypeRoad(){
        return settingsPanel.getTypeRoad();
    }
    public TypeCar getTypeCar(){
        return settingsPanel.getTypeCar();
    }

    @Override
    public void addObserver(Observer o) {
        observerSet.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observerSet.remove(o);
    }

    @Override
    public void updateAllObservers(EventObject eventObject) {
        observerSet.forEach(o -> o.update(eventObject));
    }

    @Override
    public void update(EventObject eventObject) {
        updateAllObservers(eventObject);
    }
}
