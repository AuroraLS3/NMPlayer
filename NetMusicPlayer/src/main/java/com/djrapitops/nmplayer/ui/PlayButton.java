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
 * JavaFx UI component, a Button used to play/pause the currently playing track.
 *
 * @author Rsl1122
 * @see MusicPlayer
 */
public class PlayButton extends Button implements Updateable {

    /**
     * Constructor for the Button.
     * <p>
     * Sets the click event response to either pause or play the playback with
     * MusicPlayer and update the text on the button.
     *
     * @see MusicPlayer
     */
    public PlayButton(Updateable u) {
        super.setStyle("-fx-background-color: #8290ed; -fx-text-fill: White");
        u.update();
        EventHandler h = (EventHandler<ActionEvent>) (ActionEvent event) -> {
            MusicPlayer musicPlayer = MusicPlayer.getInstance();
            if (!musicPlayer.isPlaying()) {
                musicPlayer.play();
            } else {
                musicPlayer.pause();
            }
            u.update();
        };
        super.setOnAction(h);
    }

    @Override
    public void update() {
        if (!MusicPlayer.getInstance().isPlaying()) {
            super.setText("Play");
        } else {
            super.setText("Pause");
        }
    }

}
