package com.example.dungeonrunner.viewModels;

public interface PlayerObservable {
    void registerObserver(EnemyObserver o);
    void notifyObserver();
}
