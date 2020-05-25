package by.bsu.interfaces;

import java.util.EventObject;

public interface Observable {
    void addObserver(Observer o);
    void removeObserver(Observer o);
    void updateAllObservers(EventObject eventObject);
}
