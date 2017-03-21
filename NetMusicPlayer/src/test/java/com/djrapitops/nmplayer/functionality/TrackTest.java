/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.functionality;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Risto
 */
public class TrackTest {

    public TrackTest() {
    }

    @Test
    public void testEquals() {
        System.out.println("Test Track.equals");
        Object obj = new Track("1","2","3");
        Track instance = new Track("1","2","3");
        assertTrue("Not Equals!", instance.equals(obj));
    }
    
    @Test
    public void testEquals2() {
        System.out.println("Test Track.equals (not equal)");
        Object obj = new Track("1","2","4");
        Track instance = new Track("1","2","3");
        assertTrue("Equals!", !instance.equals(obj));
    }

}
