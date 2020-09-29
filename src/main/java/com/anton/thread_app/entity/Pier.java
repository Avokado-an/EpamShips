package com.anton.thread_app.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Pier {
    private static final Logger LOGGER = LogManager.getLogger();
    private int id;

    public Pier(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void unloadShipToStorage(Ship ship) {
        int emptyShipContainersAmount = 0;
        Storage storage = Storage.getInstance();
        if (!storage.loadContainers(ship)) {
            LOGGER.info("Not enough space in the storage");
        } else {
            ship.setCurrentLoad(emptyShipContainersAmount);
            LOGGER.info(String.format("Ship %d was unloaded", ship.getId()));
        }
    }

    public void loadShipFromStorage(Ship ship) {
        Storage storage = Storage.getInstance();
        if (!storage.unloadContainers(ship)) {
            LOGGER.info("Not enough containers in the storage");
        } else {
            LOGGER.info(String.format("Ship %d was loaded", ship.getId()));
            ship.setCurrentLoad(ship.getTotalCapacity());
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("Pier number ").append(id).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pier pier = (Pier) o;
        return getId() == pier.getId();
    }

    @Override
    public int hashCode() {
        int hash = 31;
        hash += 17 * id;
        return hash;
    }
}
