package com.djrapitops.nmplayer.functionality.playlist;

import com.djrapitops.nmplayer.functionality.Track;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to manipulate the List containing Track objects called
 * playlist.
 *
 * @author Risto
 * @see Track
 */
public class PlaylistManager {

    private List<Track> playlist;

    /**
     * Creates a new PlaylistManager with given List as the playlist.
     *
     * @param playlist A pre-existing List of tracks
     */
    public PlaylistManager(List<Track> playlist) {
        this.playlist = playlist;
    }

    /**
     * Creates a new PlaylistManager with a new empty list as the playlist.
     */
    public PlaylistManager() {
        this(new ArrayList<>());
    }

    /**
     * Adds a new Track to the playlist.
     *
     * @param t Track to be added.
     */
    public void addTrackToPlaylist(Track t) {
        playlist.add(t);
    }

    /**
     * Removed the given track object from the playlist.
     *
     * @param t Track to be removed.
     */
    public void removeTrackFromPlaylist(Track t) {
        playlist.remove(t);
    }

    /**
     * Removes all Tracks from the playlist.
     */
    public void clearPlaylist() {
        playlist.clear();
    }

    /**
     * Grab the list containing all the Track objects called playlist.
     *
     * @return the playlist.
     */
    public List<Track> getPlaylist() {
        return playlist;
    }

    /**
     * Replaces the current playlist with the given parameter.
     *
     * @param playlist a List used to replace the old playlist.
     */
    public void setPlaylist(List<Track> playlist) {
        this.playlist = playlist;
    }

    /**
     * Attempts to get a Track object from the playlist with the same track name
     * as the parameter.
     * <p>
     * If playlist contains multiple of the same name, the first one will be
     * returned.
     * <p>
     * If playlist contains none null will be returned.
     *
     * @param trackName Name to search for in the Track objects.
     * @return Track object that has the same name, or null.
     */
    public Track getTrackByName(String trackName) {
        for (Track track : playlist) {
            if (track.getName().equals(trackName)) {
                return track;
            }
        }
        return null;
    }

    /**
     * Check whether or not the playlist is empty.
     *
     * @return Emptiness state of the playlist.
     */
    public boolean isEmpty() {
        return playlist.isEmpty();
    }
}
