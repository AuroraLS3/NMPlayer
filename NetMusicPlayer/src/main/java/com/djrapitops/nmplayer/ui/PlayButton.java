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
public class PlayButton extends Button {

    public PlayButton() {
        super.setStyle("-fx-background-color: White");
        super.setText("Play");
        EventHandler h = (EventHandler<ActionEvent>) (ActionEvent event) -> {
            MusicPlayer.getInstance().play();
        };
        super.setOnAction(h);
    }
    
}
