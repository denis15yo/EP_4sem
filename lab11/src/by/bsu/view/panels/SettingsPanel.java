package by.bsu.view.panels;

import by.bsu.events.SettingsConfiguredEvent;
import by.bsu.interfaces.Observable;
import by.bsu.interfaces.Observer;
import by.bsu.model.car.TypeCar;
import by.bsu.model.road.TypeRoad;
import by.bsu.view.panels.chooseTypeCar.ChooseTypeCarPanel;
import by.bsu.view.panels.chooseTypeRoad.ChooseTypeRoadPanel;

import javax.swing.*;
import java.awt.*;
import java.util.EventObject;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("FieldCanBeLocal")
public class SettingsPanel extends JPanel implements Observable {
    private final Set<Observer> observerSet;

    private final ChooseTypeRoadPanel chooseRoadPanel;
    private final ChooseTypeCarPanel chooseCarPanel;
    private final ChooseLevelHardPanel chooseLevelHardPanel;

    private final JButton button;
    private int activeTab;

    private TypeRoad typeRoad;
    private TypeCar typeCar;
    private int level;

    public SettingsPanel(int levelsCount) {
        super(new BorderLayout());

        observerSet = new HashSet<>();

        chooseRoadPanel = new ChooseTypeRoadPanel();
        chooseCarPanel = new ChooseTypeCarPanel();
        chooseLevelHardPanel = new ChooseLevelHardPanel(levelsCount);

        button = new JButton("Apply");
        activeTab = 0;

        typeRoad = null;
        typeCar = null;
        level = 1;

        CardLayout cardLayout = new CardLayout();
        cardLayout.addLayoutComponent(chooseRoadPanel, "Map");
        cardLayout.addLayoutComponent(chooseCarPanel, "Car");
        cardLayout.addLayoutComponent(chooseLevelHardPanel, "Level");

        JPanel cards = new JPanel(cardLayout);
        cards.add(chooseRoadPanel);
        cards.add(chooseCarPanel);
        cards.add(chooseLevelHardPanel);
        cardLayout.first(cards);
        add(cards, BorderLayout.CENTER);
        JPanel tempPanel = new JPanel(new FlowLayout());
        tempPanel.add(button);
        add(tempPanel, BorderLayout.SOUTH);

        button.addActionListener(e -> {
            try{
                if(activeTab == 0){
                    typeRoad = chooseRoadPanel.getChosenTypeRoad();
                    cardLayout.next(cards);
                    ++activeTab;
                } else if(activeTab == 1){
                    typeCar = chooseCarPanel.getChosenTypeCar();
                    cardLayout.next(cards);
                    ++activeTab;
                } else if(activeTab == 2){
                    level = chooseLevelHardPanel.getChosenLevel();
                    updateAllObservers(new SettingsConfiguredEvent(this));
                }
            } catch (IndexOutOfBoundsException ex){
                JOptionPane.showMessageDialog(null, "Выбор не сделан", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }

        });
    }

    public TypeRoad getTypeRoad() {
        return typeRoad;
    }

    public TypeCar getTypeCar() {
        return typeCar;
    }

    public int getLevel(){
        return level;
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
