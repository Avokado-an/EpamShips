package com.anton.thread_app.main;

import com.anton.thread_app.creator.ShipCreator;
import com.anton.thread_app.entity.Ship;
import com.anton.thread_app.entity.Storage;
import com.anton.thread_app.exception.MultiThreadException;
import com.anton.thread_app.reader.StreamFileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String FILE_NAME = "data/shipData.txt";

    public static void main(String[] args) {
        ShipCreator creator = new ShipCreator();
        StreamFileReader reader = new StreamFileReader();
        try {
            List<String> shipsData = reader.readFile(FILE_NAME);
            List<Ship> ships = creator.createShips(shipsData);
            LOGGER.info("Starting storage: " + Storage.getInstance().toString());
            ExecutorService executor = Executors.newFixedThreadPool(ships.size());
            List<Future<String>> futureShipsOperationInfo = new ArrayList<>();
            ships.forEach(s -> futureShipsOperationInfo.add(executor.submit(s)));
            futureShipsOperationInfo.forEach(info -> {
                try {
                    LOGGER.info(String.format("%s has finished", info.get()));
                } catch (InterruptedException | ExecutionException e) {
                    LOGGER.info("Error occurred");
                }
            });
            LOGGER.info("Final storage: " + Storage.getInstance().toString());
            executor.shutdown();
        } catch (MultiThreadException e) {
            LOGGER.error("Can't read file with data");
        }
    }
}
