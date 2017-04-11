/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.functionality;

import com.djrapitops.nmplayer.fileutils.PlaylistFileManager;
import com.djrapitops.nmplayer.fileutils.TrackFileManager;
import com.djrapitops.nmplayer.messaging.MessageSender;
import com.djrapitops.nmplayer.messaging.Phrase;
import com.djrapitops.nmplayer.functionality.playlist.PlaylistManager;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * This class contains all the logic used to change the playback (sound that is
 * coming out of the speakers).
 * <p>
 * THIS CLASS SHOULD NOT BE INITIALIZED WITH CLASS CONSTRUCTOR, MusicPlayer IS A
 * SINGLETON CLASS, use MusicPlayer.getInstance() instead.
 *
 * <p>
 * The class contains information about the current state of the player, as well
 * as a PlaylistManager
 *
 * @author Rsl1122
 * @see PlaylistManager
 */
public class MusicPlayer {

    private final PlaylistManager playlist;
    private final MessageSender msg;
    private MediaPlayer mp;

    private Track currentTrack;
    private int currentTrackIndex;
    private String selectedPlaylist;
    private boolean playing;

    /**
     * Class constructor.
     *
     * <p>
     * Creates a new PlaylistManager and grabs the instance of MessageSender for
     * easier access to sending messages.
     *
     * @see MessageSender
     */
    public MusicPlayer() {
        playlist = new PlaylistManager();
        msg = MessageSender.getInstance();
    }

    /**
     * Method used to start the playback logic.
     *
     * <p>
     * Selects a playlist "all" that contains all the tracks in other playlists
     * & the tracks folder.
     *
     * <p>
     * Sets the initial playing state to false.
     *
     * @throws IllegalStateException If a javafx Application is has not been
     * started yet.
     */
    public void init() throws IllegalStateException {
        selectPlaylist("all");
        playing = false;
    }

    /**
     * This method is used to change the playlist.
     * <p>
     * PlaylistFileManager is used to load the playlist file, and
     * TrackFileManager is used to translate the file contents into Track
     * objects.
     *
     * <p>
     * Then the playlist inside the PlaylistManager will be set as the new List
     * given by TrackFileManager.
     *
     * <p>
     * First track of the playlist will be selected for play.
     *
     * @param playlistName Name of the playlist
     * @throws IllegalStateException If a javafx Application is has not been
     * started yet.
     * @see PlaylistFileManager
     * @see TrackFileManager
     * @see PlaylistManager
     * @see selectTrack
     */
    public void selectPlaylist(String playlistName) throws IllegalStateException {
        selectedPlaylist = playlistName;
        playlist.setPlaylist(TrackFileManager.translateToTracks(PlaylistFileManager.load(selectedPlaylist)));
        currentTrackIndex = 0;
        if (playlist.isEmpty()) {
            msg.send(Phrase.PLAYLIST_EMPTY + "");            
            return;
        }
        selectTrack(playlist.selectTrack(currentTrackIndex));
    }

    /**
     * Used to move to the next track in the playlist.
     *
     * <p>
     * If currentTrack is null (Not initialized) nothing is done.
     * <p>
     * Otherwise the playback is stopped, new Track selected, and then played.
     *
     * @throws IllegalStateException If a javafx Application is has not been
     * started yet.
     * @see selectTrack
     */
    public void nextTrack() throws IllegalStateException {
        if (currentTrack != null) {
            if (playlist.isEmpty()) {
                return;
            }
            mp.stop();
            playing = false;
            selectTrack(playlist.selectTrack(currentTrackIndex + 1));
            play();
        }
    }

    /**
     * Used to move to the previous track in the playlist.
     *
     * <p>
     * If currentTrack is null (Not initialized) nothing is done.
     * <p>
     * Otherwise the playback is stopped, new Track selected, and then played.
     *
     * @see selectTrack
     * @throws IllegalStateException If a javafx Application is has not been
     * started yet.
     */
    public void previousTrack() throws IllegalStateException {
        if (currentTrack != null) {
            if (playlist.isEmpty()) {
                return;
            }
            mp.stop();
            playing = false;
            selectTrack(playlist.selectTrack(currentTrackIndex - 1));
            play();
        }
    }

    /**
     * Begins/Resumes the playback.
     *
     * <p>
     * If MusicPlayer is null (not initialized) nothing is done.
     * <p>
     * Playing Message will be sent with MessageSender
     *
     * @see MessageSender
     */
    public void play() {
        if (mp != null) {
            playing = true;
            mp.play();
            msg.send(Phrase.NOW_PLAYING.parse(currentTrack.toString()));
        }
    }

    /**
     * Pauses the playback.
     *
     * <p>
     * If MusicPlayer is null (not initialized) nothing is done.
     *
     * <p>
     * Pause Message will be sent with MessageSender
     *
     * @see MessageSender
     */
    public void pause() {
        if (mp != null && playing) {
            playing = false;
            mp.pause();
            msg.send(Phrase.PAUSE + "");
        }
    }

    /**
     * Stops the playback.
     *
     * <p>
     * If MusicPlayer is null (not initialized) nothing is done.
     * <p>
     * Stop Message will be sent with MessageSender
     *
     * @see MessageSender
     */
    public void stop() {
        if (mp != null) {
            playing = false;
            mp.stop();
            msg.send(Phrase.STOP + "");
        }
    }

    /**
     * Used to change the MediaPlayer object to play the Track.
     *
     * <p>
     * Creates a new MediaPlayer object with the filepath inside the Track
     * object, and frees the resources associated with the old MediaPlayer
     * object, if one exists.
     * <p>
     * If the file specified by Track object doesn't exist a message will be
     * sent with MessageSender.
     * <p>
     * After successfully creating the new MediaPlayer object, the currentTrack
     * information will be updated and a select message will be sent with
     * MessageSender
     *
     *
     * @param track Track to be played.
     * @throws IllegalStateException If a javafx Application is has not been
     * started yet.
     * @see MessageSender
     */
    public void selectTrack(Track track) throws IllegalStateException {
        if (track != null) {
            String mp3FilePath = track.getFilePath();
            File trackFile = new File(mp3FilePath);
            if (!trackFile.exists()) {
                msg.send("File doesn't exist! Restart application! " + track);
                return;
            }
            Media play = new Media(trackFile.toURI().toString());
            if (mp != null) {
                mp.dispose();
            }
            mp = new MediaPlayer(play);
            mp.setOnEndOfMedia(new Runnable() {
                @Override
                public void run() {
                    nextTrack();
                }
            });
            currentTrackIndex = playlist.getIndexOf(track);
            currentTrack = track;
            msg.send(Phrase.SELECTED.parse(currentTrack.toString()));
        }
    }

    /**
     * Adds a new Track object to the playlist in PlaylistManager.
     *
     * <p>
     * If the track given is null nothing is done.
     * <p>
     * Otherwise the track is added to playlist in PlaylistManager, add message
     * is sent with MessageSender and the new playlist is saved with
     * PlaylistFileManager.
     *
     * @param track Track to add to the playlist.
     * @throws IllegalStateException If a javafx Application is has not been
     * started yet.
     * @see PlaylistManager
     * @see PlaylistFileManager
     * @see MessageSender
     */
    public void addTrackToPlaylist(Track track) throws IllegalStateException {
        if (track == null) {
            return;
        }
        playlist.addTrackToPlaylist(track);
        msg.send(Phrase.ADDED_TRACK.parse(track.getArtist() + " - " + track.getName()));
        PlaylistFileManager.save(playlist.getPlaylist(), selectedPlaylist, true);
        selectTrack(track);
    }

    /**
     * Used to grab the MediaPlayer object that is currently handling the
     * playback.
     *
     * @return MediaPlayer that has the current track playback capability.
     */
    public MediaPlayer getMediaPlayer() {
        return mp;
    }

    /**
     * Tells wether or not the MusicPlayer has active playback going on.
     *
     * @return Is sound coming out of the speakers?
     */
    public boolean isPlaying() {
        return playing;
    }

    /**
     * Used to get the only instance of the MusicPlayer so that all of it's
     * methods can be accessed easily.
     *
     * @return INSTANCE created in the static class MusicPlayerSingletonHolder
     */
    public static MusicPlayer getInstance() {
        return MusicPlayerSingletonHolder.INSTANCE;
    }

    private static class MusicPlayerSingletonHolder {

        private static final MusicPlayer INSTANCE = new MusicPlayer();
    }
}
