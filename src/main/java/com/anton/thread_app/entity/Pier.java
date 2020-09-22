package com.anton.thread_app.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pier {
    private static final int DEFAULT_CAPACITY = 12;
    List<Port> ports = new ArrayList<>(DEFAULT_CAPACITY);

    public Pier(List<Port> ports) {
        this.ports = ports;
    }

    public Pier() {
    }

    public List<Port> getPorts() {
        return Collections.unmodifiableList(ports);
    }

    public void setPorts(List<Port> ports) {
        this.ports = ports;
    }
}
