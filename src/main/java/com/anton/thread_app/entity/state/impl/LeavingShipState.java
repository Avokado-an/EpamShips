package com.anton.thread_app.entity.state.impl;

import com.anton.thread_app.entity.Pier;
import com.anton.thread_app.entity.Port;
import com.anton.thread_app.entity.Ship;
import com.anton.thread_app.entity.state.ShipState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LeavingShipState implements ShipState {
    private static final Logger LOGGER = LogManager.getLogger();

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
        LOGGER.warn("Unsupported operation");
    }

    @Override
    public void leave(Ship ship) {
        Port port = Port.getInstance();
        Pier pier = ship.getCurrentPier();
        port.leavePier(pier);
        LOGGER.info(String.format("Ship %d left the port", ship.getId()));
    }

    @Override
    public String toString() {
        return "LeavingShipState";
    }
}
