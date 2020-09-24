package com.anton.thread_app.entity;

import com.anton.thread_app.entity.state.ShipState;
import com.anton.thread_app.entity.state.impl.ArrivingShipState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;

public class Ship implements Callable<Ship> {
    private static final int TIME_TO_LEAVE_PORT = 1000;
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int DEFAULT_CAPACITY = 10;
    private int id;
    private Semaphore semaphore;
    private int totalCapacity;
    private int currentLoad;
    private Pier currentPier;
    private ShipState shipState;

    public Ship(int id, int totalCapacity, Semaphore semaphore, Pier currentPier) {
        this.id = id;
        this.totalCapacity = totalCapacity;
        this.semaphore = semaphore;
        currentLoad = new Random().nextInt(totalCapacity);
        this.currentPier = currentPier;
        shipState = new ArrivingShipState();
    }

    public Ship(int id, Pier currentPier, Semaphore semaphore) {
        this.id = id;
        this.currentLoad = DEFAULT_CAPACITY;
        Random random = new Random();
        this.semaphore = semaphore;
        currentLoad = random.nextInt(totalCapacity);
        this.currentPier = currentPier;
        shipState = new ArrivingShipState();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pier getCurrentPier() {
        return currentPier;
    }

    public void setCurrentPier(Pier currentPier) {
        this.currentPier = currentPier;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public int getCurrentLoad() {
        return currentLoad;
    }

    public void setCurrentLoad(int currentLoad) {
        this.currentLoad = currentLoad;
    }

    public ShipState getShipState() {
        return shipState;
    }

    public void setShipState(ShipState shipState) {
        this.shipState = shipState;
    }

    public static int getDefaultCapacity() {
        return DEFAULT_CAPACITY;
    }

    @Override
    public Ship call() {
        try {
            LOGGER.info("ship gets to the port");
            semaphore.acquire();
            shipState.arrive(this);
            shipState.unload(this);
            shipState.load(this);
            shipState.leave(this);
        } catch (InterruptedException e) {
            LOGGER.warn("Ship thread was interrupted");
        } finally {
            LOGGER.info("ship leaves port");
            semaphore.release();
        }
        return this;// TODO: 24.09.2020 what to do with it??? 
    }
}
