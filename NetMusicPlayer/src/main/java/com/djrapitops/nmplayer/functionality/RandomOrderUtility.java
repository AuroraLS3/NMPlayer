package com.djrapitops.nmplayer.functionality;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class is used for selecting tracks in random order consistently.
 *
 * @author Rsl1122
 */
public class RandomOrderUtility {

    private List<Integer> indexOrder;

    /**
     * Creates a new Random order with the currentIndex at index 0.
     *
     * @param playlistSize Size of required random order.
     * @param currentIndex Index to set to index 0
     */
    public RandomOrderUtility(int playlistSize, int currentIndex) {
        indexOrder = new ArrayList<>();
        indexOrder.add(currentIndex);
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < playlistSize; i++) {
            if (i == currentIndex) {
                continue;
            }
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        indexOrder.addAll(numbers);
    }

    /**
     * Used to get the index number in the index spot.
     *
     * @param index index on the list.
     * @return number on the list.
     */
    public int getNewIndexFromOrder(int index) {
        return indexOrder.get(index);
    }

    /**
     * Used to access the list.
     *
     * @return The list of numbers used.
     */
    public List<Integer> getIndexOrderList() {
        return indexOrder;
    }
}
