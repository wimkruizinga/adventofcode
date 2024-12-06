package org.example;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class LocationData {
    private List<Integer> listA;
    private List<Integer> listB;

    public LocationData() {
        this.listA = new ArrayList<>();
        this.listB = new ArrayList<>();
    }

    public Integer calculateTotalDistance() {
        sortLists();
        var pairs = listA.size();
        int totalDistance = 0;
        for (int i = 0; i < pairs; i++) {
            totalDistance += Math.abs(listA.get(i) - listB.get(i));
        }

        return totalDistance;
    }

    private void sortLists() {
        Collections.sort(listA);
        Collections.sort(listB);
    }
}
