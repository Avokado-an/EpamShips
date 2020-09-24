package com.anton.thread_app.entity;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Port {
    private static final int DEFAULT_PIERS_AMOUNT = 6;
    private static final Port instance = new Port();
    private static final Storage storage = Storage.getInstance();
    private Semaphore semaphore = new Semaphore(DEFAULT_PIERS_AMOUNT);
    Queue<Pier> freePiers;
    Deque<Pier> offeredPiers;

    private Port() {
        freePiers = new LinkedList<>();
        offeredPiers = new ArrayDeque<>();
        for (int i = 0; i < DEFAULT_PIERS_AMOUNT; i++) {
            freePiers.add(new Pier(i));
        }
    }

    public static Port getInstance() {
        return instance;
    }

    public Pier visitPier() {
        return null;
    }

    public void leavePier(int id) {

    }
}
