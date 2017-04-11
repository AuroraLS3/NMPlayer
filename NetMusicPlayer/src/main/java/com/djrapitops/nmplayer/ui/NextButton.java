/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djrapitops.nmplayer.ui;

import com.djrapitops.nmplayer.functionality.MusicPlayer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * JavaFx UI component, a Button used to change the currently playing track.
 *
 * <p>
 * Playbutton is needed to update the pause status text if this button is
 * pressed.
 *
 * @author Rsl1122
 * @see PlaylistFileManager
 * @see PlaylistManager
 * @see MusicPlayer
 */
public class NextButton extends Button {

    /**
     * Constructor for the button. Sets the click event response to play the
     * next track on the MusicPlayer.
     *
     * @param play Already initialized PlayButton
     * @see MusicPlayer
     */
    public NextButton(PlayButton play) {
        super.setStyle("-fx-background-color: White");
        super.setText(">>");
        EventHandler h = (EventHandler<ActionEvent>) (ActionEvent event) -> {
            MusicPlayer.getInstance().nextTrack();
            play.update();
        };
        super.setOnAction(h);
    }

}
