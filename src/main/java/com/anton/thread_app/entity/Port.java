package com.anton.thread_app.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Port {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int DEFAULT_PIERS_AMOUNT = 2;
    private static final Port instance = new Port();
    private Deque<Pier> freePiers;
    private Queue<Pier> offeredPiers;
    private Lock lock;
    private Condition condition;

    private Port() {
        lock = new ReentrantLock();
        condition = lock.newCondition();
        freePiers = new ArrayDeque<>();
        offeredPiers = new LinkedList<>();
        for (int i = 0; i < DEFAULT_PIERS_AMOUNT; i++) {
            freePiers.add(new Pier(i));
        }
    }

    public static Port getInstance() {
        return instance;
    }

    public Optional<Pier> visitPier() {
        Optional<Pier> pier;
        try {
            lock.lock();
            while (freePiers.isEmpty()) {
                LOGGER.info("All piers are taken");
                condition.await();
            }
            pier = Optional.of(freePiers.poll());
            offeredPiers.offer(pier.get());
        } catch (InterruptedException e) {
            pier = Optional.empty();
            LOGGER.warn(e);
        } finally {
            lock.unlock();
        }
        return pier;
    }

    public void leavePier(Pier pier) {
        try {
            lock.lock();
            freePiers.offer(pier);
            offeredPiers.remove(pier);
        } finally {
            condition.signal();
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Port with ").append(DEFAULT_PIERS_AMOUNT).append(" amount of piers and ").
                append(freePiers.size()).append(" free piers right now");
        return res.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Port port = (Port) o;
        return freePiers.equals(port.freePiers) &&
                offeredPiers.equals(port.offeredPiers) &&
                lock.equals(port.lock) &&
                condition.equals(port.condition);
    }

    @Override
    public int hashCode() {
        int hash = 31;
        hash += freePiers.hashCode();
        hash += offeredPiers.hashCode();
        hash += lock.hashCode();
        hash += condition.hashCode();
        return hash;
    }
}
