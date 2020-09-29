package com.anton.thread_app.entity;

import com.anton.thread_app.entity.state.ShipState;
import com.anton.thread_app.entity.state.impl.ArrivingShipState;

import java.util.concurrent.Callable;

public class Ship implements Callable<String> {
    private int id;
    private int totalCapacity;
    private int currentLoad;
    private Pier currentPier;
    private ShipState shipState;

    public Ship(int id, int totalCapacity, int currentLoad) {
        this.id = id;
        this.totalCapacity = totalCapacity;
        this.currentLoad = currentLoad;
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

    @Override
    public String call() {
        shipState.arrive(this);
        shipState.unload(this);
        shipState.load(this);
        shipState.leave(this);
        return this.toString();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Ship ").append(id).append(" left pier ").
                append(currentPier.getId()).append(" with load of ").append(currentLoad);
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ship ship = (Ship) o;
        return getId() == ship.getId() &&
                getTotalCapacity() == ship.getTotalCapacity() &&
                getCurrentLoad() == ship.getCurrentLoad() &&
                getCurrentPier().equals(ship.getCurrentPier()) &&
                getShipState().equals(ship.getShipState());
    }

    @Override
    public int hashCode() {
        int hash = 31;
        hash += id * 17;
        hash += totalCapacity * 17;
        hash += currentLoad * 17;
        hash += currentPier.hashCode();
        hash += shipState.hashCode();
        return hash;
    }
}
