/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.functionality;

import com.djrapitops.nmplayer.fileutils.TrackFileManager;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.media.MediaPlayer;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Risto
 */
public class MusicPlayerTest {
    
    public MusicPlayerTest() {
    }

    @Test
    public void testInit() {
    }

    @Test
    public void testSelectPlaylist() {
    }

    @Test
    public void testNextTrack() {
    }

    @Test
    public void testPreviousTrack() {
    }

    @Test
    public void testPlay() {
    }

    @Test
    public void testPause() {
    }

    @Test
    public void testStop() {
    }

    @Ignore("User Interface needs to be started") @Test
    public void testSelectTrack_int() {
    }

    @Ignore("User Interface needs to be started") @Test
    public void testSelectTrack_String() {
    }

    @Test
    public void testAddTrackToPlaylist() {
    }

    @Test
    public void testGetMediaPlayer() {
        MediaPlayer exp = null;
        MediaPlayer result = MusicPlayer.getInstance().getMediaPlayer();
        assertEquals(exp, result);
    }

    @Test
    public void testGetInstance() {
        MusicPlayer result = MusicPlayer.getInstance();
        assertTrue("Result is null", result != null);
    }
    
}
