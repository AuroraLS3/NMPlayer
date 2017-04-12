/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.fileutils;

import static com.djrapitops.nmplayer.fileutils.PlaylistFileManager.getPlaylistFolder;
import static com.djrapitops.nmplayer.fileutils.PlaylistFileManager.load;
import com.djrapitops.nmplayer.functionality.Track;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rsl1122
 */
public class PlaylistFileManagerTest {

    /**
     *
     */
    public PlaylistFileManagerTest() {
    }

    /**
     *
     * @throws IOException
     */
    @Before
    public void setUp() throws IOException {
    }

    /**
     *
     */
    @Test
    public void testGetPlaylistFolder() {
        PlaylistFileManager t = new PlaylistFileManager();
        System.out.println("  Test getPlaylistFolder");
        File expResult = new File("playlists");
        File result = PlaylistFileManager.getPlaylistFolder();
        assertEquals(expResult, result);
        assertTrue("Didn't create playlists folder", new File("playlists").exists());
    }

    /**
     *
     * @throws IOException
     */
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
        File testFile = new File(PlaylistFileManager.getPlaylistFolder(), name + ".txt");
        assertTrue("Didn't create playlist file", testFile.exists());
        List<String> result = PlaylistFileManager.load(name);
        assertEquals(expResult, result);
        testFile.deleteOnExit();
        Files.deleteIfExists(new File("testFile.txt").toPath());
    }

    /**
     *
     * @throws IOException
     */
    @Test
    public void testSaveException() throws IOException {
        System.out.println("  Test save for exception");
        String name = "testSaveException";
        File exceptionFolder = new File(PlaylistFileManager.getPlaylistFolder(), name + ".txt");
        exceptionFolder.mkdir();
        List<String> expResult = new ArrayList<>();
        expResult.add("testlink");
        File errors = new File("Errors.txt");
        ErrorManager.toLog("Test");
        long linesBefore = Files.lines(errors.toPath(), Charset.defaultCharset()).count();
        if (PlaylistFileManager.save(expResult, name)) {
            fail("Succeeded to save");
        }
        long linesNow = Files.lines(errors.toPath(), Charset.defaultCharset()).count();
        assertTrue("Did not catch IOException, is folder", linesBefore < linesNow);
        assertTrue("Didn't create playlists folder", new File("playlists").exists());
        assertTrue("Didn't create playlist file", !new File(PlaylistFileManager.getPlaylistFolder(), name + ".txt").isFile());
        Files.deleteIfExists(new File(PlaylistFileManager.getPlaylistFolder(), name + ".txt").toPath());
    }

    /**
     *
     * @throws IOException
     */
    @Test
    public void testLoadException() throws IOException {
        System.out.println("  Test load for exception");
        String name = "testLoadException";
        File errors = new File("Errors.txt");
        ErrorManager.toLog("Test");
        long linesBefore = Files.lines(errors.toPath(), Charset.defaultCharset()).count();
        File exceptionFolder = new File(PlaylistFileManager.getPlaylistFolder(), name + ".txt");
        exceptionFolder.mkdir();
        PlaylistFileManager.load(name);
        long linesNow = Files.lines(errors.toPath(), Charset.defaultCharset()).count();
        assertTrue("Did not catch IOException, pathseparator", linesBefore < linesNow);
        Files.deleteIfExists(exceptionFolder.toPath());
    }

    @Test
    public void testGetKnownPlaylists() {
        String knownPlaylists = PlaylistFileManager.getKnownPlaylists();
        File[] files = PlaylistFileManager.getPlaylistFolder().listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assertTrue("Included a directory "+ file.toString() + " | " + knownPlaylists, !knownPlaylists.toLowerCase().contains(file.getName().toLowerCase().replace(".txt", "")));
            }
            assertTrue("Didn't include " + file.toString() + " | " + knownPlaylists, knownPlaylists.toLowerCase().contains(file.getName().toLowerCase().replace(".txt", "")));
        }
    }

    @Test
    public void testLoadAll() throws IOException {
        List<String> loaded = PlaylistFileManager.loadAll();
        File playlistFolder = getPlaylistFolder();
        File[] files = playlistFolder.listFiles();
        for (File file : files) {
            if (file.isDirectory() || !file.canRead() || !file.getName().endsWith(".txt") || file.getName().equals("all.txt")) {
                continue;
            }
            for (String s : Files.lines(file.toPath(), Charset.defaultCharset()).collect(Collectors.toList())) {
                assertTrue("Didn't contain " + s, loaded.contains(s));
            }
        }
        for (File trackF : TrackFileManager.getFolder().listFiles()) {
            if (trackF.isDirectory() || !trackF.canRead() || !trackF.getName().endsWith(".mp3")) {
                continue;
            }
            assertTrue("Didn't contain " + trackF.toString(), loaded.contains(trackF.getAbsolutePath()));
        }
    }
    
    @Test
    public void testLoad_All() throws IOException {
        List<String> loaded = PlaylistFileManager.load("all");
        File playlistFolder = getPlaylistFolder();
        File[] files = playlistFolder.listFiles();
        for (File file : files) {
            if (file.isDirectory() || !file.canRead() || !file.getName().endsWith(".txt") || file.getName().equals("all.txt")) {
                continue;
            }
            for (String s : Files.lines(file.toPath(), Charset.defaultCharset()).collect(Collectors.toList())) {
                assertTrue("Didn't contain " + s, loaded.contains(s));
            }
        }
        for (File trackF : TrackFileManager.getFolder().listFiles()) {
            if (trackF.isDirectory() || !trackF.canRead() || !trackF.getName().endsWith(".mp3")) {
                continue;
            }
            assertTrue("Didn't contain " + trackF.toString(), loaded.contains(trackF.getAbsolutePath()));
        }
    }
}
