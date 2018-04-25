/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.fileutils;

import com.djrapitops.nmplayer.functionality.Track;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static com.djrapitops.nmplayer.fileutils.PlaylistFileManager.getPlaylistFolder;
import static org.junit.Assert.*;

public class PlaylistFileManagerTest {

    public PlaylistFileManagerTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void testGetPlaylistFolder() {
        PlaylistFileManager t = new PlaylistFileManager();
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
        if (!PlaylistFileManager.saveTracksAsPlaylist(testTracks, name)) {
            fail("Failed to saveTracksAsPlaylist.");
        }
        assertTrue("Didn't create playlists folder", new File("playlists").exists());
        File testFile = new File(PlaylistFileManager.getPlaylistFolder(), name + ".txt");
        assertTrue("Didn't create playlist file", testFile.exists());
        List<String> result = PlaylistFileManager.load(name);
        assertEquals(expResult, result);
        testFile.deleteOnExit();
        Files.deleteIfExists(new File("testFile.txt").toPath());
    }

    @Test
    public void testSaveException() throws IOException {
        System.out.println("  Test saveTracksAsPlaylist for exception");
        String name = "testSaveException";
        File exceptionFolder = new File(PlaylistFileManager.getPlaylistFolder(), name + ".txt");
        exceptionFolder.mkdir();
        List<String> expResult = new ArrayList<>();
        expResult.add("testlink");
        File errors = new File("Errors.txt");
        ErrorManager.toLog("Test");
        long linesBefore = getLineCount(errors);
        if (PlaylistFileManager.save(expResult, name)) {
            fail("Succeeded to saveTracksAsPlaylist");
        }
        long linesNow = getLineCount(errors);
        assertTrue("Did not catch IOException, is folder", linesBefore < linesNow);
        assertTrue("Didn't create playlists folder", new File("playlists").exists());
        assertTrue("Didn't create playlist file", !new File(PlaylistFileManager.getPlaylistFolder(), name + ".txt").isFile());
        Files.deleteIfExists(new File(PlaylistFileManager.getPlaylistFolder(), name + ".txt").toPath());
    }

    private int getLineCount(File errors) throws IOException {
        return FileReader.lines(errors).size();
    }

    @Test
    public void testLoadException() throws IOException {
        System.out.println("  Test load for exception");
        String name = "testLoadException";
        File errors = new File("Errors.txt");
        ErrorManager.toLog("Test");
        long linesBefore = getLineCount(errors);
        File exceptionFolder = new File(PlaylistFileManager.getPlaylistFolder(), name + ".txt");
        exceptionFolder.mkdir();
        PlaylistFileManager.load(name);
        long linesNow = getLineCount(errors);
        assertTrue("Did not catch IOException, pathseparator", linesBefore < linesNow);
        Files.deleteIfExists(exceptionFolder.toPath());
    }

    @Test
    public void testGetKnownPlaylists() {
        String knownPlaylists = PlaylistFileManager.getKnownPlaylists();
        File[] files = PlaylistFileManager.getPlaylistFolder().listFiles();
        for (File file : files) {
            if (file.getName().equals("all.txt")) {
                continue;
            }
            if (file.isDirectory()) {
                assertTrue("Included a directory " + file.toString() + " | " + knownPlaylists, !knownPlaylists.toLowerCase().contains(file.getName().toLowerCase().replace(".txt", "")));
            }
            int tracks = 0;
            try {
                tracks = getLineCount(file);
            } catch (IOException ex) {
            }
            if (tracks > 0) {
                assertTrue("Didn't include " + file.toString() + " | " + knownPlaylists, knownPlaylists.toLowerCase().contains(file.getName().toLowerCase().replace(".txt", "")));
            } else {
                assertTrue("Included a list with 0 paths " + file.toString() + " | " + knownPlaylists, !knownPlaylists.toLowerCase().contains(file.getName().toLowerCase().replace(".txt", "")));
            }
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
            assertTrue(loaded.containsAll(FileReader.lines(file)));
        }
        for (File trackF : TrackFileManager.getFolder().listFiles()) {
            boolean isSupportedFileType = (trackF.getName().endsWith(".mp3") || trackF.getName().endsWith(".wav"));
            if (trackF.isDirectory() || !trackF.canRead() || !isSupportedFileType) {
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
            assertTrue(loaded.containsAll(FileReader.lines(file)));
        }
        for (File trackF : TrackFileManager.getFolder().listFiles()) {
            boolean isSupportedFileType = (trackF.getName().endsWith(".mp3") || trackF.getName().endsWith(".wav"));
            if (trackF.isDirectory() || !trackF.canRead() || !isSupportedFileType) {
                continue;
            }
            assertTrue("Didn't contain " + trackF.toString(), loaded.contains(trackF.getAbsolutePath()));
        }
    }
}
