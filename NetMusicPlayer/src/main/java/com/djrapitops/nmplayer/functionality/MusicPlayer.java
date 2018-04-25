package com.djrapitops.nmplayer.functionality;

import com.djrapitops.nmplayer.fileutils.PlaylistFileManager;
import com.djrapitops.nmplayer.fileutils.TrackFileManager;
import com.djrapitops.nmplayer.functionality.utilities.TextUtils;
import com.djrapitops.nmplayer.functionality.utilities.TrackComparator;
import com.djrapitops.nmplayer.messaging.MessageSender;
import com.djrapitops.nmplayer.messaging.Phrase;
import com.djrapitops.nmplayer.ui.Updateable;
import javafx.beans.Observable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * This class contains all the logic used to change the playback (sound that is
 * coming out of the speakers).
 *
 * The class contains information about the current state of the player, as well
 * as a PlaylistManager
 *
 * THIS CLASS SHOULD NOT BE INITIALIZED WITH CLASS CONSTRUCTOR, MusicPlayer IS A
 * SINGLETON CLASS, use MusicPlayer.getInstance() instead.
 *
 * @author Rsl1122
 * @see PlaylistManager
 */
public class MusicPlayer {

    private final PlaylistManager playlist;
    private final MessageSender msg;
    private MediaPlayer mp;
    private Updateable progressBar;
    private Updateable ui;

    private String selectedPlaylist;
    private boolean playing;
    private double volume;

    /**
     * Class constructor.
     *
     * Creates a new PlaylistManager and grabs the instance of MessageSender for
     * easier access to sending messages.
     *
     * @see MessageSender
     */
    public MusicPlayer() {
        playlist = new PlaylistManager();
        msg = MessageSender.getInstance();
        selectedPlaylist = "None";
        volume = 0.75;
    }

    /**
     * Method used to start the playback logic.
     *
     * Selects a playlist "all" that contains all the tracks in other playlists
     * and the tracks folder.
     *
     * Sets the initial playing state to false.
     *
     * @throws IllegalStateException If a javafx Application is has not been
     * started yet.
     */
    public void init() throws IllegalStateException {
        selectPlaylist("all");
        selectTrack(0);
        playing = false;
    }

    /**
     * This method is used to change the playlist. PlaylistFileManager is used
     * to load the playlist file, and TrackFileManager is used to translate the
     * file contents into Track objects.
     *
     * Then the playlist inside the PlaylistManager will be set as the new List
     * given by TrackFileManager.
     *
     * First track of the playlist will be selected if the currently playing
     * track is not found.
     *
     * @param playlistName Name of the playlist
     * @throws IllegalStateException If a javafx Application is has not been
     * started yet.
     * @see PlaylistFileManager
     * @see TrackFileManager
     * @see PlaylistManager
     */
    public void selectPlaylist(String playlistName) throws IllegalStateException {
        msg.send(Phrase.LOADING_PLAYLIST.parse(TextUtils.uppercaseFirst(playlistName)));
        selectedPlaylist = playlistName;
        List<Track> newPlaylist = TrackFileManager.translateToTracks(PlaylistFileManager.load(selectedPlaylist));
        if (selectedPlaylist.equals("all")) {
            Collections.sort(newPlaylist, new TrackComparator());
        }
        playlist.setPlaylist(newPlaylist);
        msg.send(Phrase.SELECTED_PLAYLIST.parse(TextUtils.uppercaseFirst(playlistName)));
        if (playlist.isEmpty()) {
            msg.send(Phrase.PLAYLIST_EMPTY + "");
        }
    }

    /**
     * Used to move to the next track in the playlist.
     *
     * If currentTrack is null (Not initialized) nothing is done. Otherwise the
     * playback is stopped, new Track selected, and then played.
     *
     * @throws IllegalStateException If a javafx Application is has not been
     * started yet.
     */
    public void nextTrack() throws IllegalStateException {
        if (playlist.getCurrentTrack() != null) {
            if (playlist.isEmpty()) {
                return;
            }
            mp.stop();
            playing = false;
            selectTrack(playlist.getCurrentTrackIndex() + 1);
            play();
        }
    }

    /**
     * Used to move to the previous track in the playlist.
     *
     * If currentTrack is null (Not initialized) nothing is done. Otherwise the
     * playback is stopped, new Track selected, and then played.
     *
     * @throws IllegalStateException If a javafx Application is has not been
     * started yet.
     */
    public void previousTrack() throws IllegalStateException {
        if (playlist.getCurrentTrack() != null) {
            if (playlist.isEmpty()) {
                return;
            }
            mp.stop();
            playing = false;
            selectTrack(playlist.getCurrentTrackIndex() - 1);
            play();
        }
    }

    /**
     * Begins/Resumes the playback.
     *
     * If MusicPlayer is null (not initialized) nothing is done. Playing Message
     * will be sent with MessageSender
     *
     * @see MessageSender
     */
    public void play() {
        if (mp != null) {
            playing = true;
            mp.play();
            msg.send(Phrase.NOW_PLAYING.parse(playlist.getCurrentTrack().toString()));
        }
    }

    /**
     * Pauses the playback.
     *
     * If MusicPlayer is null (not initialized) nothing is done.
     *
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
     * If MusicPlayer is null (not initialized) nothing is done. Stop Message
     * will be sent with MessageSender
     *
     * @see MessageSender
     */
    public void stop() {
        if (mp != null && playing) {
            playing = false;
            mp.stop();
            msg.send(Phrase.STOP + "");
        }
    }

    /**
     * Used to change the MediaPlayer object to play the Track.
     *
     * Creates a new MediaPlayer object with the filepath inside the Track
     * object, and frees the resources associated with the old MediaPlayer
     * object, if one exists. If the file specified by Track object doesn't
     * exist a message will be sent with MessageSender. After successfully
     * creating the new MediaPlayer object, the currentTrack information will be
     * updated and a select message will be sent with MessageSender
     *
     *
     * @param track Track to be played.
     * @throws IllegalStateException If a javafx Application is has not been
     * started yet.
     * @see MessageSender
     */
    public void selectTrack(Track track) throws IllegalStateException {
        if (track != null && !track.equals(playlist.getCurrentTrack())) {
            String mp3FilePath = track.getFilePath();
            File trackFile = new File(mp3FilePath);
            if (!trackFile.exists()) {
                msg.send(Phrase.NONEXISTING_FILE.parse(track.toString()));
                return;
            }
            Media play = new Media(trackFile.toURI().toString());
            if (mp != null) {
                mp.dispose();
            }
            mp = new MediaPlayer(play);
            mp.setVolume(volume);
            mp.setOnEndOfMedia(new Runnable() {
                @Override
                public void run() {
                    nextTrack();
                    ui.update();
                }
            });
            mp.currentTimeProperty().addListener((Observable ov) -> {
                progressBar.update();
            });
            mp.setOnReady(() -> {
                progressBar.update();
            });
            playlist.setCurrentTrack(track);
        }
    }

    /**
     * Shortcut for selectTrack(playlist.selectTrack(i)).
     *
     * @param i Index of the track to select.
     * @throws IllegalStateException If JavaFx Application is not running.
     */
    public void selectTrack(int i) throws IllegalStateException {
        selectTrack(playlist.selectTrack(i));
    }

    /**
     * Adds a new Track object to the playlist in PlaylistManager.
     *
     * If the track given is null nothing is done. Otherwise the track is added
     * to playlist in PlaylistManager, add message is sent with MessageSender
     * and the new playlist is saved with PlaylistFileManager.
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
        PlaylistFileManager.saveTracksAsPlaylist(playlist.getPlaylist(), selectedPlaylist);
    }

    /**
     * Removes a track object from the current Playlist, and saves the change to
     * the txt file.
     *
     * @param track Track to remove.
     */
    public void removeTrackFromPlaylist(Track track) {
        boolean removingCurrentTrack = playlist.getCurrentTrackIndex() == playlist.getIndexOf(track);
        if (removingCurrentTrack && playing) {
            stop();
        }
        playlist.removeTrackFromPlaylist(track);
        msg.send(Phrase.REMOVED_TRACK.parse(track.toString()));
        PlaylistFileManager.saveTracksAsPlaylist(playlist.getPlaylist(), selectedPlaylist);
        if (removingCurrentTrack) {
            selectTrack(playlist.getCurrentTrackIndex());
        }
    }

    /**
     * Used to get the relative playtime/duration of the playback for the
     * current track.
     *
     * @return a double from 0 to 1.0
     */
    public double getCurrentTrackProgress() {
        if (mp == null) {
            return 0;
        }
        return mp.getCurrentTime().toSeconds() / mp.getTotalDuration().toSeconds();
    }

    /**
     * Used to change the play position of the current track based on the
     * relative duration given.
     *
     * @param d a double from 0 to 1.0
     */
    public void setTrackPosition(double d) {
        if (mp == null) {
            return;
        }
        mp.seek(mp.getTotalDuration().multiply(d));
    }

    /**
     * Used to set the playback volume.
     *
     * @param d a double from 0 to 1.0
     */
    public void setVolume(double d) {
        if (d < 0) {
            d = 0;
        }
        if (d > 1) {
            d = 1;
        }
        volume = d;
        if (mp != null) {
            mp.setVolume(volume);
        }
    }

    /**
     * Used to get the playback volume.
     *
     * @return a double from 0 to 1.0
     */
    public double getVolume() {
        return volume;
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
     * Tells whether or not the MusicPlayer has active playback going on.
     *
     * @return Is sound coming out of the speakers?
     */
    public boolean isPlaying() {
        return playing;
    }

    /**
     * Grab the currently used List of Tracks from PlaylistManager.
     *
     * @return list of tracks.
     * @see PlaylistManager
     */
    public List<Track> getPlaylist() {
        return playlist.getPlaylist();
    }

    /**
     * Used to get the PlaylistManager.
     *
     * @return Currently used playlistmanager.
     */
    public PlaylistManager getPlaylistManager() {
        return playlist;
    }

    /**
     * Used to get the name of the currently playing playlist.
     *
     * @return For example "All" or "TestPlaylist"
     */
    public String getSelectedPlaylist() {
        return selectedPlaylist;
    }

    /**
     * Used to get the currently playing Track object.
     *
     * Returns null if nothing is playing (isPlaying() false)
     *
     * @return currently playing Track object or null if nothing is playing.
     */
    public Track getCurrentTrack() {
        return playlist.getCurrentTrack();
    }

    /**
     * Used to set the Updateable which .update() method will be called when the
     * playback moves forward.
     *
     * @param progressBar An Object that implements Updateable
     */
    public void setProgressBar(Updateable progressBar) {
        this.progressBar = progressBar;
    }

    /**
     * Used to set the Updateable which .update method will be called when the
     * playback ends at the end of file.
     *
     * @param updateable An Object that implements Updateable
     */
    public void setEndOfMediaUpdate(Updateable updateable) {
        this.ui = updateable;
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
