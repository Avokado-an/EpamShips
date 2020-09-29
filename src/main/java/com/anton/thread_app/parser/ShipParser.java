package com.anton.thread_app.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShipParser {
    public List<Integer> parseShipsData(String data) {
        String space = " ";
        List<Integer> argument = new ArrayList<>();
        String[] elements = data.split(space);
        Arrays.stream(elements).mapToInt(Integer::parseInt).forEach(argument::add);
        return argument;
    }
}
