package com.anton.thread_app.entity;

public class Port {
    private int totalCapacity;
    private int currentLoading;

    public Port(int totalCapacity, int currentLoading) {
        this.totalCapacity = totalCapacity;
        this.currentLoading = currentLoading;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public int getCurrentLoading() {
        return currentLoading;
    }

    public void setCurrentLoading(int currentLoading) {
        this.currentLoading = currentLoading;
    }

    public int getCurrentFreeSpace() {
        return totalCapacity - currentLoading;
    }
}
