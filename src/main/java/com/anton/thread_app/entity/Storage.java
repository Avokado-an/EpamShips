package com.anton.thread_app.entity;

import java.util.concurrent.atomic.AtomicInteger;

public class Storage {
    private static final int STORAGE_CAPACITY = 150;
    private static final int STARTING_LOAD = STORAGE_CAPACITY / 10;
    private static final Storage instance = new Storage();
    private AtomicInteger currentLoad;

    private Storage() {
        currentLoad = new AtomicInteger(STARTING_LOAD);
    }

    public static Storage getInstance() {
        return instance;
    }

    boolean loadContainers(Ship ship) {
        boolean flag = false;
        if (currentLoad.intValue() + ship.getCurrentLoad() <= STORAGE_CAPACITY) {
            currentLoad.set(currentLoad.intValue() + ship.getCurrentLoad());
            flag = true;
        }
        return flag;
    }

    boolean unloadContainers(Ship ship) {
        boolean flag = false;
        int amountOfContainersToUnload = ship.getTotalCapacity() - ship.getCurrentLoad();
        if (currentLoad.intValue() - amountOfContainersToUnload >= 0) {
            currentLoad.set(currentLoad.intValue() - amountOfContainersToUnload);
            flag = true;
        }
        return flag;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Storage with max load of ").
                append(STORAGE_CAPACITY).append(" and current load of ").append(currentLoad);
        return res.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Storage storage = (Storage) o;
        return currentLoad.get() == storage.currentLoad.get();
    }

    @Override
    public int hashCode() {
        int hash = 31;
        hash += currentLoad.hashCode();
        return hash;
    }
}
