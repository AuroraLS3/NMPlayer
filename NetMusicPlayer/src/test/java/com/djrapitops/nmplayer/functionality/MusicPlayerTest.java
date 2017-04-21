/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.functionality;

import com.djrapitops.nmplayer.fileutils.PlaylistFileManager;
import com.djrapitops.nmplayer.fileutils.TrackFileManager;
import com.sun.javafx.application.PlatformImpl;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javafx.scene.media.MediaPlayer;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Rsl1122
 */
public class MusicPlayerTest {

    private MusicPlayer mp;
    private boolean calledProgressUpdate;
    private boolean calledUiUpdate;

    /**
     *
     */
    public MusicPlayerTest() {
    }

    /**
     *
     */
    @Before
    public void setUp() throws IOException {
        Files.deleteIfExists(new File(PlaylistFileManager.getPlaylistFolder(), "testplaylist.txt").toPath());
        PlatformImpl.startup(() -> {
        });
        mp = new MusicPlayer();
        calledProgressUpdate = false;
        calledUiUpdate = false;
        assertEquals("None", mp.getSelectedPlaylist());
        mp.setProgressBar(() -> {
            calledProgressUpdate = true;
        });
        mp.setEndOfMediaUpdate(() -> {
            calledUiUpdate = true;
        });
    }

    /**
     *
     */
    @After
    public void tearDown() {
        mp.stop();
        mp.getPlaylistManager().clearPlaylist();
    }

    /**
     *
     */
    @Test
//    @Ignore("IllegalStateException: Tookit not initialized.")
    public void testInit() {
        mp.init();
        assertEquals("all", mp.getSelectedPlaylist());
        assertTrue(mp.getCurrentTrack() != null);
        assertTrue(mp.getMediaPlayer() != null);
        assertTrue(mp.getPlaylistManager() != null);
    }

    /**
     *
     */
    @Test
    public void testSelectPlaylist() {
        mp.selectPlaylist("testplaylist");
        assertEquals("testplaylist", mp.getSelectedPlaylist());
    }

    /**
     *
     */
    @Test
    public void testNextTrack() {
        Track track = TrackFileManager.processFile(new File(TrackFileManager.getFolder(), "Dj Rapitops - Arrival.mp3"));
        Track track2 = TrackFileManager.processFile(new File(TrackFileManager.getFolder(), "Dj Rapitops - Evacuate.mp3"));
        mp.selectPlaylist("testplaylist");
        mp.addTrackToPlaylist(track);
        mp.addTrackToPlaylist(track2);
        assertTrue(!mp.getPlaylistManager().isEmpty());
        mp.selectTrack(0);
        mp.setVolume(0.0);
        mp.nextTrack();
        assertTrue(mp.isPlaying());
        assertEquals(1, mp.getPlaylistManager().getIndexOf(mp.getCurrentTrack()));
    }

    @Test
    public void testNextTrackLoops() {
        Track track = TrackFileManager.processFile(new File(TrackFileManager.getFolder(), "Dj Rapitops - Arrival.mp3"));
        Track track2 = TrackFileManager.processFile(new File(TrackFileManager.getFolder(), "Dj Rapitops - Evacuate.mp3"));
        mp.selectPlaylist("testplaylist");
        mp.addTrackToPlaylist(track);
        mp.addTrackToPlaylist(track2);
        assertTrue(!mp.getPlaylistManager().isEmpty());
        mp.selectTrack(track2);
        mp.setVolume(0.0);
        mp.nextTrack();
        assertTrue(mp.isPlaying());
        assertEquals(0, mp.getPlaylistManager().getIndexOf(mp.getCurrentTrack()));
    }

    /**
     *
     */
    @Test
    public void testPreviousTrack() {
        Track track = TrackFileManager.processFile(new File(TrackFileManager.getFolder(), "Dj Rapitops - Arrival.mp3"));
        Track track2 = TrackFileManager.processFile(new File(TrackFileManager.getFolder(), "Dj Rapitops - Evacuate.mp3"));
        mp.selectPlaylist("testplaylist");
        mp.addTrackToPlaylist(track);
        mp.addTrackToPlaylist(track2);
        assertTrue(!mp.getPlaylistManager().isEmpty());
        mp.selectTrack(track2);
        mp.setVolume(0.0);
        mp.previousTrack();
        assertTrue(mp.isPlaying());
        assertEquals(track, mp.getCurrentTrack());
    }

    @Test
    public void testPreviousTrackLoops() {
        Track track = TrackFileManager.processFile(new File(TrackFileManager.getFolder(), "Dj Rapitops - Arrival.mp3"));
        Track track2 = TrackFileManager.processFile(new File(TrackFileManager.getFolder(), "Dj Rapitops - Evacuate.mp3"));
        mp.selectPlaylist("testplaylist");
        mp.addTrackToPlaylist(track);
        mp.addTrackToPlaylist(track2);
        assertTrue(!mp.getPlaylistManager().isEmpty());
        mp.selectTrack(0);
        mp.setVolume(0.0);
        mp.previousTrack();
        assertTrue(mp.isPlaying());
        assertEquals(mp.getPlaylist().size() - 1, mp.getPlaylistManager().getIndexOf(mp.getCurrentTrack()));
    }

    /**
     *
     */
    @Test
    public void testPlay() {
        Track track = TrackFileManager.processFile(new File(TrackFileManager.getFolder(), "Dj Rapitops - Arrival.mp3"));
        mp.selectPlaylist("testplaylist");
        mp.addTrackToPlaylist(track);
        mp.selectTrack(track);
        mp.setVolume(0.0);
        mp.play();
        assertTrue(mp.isPlaying());
        assertEquals(track, mp.getCurrentTrack());
    }

    /**
     *
     */
    @Test
    public void testPause() {
        Track track = TrackFileManager.processFile(new File(TrackFileManager.getFolder(), "Dj Rapitops - Arrival.mp3"));
        mp.selectPlaylist("testplaylist");
        mp.addTrackToPlaylist(track);
        mp.selectTrack(track);
        mp.setVolume(0.0);
        mp.play();
        mp.pause();
        assertEquals(track, mp.getCurrentTrack());
        assertTrue(!mp.isPlaying());
    }

    /**
     *
     */
    @Test
    public void testStop() {
        Track track = TrackFileManager.processFile(new File(TrackFileManager.getFolder(), "Dj Rapitops - Arrival.mp3"));
        mp.selectPlaylist("testplaylist");
        mp.addTrackToPlaylist(track);
        mp.selectTrack(track);
        mp.setVolume(0.0);
        mp.play();
        mp.stop();
        assertEquals(track, mp.getCurrentTrack());
        assertTrue(!mp.isPlaying());

    }

    /**
     *
     */
    @Test
    public void testSelectTrack_int() {
        Track track = TrackFileManager.processFile(new File(TrackFileManager.getFolder(), "Dj Rapitops - Arrival.mp3"));
        mp.selectPlaylist("testplaylist");
        mp.addTrackToPlaylist(track);
        mp.selectTrack(0);
        assertEquals(track, mp.getCurrentTrack());
    }

    /**
     *
     */
    @Test
    public void testAddTrackToPlaylist() {
        Track track = new Track("1", "2", "3");
        mp.selectPlaylist("testplaylist");
        mp.addTrackToPlaylist(track);
        assertTrue(mp.getPlaylistManager().hasTrack(track));
    }

    /**
     *
     */
    @Test
    public void testGetMediaPlayer() {
        MediaPlayer exp = null;
        MediaPlayer result = MusicPlayer.getInstance().getMediaPlayer();
        assertEquals(exp, result);
    }

    /**
     *
     */
    @Test
    public void testGetInstance() {
        MusicPlayer result = MusicPlayer.getInstance();
        assertTrue("Result is null", result != null);
    }

    @Test
    public void testVolume() {
        MusicPlayer test = new MusicPlayer();
        test.setVolume(0.3);
        assertTrue("Volume not correct.", 0.3 == test.getVolume());
    }
    
    @Test
    public void testVolumeNegative() {
        MusicPlayer test = new MusicPlayer();
        test.setVolume(-0.1);
        assertTrue("Volume not correct.", 0.0 == test.getVolume());
    }
    
    @Test
    public void testVolumeOver1() {
        MusicPlayer test = new MusicPlayer();
        test.setVolume(1.3);
        assertTrue("Volume not correct.", 1.0 == test.getVolume());
    }

    @Test
    public void testProgressNullMP() {
        mp.setTrackPosition(0.5);
        assertTrue(0.0 == mp.getCurrentTrackProgress());
    }

    @Test
    public void testProgress() {
        Track track = TrackFileManager.processFile(new File(TrackFileManager.getFolder(), "Dj Rapitops - Arrival.mp3"));
        mp.selectPlaylist("testplaylist");
        mp.addTrackToPlaylist(track);
        mp.selectTrack(track);
        mp.setTrackPosition(0.5);
    }
    
    @Test
    public void testRemoveFromPlaylist() {
        Track track = TrackFileManager.processFile(new File(TrackFileManager.getFolder(), "Dj Rapitops - Arrival.mp3"));
        mp.selectPlaylist("testplaylist");
        mp.addTrackToPlaylist(track);
        assertTrue(mp.getPlaylistManager().hasTrack(track));
        mp.removeTrackFromPlaylist(track);
        assertTrue(!mp.getPlaylistManager().hasTrack(track));
    }
}
