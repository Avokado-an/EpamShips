package com.anton.thread_app.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Storage {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int STORAGE_CAPACITY = 150;
    private static final Storage instance = new Storage(STORAGE_CAPACITY);
    private int currentLoad;

    private Storage(int currentLoad) {
        this.currentLoad = currentLoad;
    }

    public static Storage getInstance() {
        return instance;
    }

    boolean loadContainers(int amountOfContainers) {
        boolean flag = false;
        if(currentLoad + amountOfContainers <= STORAGE_CAPACITY) {
            currentLoad += amountOfContainers;
            flag = true;
        }
        return flag;
    }

    boolean unloadContainers(int amountOfContainers) {
        boolean flag = false;
        if(currentLoad - amountOfContainers >= 0) {
            currentLoad -= amountOfContainers;
            flag = true;
        }
        return flag;
    }
}
