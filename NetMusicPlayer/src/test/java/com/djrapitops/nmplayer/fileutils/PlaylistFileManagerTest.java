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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws IOException {
        Files.deleteIfExists(new File(PlaylistFileManager.getPlaylistFolder(), "test.txt").toPath());
    }

    @After
    public void tearDown() {
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
    public void testSaveAndLoad() {
        System.out.println("  Test load Method");
        String name = "test";
        List<String> expResult = new ArrayList<>();
        expResult.add("testlink");
        if (!PlaylistFileManager.save(expResult, name)) {
            fail("Failed to save.");
        }
        assertTrue("Didn't create playlists folder", new File("playlists").exists());
        List<String> result = PlaylistFileManager.load(name);
        assertEquals(expResult, result);        
    }

}
