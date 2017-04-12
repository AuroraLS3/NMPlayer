/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.ui.playlist;

import com.djrapitops.nmplayer.fileutils.PlaylistFileManager;
import com.djrapitops.nmplayer.functionality.MusicPlayer;
import com.djrapitops.nmplayer.functionality.playlist.PlaylistManager;
import com.djrapitops.nmplayer.messaging.MessageSender;
import com.djrapitops.nmplayer.messaging.Phrase;
import com.djrapitops.nmplayer.ui.Updateable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * JavaFx UI component, a Button used to change the current playlist.
 *
 * @author Rsl1122
 * @see PlaylistFileManager
 * @see PlaylistManager
 * @see MusicPlayer
 */
public class ChangePlaylistButton extends Button {

    /**
     * Constructor for the button. Sets the click event response to change the playlist of MusicPlayer.
     *
     * @param t TextArea which text is used to read user input for playlist name.
     * @param u A UI Component to update when the button is pressed.
     * @see MusicPlayer
     * @see UITrack
     * @see PlaylistManager
     */
    public ChangePlaylistButton(TextField t, Updateable u) {
        super.setText("Change Playlist");
        super.setStyle("-fx-background-color: White");
        super.setAlignment(Pos.CENTER_RIGHT);
        EventHandler h = (EventHandler<ActionEvent>) (ActionEvent event) -> {
            changePlaylist(t, u);
        };
        super.setOnAction(h);
    }

    public void changePlaylist(TextField t, Updateable u) throws IllegalStateException {
        String newPlaylist = t.getText().toLowerCase().trim();
        if (newPlaylist.isEmpty()) {
            MessageSender.getInstance().send(Phrase.EMPTY_NAME.parse(PlaylistFileManager.getKnownPlaylists()));
            return;
        }
        MusicPlayer mp = MusicPlayer.getInstance();
        mp.selectPlaylist(newPlaylist);
        u.update();
    }

}
