package com.anton.thread_app.entity;

public class Pier {
    private int id;

    public Pier(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void load(Ship ship) {
        Storage storage = Storage.getInstance();
        storage.loadContainers(ship.getCurrentLoad());
    }

    public void unload(Ship ship) {
        Storage storage = Storage.getInstance();
        storage.unloadContainers(ship.getCurrentLoad());
    }

    public void setId(int id) {
        this.id = id;
    }
}
