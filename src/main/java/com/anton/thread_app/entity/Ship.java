package com.anton.thread_app.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ship implements Callable<Boolean> {
    private static final int TIME_TO_LEAVE_PORT = 1000;
    private static final Logger LOGGER = LogManager.getLogger();
    private int totalLoad;
    private boolean isEmpty;
    private Port currentPort;

    public Ship(int totalLoad, boolean isEmpty, Port currentPort) {
        this.totalLoad = totalLoad;
        this.isEmpty = isEmpty;
        this.currentPort = currentPort;
    }

    public Ship(Port currentPort) {
        this.totalLoad = (int) (Math.random() * 10000);
        this.isEmpty = ((int) (Math.random() * 2)) == 1;
        this.currentPort = currentPort;
    }

    public Port getCurrentPort() {
        return currentPort;
    }

    public void setCurrentPort(Port currentPort) {
        this.currentPort = currentPort;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public int getTotalLoad() {
        return totalLoad;
    }

    public void setTotalLoad(int totalLoad) {
        this.totalLoad = totalLoad;
    }

    @Override
    public Boolean call() {
        Lock locker = new ReentrantLock();
        boolean wasOperationSuccessful = true;
        try {
            if (isEmpty && currentPort.getCurrentLoading() >= totalLoad) {
                load(locker);
            } else if (!isEmpty && currentPort.getCurrentFreeSpace() >= totalLoad) {
                unload(locker);
            } else {
                leavePort(locker);
                wasOperationSuccessful = false;
            }
        } catch (InterruptedException e) {
            LOGGER.warn("Ship thread was interrupted");
            wasOperationSuccessful = false;
        }
        return wasOperationSuccessful;
    }

    private void load(Lock locker) throws InterruptedException {
        if (locker.tryLock(totalLoad, TimeUnit.MILLISECONDS)) {
            currentPort.setCurrentLoading(currentPort.getCurrentLoading() - totalLoad);
            locker.unlock();
        }
    }

    private void unload(Lock locker) throws InterruptedException {
        if (locker.tryLock(totalLoad, TimeUnit.MILLISECONDS)) {
            currentPort.setCurrentLoading(currentPort.getCurrentLoading() + totalLoad);
            locker.unlock();
        }
    }

    private void leavePort(Lock locker) throws InterruptedException {
        locker.tryLock(TIME_TO_LEAVE_PORT, TimeUnit.MILLISECONDS);
        locker.unlock();
    }
}
