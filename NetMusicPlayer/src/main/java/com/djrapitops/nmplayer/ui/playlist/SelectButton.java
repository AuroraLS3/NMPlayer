/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.ui.playlist;

import com.djrapitops.nmplayer.functionality.MusicPlayer;
import com.djrapitops.nmplayer.functionality.playlist.PlaylistManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

/**
 *
 * @author ristolah
 */
public class SelectButton extends Button {

    /**
     * Constructor for the button. Sets the click event response to play the
     * associated track from Playlist.
     *
     * @param uiTrack A UITrack Component to update when the button is pressed.
     * @see MusicPlayer
     * @see UITrack
     * @see PlaylistManager
     */
    public SelectButton(UITrack uiTrack) {
        super.setStyle("-fx-background-color: White");
        super.setAlignment(Pos.CENTER_LEFT);
        super.setText(uiTrack.getTrack().toString());
        super.setPrefWidth(10000);
        EventHandler h = (EventHandler<ActionEvent>) (ActionEvent event) -> {
            final MusicPlayer mp = MusicPlayer.getInstance();
            mp.selectTrack(uiTrack.getTrack());            
            mp.play();
            uiTrack.update();
        };
        super.setOnAction(h);
    }

}
