package com.djrapitops.nmplayer.functionality;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Risto
 */
public class RandomOrderUtilityTest {
    
    public RandomOrderUtilityTest() {
    }

    @Test
    public void testCorrectSize() {
        int exp = 100;
        RandomOrderUtility i = new RandomOrderUtility(100, 0);
        List<Integer> result = i.getIndexOrderList();
        assertEquals(exp, result.size());
    }
    
    @Test
    public void testCurrentIndexFirst() {
        int exp = 0;
        RandomOrderUtility i = new RandomOrderUtility(100, 0);
        int result = i.getIndexOrderList().get(0);
        assertEquals(exp, result);
    }
    
    @Test
    public void testCurrentIndexFirst5() {
        int exp = 5;
        RandomOrderUtility i = new RandomOrderUtility(100, 5);
        int result = i.getIndexOrderList().get(0);
        assertEquals(exp, result);
    }
    
    @Test
    public void testGetNewIndex() {
        RandomOrderUtility i = new RandomOrderUtility(10, 0);
        int exp = i.getIndexOrderList().get(5);
        int result = i.getNewIndexFromOrder(5);
        assertEquals(exp, result);
    }
    
    @Test
    public void testShuffled() {
        RandomOrderUtility r = new RandomOrderUtility(1000, 0);
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {            
            numbers.add(i);
        }
        assertTrue(!r.getIndexOrderList().equals(numbers));
    }
    
    @Test
    public void testContainsAllNumbers() {
        RandomOrderUtility r = new RandomOrderUtility(1000, 0);
        List<Integer> test = r.getIndexOrderList();
        Set<Integer> unique = new HashSet<>();
        unique.addAll(test);
        assertEquals(test.size(), unique.size());
    }
    
    @Test
    public void testContainsAllUnique() {
        RandomOrderUtility r = new RandomOrderUtility(1000, 0);
        List<Integer> test = r.getIndexOrderList();
        List<Integer> unique = test.stream().distinct().collect(Collectors.toList());
        assertEquals(test.size(), unique.size());
    }
}
