package com.djrapitops.nmplayer.functionality.playlist;

import com.djrapitops.nmplayer.functionality.Track;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Risto
 */
public class PlaylistManager {

    private List<Track> playlist;

    /**
     *
     * @param playlist
     */
    public PlaylistManager(List<Track> playlist) {
        this.playlist = playlist;
    }

    /**
     *
     */
    public PlaylistManager() {
        this(new ArrayList<>());
    }

    /**
     *
     * @param t
     */
    public void addFilePathToPlaylist(Track t) {
        playlist.add(t);
    }

    /**
     *
     * @param t
     */
    public void removeFilePathFromPlaylist(Track t) {
        playlist.remove(t);
    }

    /**
     *
     */
    public void clearPlaylist() {
        playlist.clear();
    }

    /**
     *
     * @return
     */
    public List<Track> getPlaylist() {
        return playlist;
    }

    /**
     *
     * @param playlist
     */
    public void setPlaylist(List<Track> playlist) {
        this.playlist = playlist;
    }

    /**
     *
     * @param trackName
     * @return
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
     *
     * @return
     */
    public boolean isEmpty() {
        return playlist.isEmpty();
    }
}