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
import org.junit.Test;
import static org.junit.Assert.*;

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
    public void testTranslateToTracks() {
        List<String> paths = new ArrayList<>();
        final String path1 = new File(TrackFileManager.getFolder(), "Dj Rapitops - Arrival.mp3").getAbsolutePath();
        paths.add(path1);
        final String path2 = new File(TrackFileManager.getFolder(), "Dj Rapitops - Evacuate.mp3").getAbsolutePath();
        paths.add(path2);
        paths.add(new File("UnexistingTestFile").getAbsolutePath());
        List<Track> result = TrackFileManager.translateToTracks(paths);
        List<Track> exp = new ArrayList<>();
        exp.add(new Track("Arrival", "Dj Rapitops", path1));
        exp.add(new Track("Evacuate", "Dj Rapitops", path2));
        assertEquals(exp, result);
        
    }

    @Test
    public void testProcessFileNull() {
        Track result = TrackFileManager.processFile(null);
        Track exp = null;
        assertEquals(exp, result);
    }

    @Test
    public void testProcessFileNonexistent() {
        Track result = TrackFileManager.processFile(new File("UnexistingTestFile"));
        Track exp = null;
        assertEquals(exp, result);
    }

    @Test
    public void testProcessFileUnreadable() throws IOException {
        Files.deleteIfExists(new File("NonReadableTestFile").toPath());
        final File testFile = new File("NonReadableTestFile");
        testFile.createNewFile();
        testFile.setReadable(false);
        Track result = TrackFileManager.processFile(testFile);
        Files.deleteIfExists(testFile.toPath());
        Track exp = null;
        assertEquals(exp, result);
    }

    @Test
    public void testProcessFileNotMp3() throws IOException {
        Files.deleteIfExists(new File("NonMp3TestFile.txt").toPath());
        final File testFile = new File("NonMp3TestFile.txt");
        testFile.createNewFile();
        Track result = TrackFileManager.processFile(testFile);
        Files.deleteIfExists(testFile.toPath());
        Track exp = null;
        assertEquals(exp, result);
    }

    @Test
    public void testProcessFileExisting() {
        File testTrack = new File(TrackFileManager.getFolder(), "Dj Rapitops - Arrival.mp3");
        if (!testTrack.exists()) {
            System.out.println("Testtrack doesn't exist");
        }
        Track result = TrackFileManager.processFile(testTrack);
        Track exp = new Track("Arrival", "Dj Rapitops", testTrack.getAbsolutePath());
        assertEquals(exp, result);
    }
    
    @Test
    public void testGetArtist() {
        File testTrack = new File(TrackFileManager.getFolder(), "Dj Rapitops - Arrival.mp3");
        if (!testTrack.exists()) {
            System.out.println("Testtrack doesn't exist");
        }
        String result = TrackFileManager.getArtist(testTrack);
        String exp = "Dj Rapitops";
        assertEquals(exp, result);
    }
    
    @Test
    public void testGetArtistNotMp3() throws IOException {        
        Files.deleteIfExists(new File(TrackFileManager.getFolder(), "Dj Rapitops - Arrival").toPath());
        File testTrack = new File(TrackFileManager.getFolder(), "Dj Rapitops - Arrival");
        testTrack.createNewFile();        
        String result = TrackFileManager.getArtist(testTrack);
        String exp = "Artist";
        Files.deleteIfExists(testTrack.toPath());
        assertEquals(exp, result);
    }

    @Test
    public void testGetTrackName() {
        File testTrack = new File(TrackFileManager.getFolder(), "Dj Rapitops - Arrival.mp3");
        if (!testTrack.exists()) {
            System.out.println("Testtrack doesn't exist");
        }
        String result = TrackFileManager.getTrackName(testTrack);
        String exp = "Arrival";
        assertEquals(exp, result);
    }
    
    @Test
    public void testGetTrackNameNotMp3() throws IOException {
        Files.deleteIfExists(new File(TrackFileManager.getFolder(), "Dj Rapitops - Arrival").toPath());
        File testTrack = new File(TrackFileManager.getFolder(), "Dj Rapitops - Arrival");
        testTrack.createNewFile();
        Files.deleteIfExists(testTrack.toPath());
        String result = TrackFileManager.getTrackName(testTrack);
        String exp = "Track";
        assertEquals(exp, result);
    }
}
