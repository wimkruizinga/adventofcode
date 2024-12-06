package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws IOException {
        LocationData locationData = new LocationData();
        var path = Paths.get("src/main/resources/input.txt").toAbsolutePath();

        try (var reader = Files.newBufferedReader(path)) {
            reader.lines().forEach(l -> {
                var tokenizer = new StringTokenizer(l);
                locationData.getListA().add(Integer.valueOf(tokenizer.nextToken()));
                locationData.getListB().add(Integer.valueOf(tokenizer.nextToken()));
            });
        }

        locationData.sortLists();

        System.out.printf("Total difference: %d\n", locationData.calculateTotalDistance());
    }
}
