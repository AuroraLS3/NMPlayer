/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.fileutils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Risto
 */
public class PlaylistFileManagerTest {

    public PlaylistFileManagerTest() {
    }

    @Before
    public void setUp() throws IOException {
        Files.deleteIfExists(new File(PlaylistFileManager.getPlaylistFolder(), "test.txt").toPath());
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
        expResult.add("testlink");
        if (!PlaylistFileManager.save(expResult, name)) {
            fail("Failed to save.");
        }
        assertTrue("Didn't create playlists folder", new File("playlists").exists());
        assertTrue("Didn't create playlist file", new File(PlaylistFileManager.getPlaylistFolder(), name+".txt").exists());
        List<String> result = PlaylistFileManager.load(name);
        assertEquals(expResult, result);
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
        int linesBefore = Files.lines(errors.toPath()).collect(Collectors.toList()).size();
        if (PlaylistFileManager.save(expResult, name)) {
            fail("Succeeded to save");
        }
        int linesNow = Files.lines(errors.toPath()).collect(Collectors.toList()).size();
        assertTrue("Did not catch IOException, is folder", linesBefore < linesNow);
        assertTrue("Didn't create playlists folder", new File("playlists").exists());
        assertTrue("Didn't create playlist file", !new File(PlaylistFileManager.getPlaylistFolder(), name+".txt").isFile());
        exceptionFolder.delete();
    }
    
    @Test
    public void testLoadException() throws IOException {
        System.out.println("  Test load for exception");
        String name = "testLoadException";
        File errors = new File("Errors.txt");
        ErrorManager.toLog("Test");
        int linesBefore = Files.lines(errors.toPath()).collect(Collectors.toList()).size();
        File exceptionFile = new File(PlaylistFileManager.getPlaylistFolder(), name+".txt");
        exceptionFile.mkdir();
        PlaylistFileManager.load(name);
        int linesNow = Files.lines(errors.toPath()).collect(Collectors.toList()).size();
        assertTrue("Did not catch IOException, pathseparator", linesBefore < linesNow);
    }

}
