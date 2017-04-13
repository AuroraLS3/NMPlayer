package com.djrapitops.nmplayer.functionality.utilities;

import com.djrapitops.nmplayer.functionality.Track;
import java.util.Comparator;

/**
 * This comparator is used to alphabetically sort Track objects.
 *
 * @author Risto
 */
public class TrackComparator implements Comparator<Track> {

    @Override
    public int compare(Track o1, Track o2) {
        return o1.toString().compareTo(o2.toString());
    }

}
