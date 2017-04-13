/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.functionality.utilities;

import com.djrapitops.nmplayer.functionality.Track;
import java.util.Comparator;

/**
 *
 * @author Risto
 */
public class TrackComparator implements Comparator<Track> {

    @Override
    public int compare(Track o1, Track o2) {
        return o1.toString().compareTo(o2.toString());
    }
    
}
