/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.fileutils;

import com.djrapitops.nmplayer.functionality.Track;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Risto
 */
public class PlaylistFileManagerTest {

    public PlaylistFileManagerTest() {
    }

    @Before
    public void setUp() throws IOException {
    }

    @Test
    public void testGetPlaylistFolder() {
        System.out.println("  Test getPlaylistFolder");
        File expResult = new File("playlists");
        File result = PlaylistFileManager.getPlaylistFolder();
        assertEquals(expResult, result);
        assertTrue("Didn't create playlists folder", new File("playlists").exists());
    }

    @Test
    public void testSaveAndLoad() throws IOException {
        System.out.println("  Test load Method");
        String name = "testFile";
        List<String> expResult = new ArrayList<>();
        expResult.add("ST");
        List<Track> testTracks = new ArrayList<>();
        testTracks.add(new Track("T", "E", "ST"));
        if (!PlaylistFileManager.save(testTracks, name, true)) {
            fail("Failed to save.");
        }
        assertTrue("Didn't create playlists folder", new File("playlists").exists());
        File testFile = new File(PlaylistFileManager.getPlaylistFolder(), name+".txt");
        assertTrue("Didn't create playlist file", testFile.exists());
        List<String> result = PlaylistFileManager.load(name);
        assertEquals(expResult, result);
        testFile.deleteOnExit();
        Files.deleteIfExists(new File("testFile.txt").toPath());
    }
    
    @Test
    public void testSaveException() throws IOException {
        System.out.println("  Test save for exception");
        String name = "testSaveException";
        File exceptionFolder = new File(PlaylistFileManager.getPlaylistFolder(), name+".txt");
        exceptionFolder.mkdir();
        List<String> expResult = new ArrayList<>();
        expResult.add("testlink");
        File errors = new File("Errors.txt");
        ErrorManager.toLog("Test");
        long linesBefore = Files.lines(errors.toPath(),Charset.defaultCharset()).count();
        if (PlaylistFileManager.save(expResult, name)) {
            fail("Succeeded to save");
        }
        long linesNow = Files.lines(errors.toPath(),Charset.defaultCharset()).count();
        assertTrue("Did not catch IOException, is folder", linesBefore < linesNow);
        assertTrue("Didn't create playlists folder", new File("playlists").exists());
        assertTrue("Didn't create playlist file", !new File(PlaylistFileManager.getPlaylistFolder(), name+".txt").isFile());
        Files.deleteIfExists(exceptionFolder.toPath());
    }
    
    @Test
    public void testLoadException() throws IOException {
        System.out.println("  Test load for exception");
        String name = "testLoadException";
        File errors = new File("Errors.txt");
        ErrorManager.toLog("Test");
        long linesBefore = Files.lines(errors.toPath(),Charset.defaultCharset()).count();
        File exceptionFolder = new File(PlaylistFileManager.getPlaylistFolder(), name+".txt");
        exceptionFolder.mkdir();
        PlaylistFileManager.load(name);
        long linesNow = Files.lines(errors.toPath(),Charset.defaultCharset()).count();
        assertTrue("Did not catch IOException, pathseparator", linesBefore < linesNow);
        Files.deleteIfExists(exceptionFolder.toPath());
    }

}
