package by.bsu.view.panels;

import by.bsu.events.GameLoopTimerEvent;
import by.bsu.events.GameOverEvent;
import by.bsu.interfaces.Observable;
import by.bsu.interfaces.Observer;
import by.bsu.model.Game;
import by.bsu.model.Player;
import by.bsu.model.car.TypeCar;
import by.bsu.model.road.TypeRoad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.EventObject;
import java.util.HashSet;
import java.util.Set;

public class GamePanel extends JPanel implements Observer, Observable {
    private final Set<Observer> observerSet;

    private final Game game;

    private static final int HORIZONTAL_MOVE_DELTA = 10;
    private static final int ACCELERATION = 1;

    public GamePanel(Player player, TypeRoad typeRoad, TypeCar typeCar, int level) {
        observerSet = new HashSet<>();

        game = new Game(player, typeRoad, typeCar, level);

        setPreferredSize(new Dimension(game.getWidth(), game.getHeight()));

        setFocusable(true);

        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getExtendedKeyCode() == KeyEvent.VK_LEFT){
                    game.hMovePlayer(-HORIZONTAL_MOVE_DELTA);
                }
                else if(e.getExtendedKeyCode() == KeyEvent.VK_RIGHT){
                    game.hMovePlayer(HORIZONTAL_MOVE_DELTA);
                }
                else if(e.getExtendedKeyCode() == KeyEvent.VK_UP){
                    game.changeSpeed(ACCELERATION);
                }
                else if(e.getExtendedKeyCode() == KeyEvent.VK_DOWN){
                    game.changeSpeed(-ACCELERATION);
                }
            }
        });

        game.addObserver(this);

        game.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.draw(g);
    }

    @Override
    public void update(EventObject eventObject) {
        if(eventObject instanceof GameLoopTimerEvent){
            repaint();
        } else if(eventObject instanceof GameOverEvent){
            JOptionPane.showMessageDialog(null, game.getPlayer().getName() + ", вы проиграли и прошли дистанцию: " + game.getPlayer().getScores());
            updateAllObservers(eventObject);
        }
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
