package com.example.dungeonrunner.viewModels;

public interface Observable {
    void registerObserver(Observer o);
    void unregisterObserver();
    void notifyObserver();
}

