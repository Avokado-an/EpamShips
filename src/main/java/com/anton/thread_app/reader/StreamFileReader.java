package com.anton.thread_app.reader;

import com.anton.thread_app.exception.MultiThreadException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StreamFileReader {
    private static final Logger LOGGER = LogManager.getLogger();

    public List<String> readFile(String fileName) throws MultiThreadException {
        if (fileName == null) {
            throw new MultiThreadException();
        }
        List<String> strings = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(strings::add);
            LOGGER.log(Level.INFO, "File was read properly");
        } catch (IOException e) {
            throw new MultiThreadException("file was not read");
        }
        return strings;
    }
}
