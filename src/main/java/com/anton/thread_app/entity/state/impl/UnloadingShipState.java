package com.anton.thread_app.entity.state.impl;

import com.anton.thread_app.entity.Pier;
import com.anton.thread_app.entity.Ship;
import com.anton.thread_app.entity.state.ShipState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UnloadingShipState implements ShipState {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final ShipState nextState = new LeavingShipState();

    @Override
    public void arrive(Ship ship) {
        LOGGER.warn("Unsupported operation");
    }

    @Override
    public void load(Ship ship) {
        LOGGER.warn("Unsupported operation");
    }

    @Override
    public void unload(Ship ship) {
        Pier pier = ship.getCurrentPier();
        pier.unload(ship);
        ship.setShipState(nextState);
        LOGGER.info(String.format("Ship %d was unloaded", ship.getId()));
    }

    @Override
    public void leave(Ship ship) {
        LOGGER.warn("Unsupported operation");
    }
}
