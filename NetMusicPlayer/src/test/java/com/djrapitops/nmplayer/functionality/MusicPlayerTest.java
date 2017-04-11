/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.functionality;

import java.lang.reflect.Field;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;

/**
 *
 * @author Rsl1122
 */
public class MusicPlayerTest {

    private MediaPlayer mockMp;

    /**
     *
     */
    public MusicPlayerTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     *
     */
    @Test
    public void testInit() {
    }

    /**
     *
     */
    @Test
    public void testSelectPlaylist() {
    }

    /**
     *
     */
    @Test
    public void testNextTrack() {
    }

    /**
     *
     */
    @Test
    public void testPreviousTrack() {
    }

    /**
     *
     */
    @Test
    public void testPlay() {
        
    }

    /**
     *
     */
    @Test
    public void testPause() {
    }

    /**
     *
     */
    @Test
    public void testStop() {
    }

    /**
     *
     */
    @Test
    public void testSelectTrack_int() {
    }

    /**
     *
     */
    @Test
    public void testSelectTrack_String() {
    }

    /**
     *
     */
    @Test
    public void testAddTrackToPlaylist() {
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
}
