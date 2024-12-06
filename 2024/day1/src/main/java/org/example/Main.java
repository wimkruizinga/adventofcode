package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
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

        System.out.printf("Total difference: %d%n", locationData.calculateTotalDistance());
        System.out.printf("Similarity score: %d%n", calculateSimilarity(locationData));
    }

    private static Integer calculateSimilarity(LocationData locationData) {
        Map<Integer, Integer> numberOccurrences = new HashMap<>();

        locationData.getListB().forEach(location -> {
            numberOccurrences.merge(location, 1, Integer::sum);
        });

        return locationData.getListA().stream()
                .mapToInt(location -> location * numberOccurrences.getOrDefault(location, 0))
                .sum();
    }
}
