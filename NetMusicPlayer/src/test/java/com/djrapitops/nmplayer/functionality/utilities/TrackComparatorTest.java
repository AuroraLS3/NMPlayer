/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.functionality.utilities;

import com.djrapitops.nmplayer.functionality.Track;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Risto
 */
public class TrackComparatorTest {
    
    public TrackComparatorTest() {
    }

    @Test
    public void testCompare() {
        List<Track> a = new ArrayList<>();
        Track t3 = new Track("b","b","c");
        a.add(t3);
        Track t2 = new Track("a","b","c");
        a.add(t2);
        Track t1 = new Track("a","a","c");
        a.add(t1);
        Collections.sort(a, new TrackComparator());
        assertEquals(t1, a.get(0));
        assertEquals(t2, a.get(1));
        assertEquals(t3, a.get(2));
    }
    
}
