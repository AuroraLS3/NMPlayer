package com.djrapitops.nmplayer.functionality;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to manipulate the List containing Track objects called
 * playlist.
 *
 * @author Rsl1122
 * @see Track
 */
public class PlaylistManager {

    private List<Track> playlist;
    private RandomOrderUtility random;

    private Track currentTrack;

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
        if (!hasTrack(t)) {
            playlist.add(t);
        }
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
     * as the parameter. If playlist contains multiple of the same name, the
     * first one will be returned. If playlist contains none null will be
     * returned.
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
     * Used get a Track object from the playlist for the selectTrack(Track)
     * method.
     *
     * If random is enabled, the RandomOrderUtility index will be used to select
     * the track.
     *
     * @param i Index of the track in the playlist.
     * @return appropriate track object that is on the list.
     *
     */
    public Track selectTrack(int i) {
        final int tracks = playlist.size();
        if (playlist.isEmpty()) {
            return null;
        } else if (i == -1) {
            return selectTrack(tracks - 1);
        } else if (tracks > i && i >= 0) {
            if (isRandom()) {
                int index = random.getNewIndexFromOrder(i);
                return playlist.get(index);
            } else {
                return playlist.get(i);
            }
        } else {
            if (isRandom()) {
                setRandom(true);
            }
            return selectTrack(0);
        }
    }

    /**
     * Check whether or not the playlist is empty.
     *
     * @return Emptiness state of the playlist.
     */
    public boolean isEmpty() {
        return playlist.isEmpty();
    }

    /**
     * Used to get the index in the list of a certain Track object.
     *
     * If shuffle is enabled (isRandom()), the index of the track on the random
     * order will be returned.
     *
     * @param track Track object to look for.
     * @return Index of the given track object, -1 if not found.
     */
    public int getIndexOf(Track track) {
        int trackIndex = playlist.indexOf(track);
        if (isRandom()) {
            return random.getIndexOrderList().indexOf(trackIndex);
        }
        return trackIndex;
    }

    /**
     * Used to check if playlist has the same track that was given as parameter.
     *
     * @param track Track to look for.
     * @return Does the playlist have the track?
     */
    public boolean hasTrack(Track track) {
        for (Track t : playlist) {
            if (t.equals(track)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Used to get the currently playing track.
     *
     * @return currently playing track or null if nothing is playing.
     */
    public Track getCurrentTrack() {
        return currentTrack;
    }

    /**
     * Change the current track variable and it's index varable.
     *
     * @param currentTrack new Track.
     */
    public void setCurrentTrack(Track currentTrack) {
        this.currentTrack = currentTrack;
    }

    /**
     * Used to get the index of the current track.
     *
     * @return Index of the current track.
     */
    public int getCurrentTrackIndex() {
        return getIndexOf(currentTrack);
    }

    /**
     * Checks whether or shuffle is enabled.
     *
     * @return state.
     */
    public boolean isRandom() {
        return random != null;
    }

    /**
     * Changes the shuffle status.
     *
     * a true will create a new RandomOrderUtility.
     *
     * @param value true/false
     * @see RandomOrderUtility
     */
    public void setRandom(boolean value) {
        if (value) {
            random = new RandomOrderUtility(playlist.size(), getCurrentTrackIndex());
        } else {
            random = null;
        }
    }
}
