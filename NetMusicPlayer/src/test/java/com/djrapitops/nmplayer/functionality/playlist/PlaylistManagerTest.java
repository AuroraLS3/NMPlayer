/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.functionality.playlist;

import com.djrapitops.nmplayer.functionality.Track;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Risto
 */
public class PlaylistManagerTest {

    public PlaylistManagerTest() {
    }

    @Test
    public void testAddFilePathToPlaylist() {
        System.out.println("Test PlaylistManager.addFilePathToPlaylist");
        Track t = new Track("1", "2", "3");
        PlaylistManager instance = new PlaylistManager();
        instance.addFilePathToPlaylist(t);
        assertTrue("Did not add track", instance.getPlaylist().size() > 0);
        assertTrue("Did not add correct path", instance.getPlaylist().get(0).getFilePath().equals("3"));
    }

    @Test
    public void testRemoveFilePathFromPlaylist() {
        System.out.println("Test PlaylistManager.removeFilePathFromPlaylist");
        Track t = new Track("1", "2", "3");
        PlaylistManager instance = new PlaylistManager();
        instance.addFilePathToPlaylist(t);
        assertTrue("Did not add track", instance.getPlaylist().size() > 0);
        t = new Track("1", "2", "3");
        instance.removeFilePathFromPlaylist(t);
        assertTrue("Did not remove track", instance.getPlaylist().isEmpty());
    }

    @Test
    public void testClearPlaylist() {
        System.out.println("Test PlaylistManager.clearPlaylist");
        Track t = new Track("1", "2", "3");
        PlaylistManager instance = new PlaylistManager();
        instance.addFilePathToPlaylist(t);
        assertTrue("Did not add track", instance.getPlaylist().size() > 0);
        instance.clearPlaylist();
        assertTrue("Did not remove track", instance.getPlaylist().isEmpty());
    }

    @Test
    public void testGetPlaylist() {
        System.out.println("Test PlaylistManager.getPlaylist");
        PlaylistManager instance = new PlaylistManager();
        List<Track> expResult = new ArrayList<>();
        List<Track> result = instance.getPlaylist();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetPlaylist() {
        System.out.println("Test PlaylistManager.setPlaylist");
        PlaylistManager instance = new PlaylistManager();
        List<Track> expResult = new ArrayList();
        expResult.add(new Track("1", "2", "3"));
        expResult.add(new Track("2", "4", "5"));
        expResult.add(new Track("3", "7", "6"));
        expResult.add(new Track("5", "32", "345"));
        expResult.add(new Track("1", "Unknown", "3437"));
        instance.setPlaylist(expResult);
        List<Track> result = instance.getPlaylist();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetTrackByName() {
        System.out.println("Test PlaylistManager.getTrackByName");
        String trackName = "3";
        PlaylistManager instance = new PlaylistManager();
        instance.addFilePathToPlaylist(new Track("1", "2", "3"));
        instance.addFilePathToPlaylist(new Track("2", "4", "5"));
        instance.addFilePathToPlaylist(new Track("3", "7", "6"));
        instance.addFilePathToPlaylist(new Track("5", "32", "345"));
        instance.addFilePathToPlaylist(new Track("1", "Unknown", "3437"));
        Track expResult = new Track("3", "7", "6");
        Track result = instance.getTrackByName(trackName);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetTrackByName2() {
        System.out.println("Test PlaylistManager.getTrackByName when track doesn't exist");
        String trackName = "4";
        PlaylistManager instance = new PlaylistManager();
        instance.addFilePathToPlaylist(new Track("1", "2", "3"));
        instance.addFilePathToPlaylist(new Track("2", "4", "5"));
        instance.addFilePathToPlaylist(new Track("3", "7", "6"));
        instance.addFilePathToPlaylist(new Track("5", "32", "345"));
        instance.addFilePathToPlaylist(new Track("1", "Unknown", "3437"));
        Track expResult = null;
        Track result = instance.getTrackByName(trackName);
        assertEquals(expResult, result);
    }

    @Test
    public void testIsEmpty() {
        PlaylistManager instance = new PlaylistManager();
        assertTrue("Not empty", instance.isEmpty());
        instance.addFilePathToPlaylist(new Track("1", "2", "3"));
        assertTrue("Empty", !instance.isEmpty());
    }
}
