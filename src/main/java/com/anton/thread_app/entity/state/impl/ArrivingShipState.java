package com.anton.thread_app.entity.state.impl;

import com.anton.thread_app.entity.Pier;
import com.anton.thread_app.entity.Port;
import com.anton.thread_app.entity.Ship;
import com.anton.thread_app.entity.state.ShipState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrivingShipState implements ShipState {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final ShipState nextState = new LoadingShipState();

    @Override
    public void arrive(Ship ship) {
        Port port = Port.getInstance();
        Pier pier = port.visitPier();
        ship.setCurrentPier(pier);
        LOGGER.info(String.format("Ship %d arrived at port %d", ship.getId(), pier.getId()));
        ship.setShipState(nextState);
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
        LOGGER.warn("Unsupported operation");
    }
}
