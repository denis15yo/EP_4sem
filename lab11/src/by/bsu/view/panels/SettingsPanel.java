package by.bsu.view.panels;

import by.bsu.events.SettingsConfiguredEvent;
import by.bsu.interfaces.Observable;
import by.bsu.interfaces.Observer;
import by.bsu.models.car.TypeCar;
import by.bsu.models.road.TypeRoad;
import by.bsu.view.panels.chooseTypeCar.ChooseTypeCarPanel;
import by.bsu.view.panels.chooseTypeRoad.ChooseTypeRoadPanel;

import javax.swing.*;
import java.awt.*;
import java.util.EventObject;
import java.util.HashSet;
import java.util.Set;

public class SettingsPanel extends JPanel implements Observable {
    private Set<Observer> observerSet;

    private final ChooseTypeRoadPanel chooseMapPanel;
    private final ChooseTypeCarPanel chooseCarPanel;
//    private final GamePanel gamePanel;

    private JButton button;
    private int activeTab;

    private TypeRoad typeRoad;
    private TypeCar typeCar;

    public SettingsPanel() {
        super(new BorderLayout());

        observerSet = new HashSet<>();

        chooseMapPanel = new ChooseTypeRoadPanel();
        chooseCarPanel = new ChooseTypeCarPanel();

        button = new JButton("Apply");
        activeTab = 0;

        typeRoad = null;
        typeCar = null;

        CardLayout cardLayout = new CardLayout();

        cardLayout.addLayoutComponent(chooseMapPanel, "Map");
        cardLayout.addLayoutComponent(chooseCarPanel, "Car");


        JPanel cards = new JPanel(cardLayout);
        cards.add(chooseMapPanel);
        cards.add(chooseCarPanel);
        cardLayout.first(cards);
        add(cards, BorderLayout.CENTER);
        add(button, BorderLayout.SOUTH);

        button.addActionListener(e -> {
            try{
                if(activeTab == 0){
                    typeRoad = chooseMapPanel.getChosenTypeRoad();
                    cardLayout.next(cards);
                    ++activeTab;
                } else if(activeTab == 1){
                    typeCar = chooseCarPanel.getChosenTypeCar();
                    updateAllObservers(new SettingsConfiguredEvent(this));
                }
            } catch (IndexOutOfBoundsException ex){
                JOptionPane.showMessageDialog(null, "Ошибка", "Выбор не сделан", JOptionPane.ERROR_MESSAGE);
            }

        });
    }

    public TypeRoad getTypeRoad() {
        return typeRoad;
    }

    public TypeCar getTypeCar() {
        return typeCar;
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
}
