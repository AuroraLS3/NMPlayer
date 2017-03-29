/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.fileutils;

import com.djrapitops.nmplayer.functionality.Track;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Risto
 */
public class TrackFileManagerTest {

    public TrackFileManagerTest() {
    }
    
    @Test
    public void testGetFolder() {
        File folder = TrackFileManager.getFolder();
        assertTrue("Didn't create folder", folder.exists());
        assertEquals(new File("tracks"), folder);
    }
    
    @Test
    public void testTranslateToTracks() throws IOException {
        System.out.println("Test Saving of Register file and translation of Playlist paths from Register to Track Objects");
        File reg = TrackFileManager.createRegisterFile("testReg.txt");
        Files.deleteIfExists(reg.toPath());
        List<Track> tracks = new ArrayList<>();
        tracks.add(new Track("Test1","1","Testpath1"));
        tracks.add(new Track("Test2","2","Testpath2"));
        tracks.add(new Track("Test3","2","Testpath3"));
        tracks.add(new Track("Test4","4","Testpath4"));
        tracks.add(new Track("Test5","5","Testpath5"));
        tracks.add(new Track("Test6","6","Testpath6"));        
        boolean expResult = true;
        boolean result = TrackFileManager.saveRegisterFile(tracks, "testreg.txt");
        assertEquals(expResult, result);
        assertTrue("Did not create testreg.txt", reg.exists());
        assertTrue("Did not write testreg", Files.lines(reg.toPath()).collect(Collectors.toList()).size() == tracks.size());
        List<String> playlist = tracks.stream().map(track -> track.getFilePath()).collect(Collectors.toList());
        List<Track> resultTracks = TrackFileManager.translateToTracks(playlist, "testreg.txt");
        assertTrue("Tracklist did not contain all track information: "+resultTracks.size()+"/"+tracks.size(), resultTracks.containsAll(tracks));
        Files.deleteIfExists(reg.toPath());
    }

    @Ignore("Download method not ready") @Test
    public void testDownload() {
        System.out.println("download");
        String trackAddress = "";
        Track expResult = null;
        Track result = TrackFileManager.download(trackAddress);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
