/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.functionality.utilities;

import com.djrapitops.nmplayer.functionality.Track;
import java.util.ArrayList;
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
        List<Track> trackList = new ArrayList<>();
        Track track3 = new Track("b","b","c");
        trackList.add(track3);
        Track track2 = new Track("a","b","c");
        trackList.add(track2);
        Track track1 = new Track("a","a","c");
        trackList.add(track1);
        trackList.sort(new TrackComparator());
        assertEquals(track1, trackList.get(0));
        assertEquals(track2, trackList.get(1));
        assertEquals(track3, trackList.get(2));
    }
    
}
