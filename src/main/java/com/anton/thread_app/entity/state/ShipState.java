package com.anton.thread_app.entity.state;

import com.anton.thread_app.entity.Ship;

public interface ShipState {
    void arrive(Ship ship);

    void load(Ship ship);

    void unload(Ship ship);

    void leave(Ship ship);
}
