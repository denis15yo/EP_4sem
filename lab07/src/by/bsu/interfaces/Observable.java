package by.bsu.interfaces;

public interface Observable {
    void addObserver(Observer o);
    void removeObserver(Observer o);
    void updateAllObservers();
}
