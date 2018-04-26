/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.functionality;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TrackTest {

    @Test
    public void testGetArtist() {
        Track t = new Track("1", "2", "3");
        assertEquals("2", t.getArtist());
    }

    @Test
    public void testEquals() {
        System.out.println("Test Track.equals");
        Object obj = new Track("1", "2", "3");
        Track instance = new Track("1", "2", "3");
        assertTrue("Not Equals!", instance.equals(obj));
    }

    @Test
    public void testEquals2() {
        System.out.println("Test Track.equals (not equal)");
        Object obj = new Track("1", "2", "4");
        Track instance = new Track("1", "2", "3");
        assertTrue("Equals!", !instance.equals(obj));
    }

    @Test
    public void testEquals3() {
        System.out.println("Test Track.equals (Same object)");
        Object obj = new Track("1", "2", "4");
        Track instance = (Track) obj;
        assertTrue("Not Equals!", instance.equals(obj));
    }

    @Test
    public void testEquals4() {
        System.out.println("Test Track.equals (null)");
        Track instance = new Track("1", "2", "3");
        assertTrue("Equals!", !instance.equals(null));
    }

    /**
     *
     */
    @Test
    public void testEquals5() {
        System.out.println("Test Track.equals (not equal)");
        Object obj = new Track("1", "1", "3");
        Track instance = new Track("1", "2", "3");
        assertTrue("Equals!", !instance.equals(obj));
    }

    /**
     *
     */
    @Test
    public void testEquals6() {
        System.out.println("Test Track.equals (not equal)");
        Object obj = new Track("2", "2", "3");
        Track instance = new Track("1", "2", "3");
        assertTrue("Equals!", !instance.equals(obj));
    }

    /**
     *
     */
    @Test
    public void testEquals7() {
        System.out.println("Test Track.equals (Wrong object)");
        Object obj = "test";
        Track instance = new Track("1", "2", "3");
        assertTrue("Equals!", !instance.equals(obj));
    }

    /**
     *
     */
    @Test
    public void testToString() {
        System.out.println("Test Track.toString");
        Track instance = new Track("1", "2", "3");
        assertEquals("2 - 1", instance.toString());
    }
}
