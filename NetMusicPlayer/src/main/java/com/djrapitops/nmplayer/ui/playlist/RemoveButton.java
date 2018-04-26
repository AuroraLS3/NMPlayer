/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.ui.playlist;

import com.djrapitops.nmplayer.fileutils.PlaylistFileManager;
import com.djrapitops.nmplayer.functionality.MusicPlayer;
import com.djrapitops.nmplayer.functionality.PlaylistManager;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

/**
 * JavaFx UI component, a Button used to change the currently playing track.
 *
 * @author Rsl1122
 * @see PlaylistFileManager
 * @see PlaylistManager
 * @see MusicPlayer
 */
public class RemoveButton extends Button {

    /**
     * Constructor for the button. Sets the click event response to remove the
     * associated track from Playlist.
     *
     * @param uiTrack A UITrack Component to update when the button is pressed.
     * @see MusicPlayer
     * @see UITrack
     * @see PlaylistManager
     */
    public RemoveButton(UITrack uiTrack) {
        setStyle("-fx-background-color: DarkRed; -fx-text-fill: White");
        setText("x");
        setAlignment(Pos.CENTER_RIGHT);
        setOnAction(event -> {
            MusicPlayer musicPlayer = MusicPlayer.getInstance();
            musicPlayer.removeTrackFromPlaylist(uiTrack.getTrack());
            PlaylistManager playlistManager = musicPlayer.getPlaylistManager();
            if (playlistManager.isRandom()) {
                playlistManager.setRandom(true);
            }
            uiTrack.update();
        });
    }

}
