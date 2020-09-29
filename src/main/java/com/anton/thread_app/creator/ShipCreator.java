package com.anton.thread_app.creator;

import com.anton.thread_app.entity.Ship;
import com.anton.thread_app.parser.ShipParser;

import java.util.ArrayList;
import java.util.List;

public class ShipCreator {
    public List<Ship> createShips(List<String> data) {
        List<Ship> ships = new ArrayList<>();
        List<Integer> arguments;
        int idIndex = 0;
        int totalCapacityIndex = 1;
        int currentLoadIndex = 2;
        ShipParser parser = new ShipParser();
        for(String info : data) {
            arguments = parser.parseShipsData(info);
            ships.add(
                    new Ship(arguments.get(idIndex), arguments.get(totalCapacityIndex), arguments.get(currentLoadIndex))
            );
        }
        return ships;
    }
}
