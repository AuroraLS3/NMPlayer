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
 *
 * @author ristolah
 */
public class PreviousButton extends Button {

    /**
     *
     * @param play
     */
    public PreviousButton(PlayButton play) {
        super.setStyle("-fx-background-color: White");
        super.setText("<<");
        EventHandler h = (EventHandler<ActionEvent>) (ActionEvent event) -> {
            MusicPlayer.getInstance().nextTrack();
            play.update();
        };
        super.setOnAction(h);
    }

}
