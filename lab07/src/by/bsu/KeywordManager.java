package by.bsu;

import by.bsu.interfaces.Observable;
import by.bsu.interfaces.Observer;
import javafx.scene.input.KeyCode;

import java.util.HashSet;
import java.util.Set;

public class KeywordManager implements Observable {
    private final Set<Observer> observerList = new HashSet<>();

    private KeyCode lastKeyCode;

    @Override
    public void addObserver(Observer o) {
        observerList.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observerList.remove(o);
    }

    @Override
    public void updateAllObservers() {
        observerList.forEach(observer -> observer.update(this));
    }

    public KeyCode getLastKeyCode() {
        return lastKeyCode;
    }

    public void setLastKeyCode(KeyCode lastKeyCode) {
        this.lastKeyCode = lastKeyCode;
        updateAllObservers();
    }
}
