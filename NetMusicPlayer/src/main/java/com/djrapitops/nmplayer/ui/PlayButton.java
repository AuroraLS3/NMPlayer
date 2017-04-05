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

    private boolean showPlayButton;

    public PlayButton() {
        super.setStyle("-fx-background-color: White");
        showPlayButton = true;
        this.update();
        EventHandler h = (EventHandler<ActionEvent>) (ActionEvent event) -> {
            if (showPlayButton) {
                MusicPlayer.getInstance().play();
                showPlayButton = false;
            } else {
                MusicPlayer.getInstance().pause();
                showPlayButton = true;
            }
            update();
        };
        super.setOnAction(h);
    }

    public void update() {
        if (showPlayButton) {
            super.setText("Play");
        } else {
            super.setText("Pause");
        }
    }

    public void setShowPlayButton(boolean playButton) {
        this.showPlayButton = playButton;
    }

    public boolean showPlayButton() {
        return showPlayButton;
    }
    
    
}
