package com.anton.thread_app.entity.state.impl;

import com.anton.thread_app.entity.Pier;
import com.anton.thread_app.entity.Port;
import com.anton.thread_app.entity.Ship;
import com.anton.thread_app.entity.state.ShipState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class ArrivingShipState implements ShipState {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final ShipState nextState = new UnloadingShipState();
    private static final ShipState emergencyShipState = new LeavingShipState();

    @Override
    public void arrive(Ship ship) {
        Port port = Port.getInstance();
        Optional<Pier> pier = port.visitPier();
        if (pier.isPresent()) {
            ship.setCurrentPier(pier.get());
            LOGGER.info(String.format("Ship %d arrived at port %d", ship.getId(), pier.get().getId()));
            ship.setShipState(nextState);
        } else {
            LOGGER.debug("Emergency leaving the port");
            ship.setShipState(emergencyShipState);
        }
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

    @Override
    public String toString() {
        return "ArrivingShipState";
    }
}
